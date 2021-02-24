package com.pruvn.printserver.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.entity.UsermasterLog;
import com.pruvn.printserver.form.ChangePwdForm;
import com.pruvn.printserver.services.UsermasterLogService;
import com.pruvn.printserver.services.UsermasterService;
import com.pruvn.printserver.utils.Constant;
import com.pruvn.printserver.utils.ParameterApplication;

@Controller
@RequestMapping(value = "/mainMenu")
public class MainMenuController{	
	@Autowired
	private UsermasterService usermasterService;
	@Autowired
	private UsermasterLogService usermasterLogService;
	
	public UsermasterLogService getUsermasterLogService() {
		return usermasterLogService;
	}


	public void setUsermasterLogService(UsermasterLogService usermasterLogService) {
		this.usermasterLogService = usermasterLogService;
	}

	private static final Logger logger = Logger.getLogger(MainMenuController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getMainMenu(Model model,HttpSession session,HttpServletRequest request) throws UserException
	{
		logger.debug("reach ChangePwdController$getChangePwdForm...");
		logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getDetails());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Usermaster usermatser = usermasterService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		if(usermatser!=null){
			usermatser.setLastlogindate(new Date());
			usermatser.setCount_login_temp(null);
			usermatser.setDate_login_temp(null);
			if(usermatser.getLast_changed_pw()==null){
				usermatser.setStatus(ParameterApplication.ACTIVE.getStatus());
			}
			usermasterService.update(usermatser);
			model.addAttribute("usermatser",usermatser);
			
			UsermasterLog userlog=new UsermasterLog();
			userlog.setUsername(usermatser.getUsername());
			userlog.setDate_create(new Date());
			userlog.setNoidung(Constant.LOGIN);
			userlog.setNoidung2(Constant.LOGIN_SUCCESS);
			userlog.setSession_log(session.getId());
		    userlog.setIplogin(request.getRemoteAddr());
		    usermasterLogService.save(userlog);
		
			if(usermatser.getLast_changed_pw()==null){
				ChangePwdForm form = new ChangePwdForm();
				form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
				model.addAttribute(form);
				return "usermanager/changepwd";
			}
		}	
		return "redirect:listDocument.html";
	}

			
			public UsermasterService getUsermasterService() {
				return usermasterService;
			}

			public void setUsermasterService(UsermasterService usermasterService) {
				this.usermasterService = usermasterService;
			}
	
}


