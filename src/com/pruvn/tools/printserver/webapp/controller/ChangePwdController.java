package com.pruvn.tools.printserver.webapp.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.webapp.editor.ChangePwdForm;
import com.pruvn.tools.utils.SHA;


@Controller
public class ChangePwdController
{
	private static final Logger logger = Logger.getLogger(ChangePwdController.class);
	private UsermasterDAO usermasterDAO;

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

	@RequestMapping(value = "/changepwd.html",method = RequestMethod.GET)
	public String getChangePwdForm(Model model)
	{
		logger.debug("reach ChangePwdController$getChangePwdForm...");
		ChangePwdForm form = new ChangePwdForm();
		form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute(form);
		return "auth/changepwd";
	}

	@RequestMapping(value = "/changepwd.html",method = RequestMethod.POST)
	public String changePwd(@Valid ChangePwdForm form, BindingResult result, Model model)
	{
		try {
			logger.debug("reach ChangePwdController$changePwd...");
			
			if (result.hasErrors())
			{
				model.addAttribute(form);
				return "auth/changepwd";
			}
			
			if (form.getNewPwd().equals(form.getOldPwd())) {
				result.reject("common.form.reuse.old.pwd", "New password is the same as old password !");
				model.addAttribute(form);
				return "auth/changepwd";
			}
			
			if (!form.getNewPwd().equals(form.getReenterredNewPwd())) {
				result.reject("common.form.notmatched", "New password not matched !");
				model.addAttribute(form);
				return "auth/changepwd";
			}
			
			//Do some stuffs to change password for current user
			Usermaster user = (Usermaster) usermasterDAO.findByUsername(form.getUsername());
			String oldPwd = form.getOldPwd();
			oldPwd = SHA.encode(oldPwd);
			
			if (!user.getPassword().equals(oldPwd)) {
				result.reject("common.form.oldpwd.invalid", "Invalid old password !");
				model.addAttribute(form);
				return "auth/changepwd";
			}
			
			// Set new password and save it to database
			Date dateupdate= new Date();
			user.setLastChangedPw(new Timestamp(dateupdate.getTime()));
			user.setPassword(SHA.encode(form.getNewPwd()));
			user.setCountlogintemp(null);
			user.setDatelogintemp(null);
			usermasterDAO.saveOrUpdate(user);
			result.reject("operation.successful.message", "Operation completed successfully !");
			
			return "auth/changepwd";
		} catch (Exception e) {
			result.reject("operation.checkinput.message", e.getMessage());
			logger.error(e.getStackTrace());
			return "auth/changepwd";
		}
	}
}


