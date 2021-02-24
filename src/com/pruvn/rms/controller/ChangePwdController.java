package com.pruvn.rms.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.rms.common.PasswordValidator;
import com.pruvn.rms.domain.PasswordHistory;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.model.ChangePwdForm;
import com.pruvn.rms.model.LoginForm;
import com.pruvn.rms.service.PasswordHistoryService;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.MD5;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;


@Controller
public class ChangePwdController
{
	private static final Logger logger = Logger.getLogger(ChangePwdController.class);
	private UserMService userMService;
	private PasswordValidator passwordValidator;
	private PasswordHistoryService passwordHistoryService;
	private UserLogService userLogService;
	
	public UserLogService getUserLogService() {
		return userLogService;
	}
	@Autowired
	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}
	
	/**
	 * @return the passwordValidator
	 */
	public PasswordValidator getPasswordValidator() {
		return passwordValidator;
	}

	/**
	 * @param passwordValidator the passwordValidator to set
	 */
	@Autowired
	public void setPasswordValidator(PasswordValidator passwordValidator) {
		this.passwordValidator = passwordValidator;
	}

	public UserMService getUserMService() {
		return userMService;
	}
	
	@Autowired
	public void setPasswordHistory(PasswordHistoryService passwordHistoryService) {
		this.passwordHistoryService = passwordHistoryService;
	}
	
	public PasswordHistoryService getPasswordHistory() {
		return passwordHistoryService;
	}
	
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}

	@RequestMapping(value = "/auth/changepwd", method = RequestMethod.GET)
	public String getChangePwdForm(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach ChangePwdController$getChangePwdForm...");
		ChangePwdForm form = new ChangePwdForm();
		
		String username = (String) request.getSession().getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY);
		
		form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if (form.getUsername().equalsIgnoreCase("anonymoususer")) {
			form.setUsername(username);
		}
		
		model.addAttribute(form);
		return "changepwd";
	}

	@RequestMapping(value = "/auth/changepwd", method = RequestMethod.POST)
	public String changePwd(@Valid ChangePwdForm form, BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response)
	{
		try {
			logger.debug("reach ChangePwdController$changePwd...");
			
			if (result.hasErrors())
			{
				model.addAttribute(form);
				return "changepwd";
			}
			//if (form.getNewPwd().equals(form.getOldPwd())) {
			if(passwordHistoryService.isExitInHistory(form.getUsername(), MD5.encode(form.getNewPwd()))) {
				result.reject("common.form.reuse.old.pwd", "New password is the same in 5 lastest password !");
				model.addAttribute(form);
				return "changepwd";
			}
			
			if (!passwordValidator.validate(form.getNewPwd())) {
				result.reject("common.form.wrongpolicy", "Wrong password policy !");
				model.addAttribute(form);
				return "changepwd";
			}
			
			if (!form.getNewPwd().equals(form.getReenterredNewPwd())) {
				result.reject("common.form.notmatched", "New password not matched !");
				model.addAttribute(form);
				return "changepwd";
			}

			//Do some stuffs to change password for current user
			UserM user = userMService.getUserByUserName(form.getUsername());
			
			if (user == null) {
				throw new UserException("User not found !");
			}
			
			String oldPwd = form.getOldPwd();
			oldPwd = MD5.encode(oldPwd);
			
			if (!user.getPassword().equals(oldPwd)) {
				result.reject("common.form.oldpwd.invalid", "Invalid old password !");
				model.addAttribute(form);
				return "changepwd";
			}
			
			// Set new password and save it to database
			user.setPassword(MD5.encode(form.getNewPwd()));
			user.setLastChangedPw(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			user.setLastModifiedDate(Calendar.getInstance().getTime());
			user.setLastModifiedBy(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			userMService.saveOrUpdate(user);
			result.reject("changepwd.successful.message", "Your password was changed successfully, please login again with new password !");
			
			// Process when update password successfully
			PasswordHistory ph = new PasswordHistory();
			ph.setUserName(form.getUsername());
			ph.setDateLogin(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			ph.setPasswordCode(MD5.encode(form.getNewPwd()));
			ph.setDescription("Change pass from old pass " + oldPwd);
			passwordHistoryService.storeHistory(ph);
			//Keep password history by number password in config
			
			
			//Save user log 
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(SecurityContextHolder.getContext()
					.getAuthentication().getName());    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
	    	userlog.setActivity(ACTIONS.CHANGE_PW.toString());
	    	userlog.setInput("Username: " + user.getUsername());
	    	//userlog.setOutput(Constants.SUCCESS_RESET_PW);
	    	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
	    	userlog.setStatusNote("old pw:" + oldPwd + "; new pw: " + user.getPassword());
	    	userLogService.save(userlog);
	    	
			model.addAttribute("message", "Your password was changed successfully, please login again with new password !");
			model.addAttribute(new LoginForm());
			SecurityContextHolder.getContext().setAuthentication(null);
			return "login";
		} catch (UserException e) {
			result.reject("operation.checkinput.message", e.getMessage());
			logger.error(e.getStackTrace());
			return "changepwd";
		} catch (NoSuchAlgorithmException e) {
			result.reject("operation.failure.message", e.getMessage());
			logger.error(e.getStackTrace());
			return "changepwd";
		}
	}
}


