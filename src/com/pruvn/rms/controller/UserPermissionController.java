package com.pruvn.rms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.rms.common.Constants;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.model.UserPermissionForm;
import com.pruvn.rms.service.UserGroupAService;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;


@Controller
public class UserPermissionController 
{
	private static final Logger logger = Logger.getLogger(UserPermissionController.class);
	
	private UserMService userMService;
	private UserGroupAService userGroupAService;
	private UserLogService userLogService;
	
	public UserLogService getUserLogService() {
		return userLogService;
	}
	@Autowired
	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}
	
	/**
	 * @return the userGroupAService
	 */
	public UserGroupAService getUserGroupAService() {
		return userGroupAService;
	}

	/**
	 * @param userGroupAService the userGroupAService to set
	 */
	@Autowired
	public void setUserGroupAService(UserGroupAService userGroupAService) {
		this.userGroupAService = userGroupAService;
	}

	/**
	 * @return the userMService
	 */
	public UserMService getUserMService() {
		return userMService;
	}

	/**
	 * @param userMService the userMService to set
	 */
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}
	
	@RequestMapping(value = "/user/permissionmod", method = RequestMethod.GET)
	public String permissionmod(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach UserPermissionController$permissionmod...");
		UserPermissionForm form = new UserPermissionForm();
		String userid = request.getParameter("id");
		if (StringUtils.isNotEmpty(userid) && CommonUtils.isNumeric(userid.trim())) {
			// Get user and popular this user to form
			UserM user = userMService.getUserByUserId(new Integer(userid));
			if (user != null) {
				form.setId(user.getId());
				form.setUsername(user.getUsername());
				form.setEmailCode(user.getEmailCode());
				form.setUserPlace(user.getUserPlace()); 
				List<String> grantedPermission = userGroupAService.getGrantedPermissionsByUser(user); 
				form.setGrantedPermission(grantedPermission);
			}
		}

		List<String> allPermission = userGroupAService.getAllPermissions();
		form.setAllPermission(allPermission);
		model.addAttribute(form);
		return "permissionmod";
	}
	
	@RequestMapping(value = "/user/permissionmod", method = RequestMethod.POST)
	public String permissionmodSubmit(@Valid UserPermissionForm form, BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response)  throws Exception
	{
		logger.debug("reach UserPermissionController$permissionmodSubmit...");
		UserM user = userMService.getUserByUserId(new Integer(form.getId()));		
		userGroupAService.grantPermissions(user, form.getGrantedPermission());
		
		//Save user log 
		UserLog userlog= new UserLog();
    	userlog.setLogDate(new Date());
    	userlog.setUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());    	
    	userlog.setRemoteIP(request.getRemoteAddr());
    	userlog.setSession(request.getSession().getId());
    	
    	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
    	userlog.setActivity(ACTIONS.GRANT_PERMISSION.toString());
    	userlog.setInput("Grant permission to : " + user.getUsername() + ", list role:" + form.getGrantedPermission());
    	userlog.setOutput(Constants.SUCCESS_GRANT_PER);
    	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
    	//userlog.setStatusNote(statusNote);
    	
    	
    	userLogService.save(userlog);
		
		return "redirect:/user/userlist.html";
	}
}


