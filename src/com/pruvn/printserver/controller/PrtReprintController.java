package com.pruvn.printserver.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.printserver.entity.PrtReprintM;
import com.pruvn.printserver.form.PrtReprintForm;
import com.pruvn.printserver.services.PrintServerReprintService;



@Controller
public class PrtReprintController 
{
	private static final Logger logger = Logger.getLogger(PrtReprintController.class);
	
	private PrintServerReprintService printServerReprintService;
	
	/**
	 * @return the printServerReprintService
	 */
	public PrintServerReprintService getPrintServerReprintService() {
		return printServerReprintService;
	}

	/**
	 * @param printServerReprintService the printServerReprintService to set
	 */
	@Autowired
	public void setPrintServerReprintService(
			PrintServerReprintService printServerReprintService) {
		this.printServerReprintService = printServerReprintService;
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String prePrintList(@RequestParam(value = "applid", required = false) String applid, Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$prePrintList...");
		List<PrtReprintM> list = null;
		PrtReprintForm form = new PrtReprintForm();
		if (StringUtils.isNotEmpty(applid)) {
			list = printServerReprintService.findPrtReprintByApplid(applid);
		} else {
			list = printServerReprintService.listAllReprint();
		}
		
		form.setPrtReprints(list);
		model.addAttribute("prtReprintForm",form);
		return "prtReprint/prtReprintList";
	}
	
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	public String search(@ModelAttribute("prtReprintForm") PrtReprintForm form, BindingResult result, Model model) throws ParseException
	{
		logger.debug("reach CMClientController$search...");
		
		if (result.hasErrors())
		{
			return "prtReprint/prtReprintList";
		}
		
		logger.debug("reach DeptController$prePrintList...");
		List<PrtReprintM> list = null;
		if (form.getApplid() != null) {
			list = printServerReprintService.findPrtReprintByApplid(form.getApplid());
		} else {
			list = printServerReprintService.listAllReprint();
		}
		
		
		form.setPrtReprints(list);
		model.addAttribute(form);
		return "prtReprint/prtReprintList";
	}

	@RequestMapping(value = "/mod", method = RequestMethod.GET)
	public String prePrintMod(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$prePrintMod...");
		PrtReprintForm form = new PrtReprintForm();
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			// Get user and popular this user to form
			PrtReprintM prtReprintM = printServerReprintService.findPrtReprintById(new Integer(id));
			if (prtReprintM != null) {
				form.setId(prtReprintM.getId());
				form.setApplid(prtReprintM.getApplid());
				form.setNotes(prtReprintM.getNotes());
				form.setStopInMinutes(prtReprintM.getStopInMinutes());
			}
		}

		form.setPrtReprints(printServerReprintService.listAllReprint());
		model.addAttribute(form);
		return "prtReprint/prtReprintMod";
	}
	
	@RequestMapping(value = "/mod", method = RequestMethod.POST)
	public String prePrintModSubmit(@Valid PrtReprintForm form, BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response)  throws Exception
	{
		logger.debug("reach DeptController$prePrintModSubmit...");
		PrtReprintM prtReprint = null;
		
		if (result.hasErrors()) {
			model.addAttribute(form);
			return "prtReprint/prtReprintMod";
		}
		
		if (form.getId() != null && form.getId() > 0) {
			prtReprint = printServerReprintService.findPrtReprintById(form.getId());	
		}
		
		if (prtReprint == null) {
			prtReprint = new PrtReprintM();
			prtReprint.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			prtReprint.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			prtReprint.setFromDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		
		prtReprint.setApplid(form.getApplid());
		prtReprint.setNotes(form.getNotes());
		prtReprint.setStopInMinutes(form.getStopInMinutes());
		
		printServerReprintService.saveOrUpdate(prtReprint);

		return "redirect:/list.html";
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String prePrintDel(@RequestParam(value = "id", required = false) String id, Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$prePrintDel...");
		if (StringUtils.isNotEmpty(id)) {
			// Get prtReprint and popular this user to form
			PrtReprintM prtReprint = printServerReprintService.findPrtReprintById(new Integer(id));
			if (prtReprint != null) {
				printServerReprintService.deleteReprint(prtReprint);
			}
		}
		return "redirect:/list.html";
	}
}


