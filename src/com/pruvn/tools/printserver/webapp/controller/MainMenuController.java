package com.pruvn.tools.printserver.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.tools.common.util.Constant;
import com.pruvn.tools.printserver.ParammasterDAO;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.UsermasterlogDAO;
import com.pruvn.tools.printserver.pojo.Parammaster;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.pojo.Usermasterlog;
import com.pruvn.tools.printserver.webapp.editor.ChangePwdForm;
import com.pruvn.tools.utils.ConstantApp;
import com.pruvn.tools.utils.ParameterApplication;


@Controller
@RequestMapping(value = "/mainMenu")
public class MainMenuController
{	
	private UsermasterDAO usermasterDAO;
	private UsermasterlogDAO usermasterlogDAO;
	private ParammasterDAO parammasterDAO;
	private static final Logger logger = Logger.getLogger(MainMenuController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getMainMenu(Model model,HttpSession session,HttpServletRequest request)
	{
		logger.debug("reach ChangePwdController$getChangePwdForm...");
		logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getDetails());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
		logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Usermaster usermatser = usermasterDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		String message="";
		if(usermatser!=null && usermatser.getStatus()==ParameterApplication.BLOCK.getStatus()){
			message="The account is locked ("+usermatser.getReasonlock()+")";
			//save log
			Usermasterlog userlog=new Usermasterlog();
			userlog.setUsername(usermatser.getUsername());
			userlog.setDate(new Date());
			userlog.setNoidung(ConstantApp.LOGIN);
			userlog.setNoidung1(usermatser.getReasonlock());
			userlog.setSession(session.getId());
		    userlog.setIplogin(request.getRemoteAddr());
			usermasterlogDAO.save(userlog);
			
			model.addAttribute("messages",message);
	        SecurityContextHolder.getContext().setAuthentication(null);
			return "forward:/auth/login.html?login_error=1";
		}
		if(usermatser!=null && usermatser.getStatus()==ParameterApplication.DELETE.getStatus()){
			message="The account is deleted";
			//save log
			Usermasterlog userlog=new Usermasterlog();
			userlog.setUsername(usermatser.getUsername());
			userlog.setDate(new Date());
			userlog.setNoidung(ConstantApp.LOGIN);
			userlog.setNoidung1(ParameterApplication.DELETE.getName());
			userlog.setSession(session.getId());
		    userlog.setIplogin(request.getRemoteAddr());
			usermasterlogDAO.save(userlog);
			model.addAttribute("messages",message);
	        SecurityContextHolder.getContext().setAuthentication(null);
	        return "forward:/auth/login.html?login_error=1";
		}
		if(usermatser!=null){
			usermatser.setLastlogindate(new Date());
			usermatser.setCountlogintemp(null);
			usermatser.setDatelogintemp(null);
			if(usermatser.getLastChangedPw()==null){
				usermatser.setStatus(ParameterApplication.ACTIVE.getStatus());
			}
			usermasterDAO.update(usermatser);
			model.addAttribute("usermatser",usermatser);
			
			Usermasterlog userlog=new Usermasterlog();
			userlog.setUsername(usermatser.getUsername());
			userlog.setDate(new Date());
			userlog.setNoidung(ConstantApp.LOGIN);
			userlog.setSession(session.getId());
		    userlog.setIplogin(request.getRemoteAddr());
			usermasterlogDAO.save(userlog);
			
			
			
		
			if(usermatser.getLastChangedPw()==null){
				ChangePwdForm form = new ChangePwdForm();
				form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
				model.addAttribute(form);
				return "auth/changepwd";
			}
		}
		return "redirect:listDocument.html";
	}
	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}
	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}
	public UsermasterlogDAO getUsermasterlogDAO() {
		return usermasterlogDAO;
	}
	@Autowired
	public void setUsermasterlogDAO(UsermasterlogDAO usermasterlogDAO) {
		this.usermasterlogDAO = usermasterlogDAO;
	}
	public ParammasterDAO getParammasterDAO() {
		return parammasterDAO;
	}
	@Autowired
	public void setParammasterDAO(ParammasterDAO parammasterDAO) {
		this.parammasterDAO = parammasterDAO;
	}
	
}


