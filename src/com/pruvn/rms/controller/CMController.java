package com.pruvn.rms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pruvn.rms.domain.RecordInBox;
import com.pruvn.rms.domain.RecordScanCM;
import com.pruvn.rms.domain.RecordScanCMCheck;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.service.RecordCMService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.STAGE2;

@Controller
public class CMController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(CMController.class);

	private RecordCMService recordCMService;

	/**
	 * @return the recordCMService
	 */
	public RecordCMService getRecordCMService() {
		return recordCMService;
	}

	/**
	 * @param RecordrecordCMService
	 *            the recordCMService to set
	 */
	@Autowired
	public void setSecordCMService(RecordCMService recordCMService) {
		this.recordCMService = recordCMService;
	}

	/****** Loan Documents Scan (STAGE = RMT_SCAN_CM) ******/
	@RequestMapping(value = "/cm/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordScanCM> list = recordCMService.getAllRecordScanCMs_ACL(username,
				STAGE2.RMT_SCAN_CM.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordScanCMList";
	}

	@RequestMapping(value = "/cm/index", method = RequestMethod.POST)
	public String indexAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						if (ACTIONS.SCAN_TO_CM.name.equalsIgnoreCase(btnSubmit)) {
							recordCMService.callInvolve(request, 
									ACTIONS.SCAN_TO_CM.toString(), username,
									STAGE2.RMT_SCAN_CM.getName(), Integer.parseInt(id));
						}
					}
				}
			} catch (Exception e) {
				logger.error("indexAction has error message=" + e.getMessage());
			}
			return "redirect:/cm/index.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/cm/index.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordScanCM> list = recordCMService.getAllRecordScanCMs_ACL(username,
					STAGE2.RMT_SCAN_CM.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordScanCMList";
		}
	}

	@RequestMapping(value = "/cm/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordScanCM record = recordCMService.getRecordScanCMById(id);
		model.addAttribute("record", record);
		return model;
	}
	/****** END Loan Documents Scan ******/
	
	
	/****** Loan Documents Scan Check (STAGE = RMT_SCAN_CM_CHECK) ******/
	@RequestMapping(value = "/cm/check", method = RequestMethod.GET)
	public String check(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordScanCMCheck> list = recordCMService.getAllRecordScanCMChecks_ACL(username,
				STAGE2.RMT_SCAN_CM_CHECK.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordScanCMCheckList";
	}

	@RequestMapping(value = "/cm/check", method = RequestMethod.POST)
	public String checkAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						if (ACTIONS.SCAN_QUALITY_CHECK.name.equalsIgnoreCase(btnSubmit)) {
							recordCMService.callInvolve(request,
									ACTIONS.SCAN_QUALITY_CHECK.toString(), username,
									STAGE2.RMT_SCAN_CM_CHECK.getName(), Integer.parseInt(id));
						}
					}
				}
			} catch (Exception e) {
				logger.error("indexAction has error message=" + e.getMessage());
			}
			return "redirect:/cm/check.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/cm/check.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordScanCMCheck> list = recordCMService.getAllRecordScanCMChecks_ACL(username,
					STAGE2.RMT_SCAN_CM_CHECK.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordScanCMCheckList";
		}
	}

	@RequestMapping(value = "/cm/check_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model checkDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordScanCMCheck record = recordCMService.getRecordScanCMCheckById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Documents Scan Check ******/
	
	/****** Loan Documents In Box (STAGE = RMT_RECORD_IN_BOX) ******/
	@RequestMapping(value = "/cm/inBox", method = RequestMethod.GET)
	public String box(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordInBox> list = recordCMService.getAllRecordInBoxs_ACL(username,
				STAGE2.RMT_RECORD_IN_BOX.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordInBoxList";
	}

	@RequestMapping(value = "/cm/inBox", method = RequestMethod.POST)
	public String boxAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			try {
				for (String id : ids) {
					String btnSubmit = request.getParameter("btnSubmit");
					/*if (!CommonUtils.isNullOrEmpty(btnSubmit)) {
						if ("Scan quality check".equalsIgnoreCase(btnSubmit)) {
							recordCMService.callInvolve(
									ACTIONS.SCAN_QUALITY_CHECK.toString(), username,
									STAGE2.RMT_RECORD_IN_BOX.getName(), Integer.parseInt(id));
						}
					}*/
				}
			} catch (Exception e) {
				logger.error("indexAction has error message=" + e.getMessage());
			}
			return "redirect:/cm/inBox.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/cm/inBox.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordInBox> list = recordCMService.getAllRecordInBoxs_ACL(username,
					STAGE2.RMT_SCAN_CM_CHECK.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordInBoxList";
		}
	}

	@RequestMapping(value = "/cm/inBox_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model boxDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordInBox record = recordCMService.getRecordInBoxById(id);
		model.addAttribute("record", record);
		return model;
	}
	/****** END Loan Documents In Box (STAGE = RMT_RECORD_IN_BOX) ******/
}
