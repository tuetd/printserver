package com.pruvn.rms.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/items/index")
public class ItemsController
{
	private static final Logger logger = Logger.getLogger(ItemsController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String getItem()
	{
		//logger.debug("reach LoginController$getLoginForm...");
		//logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		//logger.info(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		//logger.info(SecurityContextHolder.getContext().getAuthentication().getDetails());
		//logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
		//logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "items";
	}
}


