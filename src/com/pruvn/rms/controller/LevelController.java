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

import com.pruvn.rms.domain.UserLevelM;
import com.pruvn.rms.model.UserLevelMForm;
import com.pruvn.rms.service.UserLevelMService;
import com.pruvn.rms.utils.CommonUtils;


@Controller
public class LevelController 
{
	private static final Logger logger = Logger.getLogger(LevelController.class);
	
	private UserLevelMService userLevelMService;
	
	
	/**
	 * @return the userLevelMService
	 */
	public UserLevelMService getUserLevelMService() {
		return userLevelMService;
	}

	/**
	 * @param userLevelMService the userLevelMService to set
	 */
	@Autowired
	public void setUserLevelMService(UserLevelMService userLevelMService) {
		this.userLevelMService = userLevelMService;
	}

	@RequestMapping(value = "/admin/levellist", method = RequestMethod.GET)
	public String userlist(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach LevelController$userlist...");
		List<UserLevelM> list = userLevelMService.findAll();
		model.addAttribute("levelList", list);
		return "levellist";
	}

	@RequestMapping(value = "/admin/levelmod", method = RequestMethod.GET)
	public String createuser(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach LevelController$createlevel...");
		UserLevelMForm form = new UserLevelMForm();
		String levelid = request.getParameter("id");
		if (StringUtils.isNotEmpty(levelid) && CommonUtils.isNumeric(levelid.trim())) {
			// Get user and popular this user to form
			UserLevelM level = userLevelMService.getById(new Integer(levelid));
			if (level != null) {
				form.setLevelid(level.getLevelid());
				form.setLevelcode(level.getLevelcode());
				form.setLevelname(level.getLevelname());
			}
		}
		model.addAttribute(form);
		return "levelmod";
	}
	
	@RequestMapping(value = "/admin/levelmod", method = RequestMethod.POST)
	public String createlevelSubmit(@Valid UserLevelMForm form, BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response)  throws Exception
	{
		logger.debug("reach LevelController$createlevelSubmit...");
		UserLevelM level = null;
		
		if (StringUtils.isEmpty(form.getLevelcode())) {
			result.reject("level.emptyrequirefields", "Please input required field !");
			model.addAttribute(form);
			return "levelmod";
		}
		
		if (form.getLevelid() != null && form.getLevelid() > 0) {
			level = userLevelMService.getById(form.getLevelid());	
		} else {
			level = userLevelMService.findBylevelcode1(form.getLevelcode());
			
			if (level != null) {
				result.reject("level.notexisted", "Level " + form.getLevelcode() + " existed !");
				model.addAttribute(form);
				return "levelmod";
			}
		}
		
		if (level == null) {
			level = new UserLevelM();
		}
		
		level.setLevelcode(form.getLevelcode());
		level.setLevelname(form.getLevelname());
		
		userLevelMService.saveOrUpdate(level);

		return "redirect:/admin/levellist.html";
	}
	
	@RequestMapping(value = "/admin/leveldel", method = RequestMethod.GET)
	public String leveldel(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach LevelController$leveldel...");
		String levelid = request.getParameter("id");
		if (StringUtils.isNotEmpty(levelid) && CommonUtils.isNumeric(levelid.trim())) {
			// Get level and popular this user to form
			UserLevelM level = userLevelMService.getById(new Integer(levelid));
			if (level != null) {
				userLevelMService.delete(level);
			}
		}
		return "redirect:/admin/levellist.html";
	}
}


