package com.pruvn.tools.printserver.webapp.controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;
import com.pruvn.tools.common.util.MailUtil;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.webapp.editor.ResetPwdForm;
import com.pruvn.tools.utils.ParameterApplication;
import com.pruvn.tools.utils.SHA;


@Controller
@RequestMapping(value = "/resetpwd.html")
public class ResetPwdController
{
	private static final Logger logger = Logger.getLogger(ResetPwdController.class);
	private UsermasterDAO usermasterDAO;
	private ReloadableResourceBundleMessageSource messageSource;
	

	/**
	 * @return the usermasterDAO
	 */
	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}

	/**
	 * @param usermasterDAO the usermasterDAO to set
	 */
	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getResetPwdForm(Model model)
	{
		logger.debug("reach ResetPwdController$getResetPwdForm...");
		model.addAttribute(new ResetPwdForm());
		return "auth/resetpwd";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String resetPwd(@Valid ResetPwdForm resetPwdForm, BindingResult result, Model model,HttpServletRequest request)
	{
			logger.debug("reach ResetPwdController$resetPwd...");
			if (result.hasErrors())
			{
				return "auth/resetpwd";
			}
			Usermaster usermaster=usermasterDAO.findByUsername(resetPwdForm.getUsername());
			if(usermaster==null){
				result.reject("common.form.resetpwd.checkinput", "Username does not exist !");
				return "auth/resetpwd";
			}else  {
					if(usermaster.getEmailCode()==null || !usermaster.getEmailCode().equals(resetPwdForm.getEmail())){
						result.reject("common.form.resetpwd.checkinput", "Email does not exist !");
						return "auth/resetpwd";
					}
					if(usermaster.getStatus()==ParameterApplication.BLOCK.getStatus()){
						result.reject("block.user","The account is locked");
						return "auth/resetpwd";
					}
					if(usermaster.getStatus()==ParameterApplication.DELETE.getStatus()){
						result.reject("delete.user","The account is deleted");
						return "auth/resetpwd";
					}
//					Email to user new password
					String newPwd = RandomStringUtils.randomAlphanumeric(10);
					usermaster.setPassword(SHA.encode(newPwd));
					usermaster.setLastChangedPw(null);
					usermasterDAO.saveOrUpdate(usermaster);
					String newpwdEmailContent = this.messageSource.getMessage("mypru.resetpass.response.email.content", new Object[] {newPwd}, RequestContextUtils.getLocale(request));
					String newpwdEmailSubject = this.messageSource.getMessage("mypru.resetpass.response.email.subject", new Object[] {}, RequestContextUtils.getLocale(request));
					MailUtil.sendMail(resetPwdForm.getEmail(),newpwdEmailContent,newpwdEmailSubject);
						result.reject("common.form.resetpwd.sucessfully", "Please check your mailbox to get new password");
					}
		      return "auth/resetpwd";
	}

	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}
	@Autowired
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}


}


