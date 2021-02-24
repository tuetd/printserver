package com.pruvn.rms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.rms.model.LoginForm;


@Controller
@RequestMapping(value = "/auth/login")
public class LoginController
{
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginForm(Model model)
	{
		logger.debug("reach LoginController$getLoginForm...");
		model.addAttribute(new LoginForm());
		SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid LoginForm loginForm, BindingResult result, Model model)
	{
		logger.debug("reach UploadController$create...");
		
		if (result.hasErrors())
		{
			return "login";
		}
		
		//Do some stuff to authenticate user
		
		return "items";
	}
}


