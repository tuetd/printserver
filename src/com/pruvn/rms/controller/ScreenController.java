/*
 * Java bean class for entity table RM_SCREEN 
 * Created on 3 Oct 2013 ( Time 12:18:01 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.rms.domain.Screen;
import com.pruvn.rms.model.ScreenForm;
import com.pruvn.rms.service.ScreenService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ParameterApplication;

/**
 * Controller implementation for table "RM_SCREEN"
 * 
 * @author Telosys Tools Generator
 * 
 */

@Controller
public class ScreenController {
	private static final Logger logger = Logger
			.getLogger(ScreenController.class);

	private ScreenService screenService;

	/**
	 * @return the ScreenService
	 */
	public ScreenService getScreenService() {
		return screenService;
	}

	/**
	 * @param ScreenService
	 *            the ScreenService to set
	 */
	@Autowired
	public void setScreenService(ScreenService screenService) {
		this.screenService = screenService;
	}

	@RequestMapping(value = "/admin/screenlist", method = RequestMethod.GET)
	public String screenlist(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach screenController#userlist...");
		List<Screen> list = screenService.findAll();
		model.addAttribute("screenList", list);
		return "screenlist";
	}

	@RequestMapping(value = "/admin/screenmod", method = RequestMethod.GET)
	public String createscreen(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach screenController#createscreen...");
		ScreenForm form = new ScreenForm();
		String screenid = request.getParameter("id");
		if (StringUtils.isNotEmpty(screenid)
				&& CommonUtils.isNumeric(screenid.trim())) {
			// Get user and popular this user to form
			Screen screen = screenService.getById(new Integer(screenid));
			if (screen != null) {
				form.setId(screen.getId());
				form.setStage(screen.getStage());
				form.setName(screen.getName());
				form.setViewName(screen.getViewName());
				form.setPriority1(screen.getPriority1());
				form.setPriority2(screen.getPriority2());
				form.setIsActived(screen.getIsActived() != null && screen.getIsActived() == ParameterApplication.ACTIVE.getStatus());
				form.setName(screen.getName());
				form.setIsActived(screen.getIsActived() != null && screen.getIsActived() == ParameterApplication.ACTIVE.getStatus());
			}
		}
		model.addAttribute(form);
		return "screenmod";
	}

	@RequestMapping(value = "/admin/screenmod", method = RequestMethod.POST)
	public String createscreenSubmit(@Valid ScreenForm form,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("reach screenController#createscreenSubmit...");
		Screen screen = null;

		if (StringUtils.isEmpty(form.getStage())) {
			result.reject("screen.emptyrequirefields",
					"Please input required field !");
			model.addAttribute(form);
			return "screenmod";
		}

		if (form.getId() != null && form.getId() > 0) {
			screen = screenService.getById(form.getId());
		} else {
			screen = screenService.findByStage(form.getStage());

			if (screen != null) {
				result.reject("screen.notexisted",
						"screen " + form.getStage() + " existed !");
				model.addAttribute(form);
				return "screenmod";
			}
		}

		if (screen == null) {
			screen = new Screen();
		}

		screen.setId(form.getId());
		screen.setStage(form.getStage());
		screen.setName(form.getName());
		screen.setViewName(form.getViewName());		
		screen.setPriority1(form.getPriority1());
		screen.setPriority2(form.getPriority2());		
		screen.setIsActived(form.getIsActived() ? ParameterApplication.ACTIVE.getStatus() : ParameterApplication.NOACTIVE.getStatus());
		screenService.saveOrUpdate(screen);

		return "redirect:/admin/screenlist.html";
	}

	@RequestMapping(value = "/admin/screendel", method = RequestMethod.GET)
	public String screendel(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach screenController#screendel...");
		String screenid = request.getParameter("id");
		if (StringUtils.isNotEmpty(screenid)
				&& CommonUtils.isNumeric(screenid.trim())) {
			// Get screen and popular this user to form
			Screen screen = screenService.getById(new Integer(screenid));
			if (screen != null) {
				screen.setIsActived(ParameterApplication.NOACTIVE.getStatus());
				screenService.saveOrUpdate(screen);
			}
		}
		return "redirect:/admin/screenlist.html";
	}
}