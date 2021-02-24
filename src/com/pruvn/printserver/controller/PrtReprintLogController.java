package com.pruvn.printserver.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.printserver.entity.PrtReprintLogM;
import com.pruvn.printserver.form.PrtReprintForm;
import com.pruvn.printserver.services.PrintServerReprintService;

@Controller
public class PrtReprintLogController 
{
	private static final Logger logger = Logger.getLogger(PrtReprintLogController.class);
	
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

	@RequestMapping(value = "/listlog", method = RequestMethod.GET)
	public String prePrintList(@RequestParam(value = "applid", required = false) String applid, Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$prePrintList...");
		List<PrtReprintLogM> list = null;
		PrtReprintForm form = new PrtReprintForm();
		if (StringUtils.isNotEmpty(applid)) {
			list = printServerReprintService.searchReprintLogByApplid(applid);
		} else {
			list = printServerReprintService.listAllReprintLog();
		}
		
		form.setPrtReprintLogs(list);
		model.addAttribute("prtReprintForm",form);
		return "prtReprint/prtReprintLogList";
	}
	
	@RequestMapping(value = "/listlog", method = RequestMethod.POST)
	public String search(@ModelAttribute("prtReprintForm")PrtReprintForm form, BindingResult result, Model model) throws ParseException
	{
		logger.debug("reach CMClientController$search...");
		
		if (result.hasErrors())
		{
			return "prtReprint/prtReprintLogList";
		}
		
		logger.debug("reach DeptController$prePrintList...");
		List<PrtReprintLogM> list = null;
		if (form.getApplid() != null) {
			list = printServerReprintService.searchReprintLogByApplid(form.getApplid());
		} else {
			list = printServerReprintService.listAllReprintLog();
		}
		
		
		form.setPrtReprintLogs(list);
		model.addAttribute(form);
		return "prtReprint/prtReprintLogList";
	}
}

