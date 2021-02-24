package com.pruvn.printserver.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		return "index";
	}
	
}
