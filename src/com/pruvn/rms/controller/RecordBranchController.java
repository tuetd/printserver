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

import com.pruvn.rms.domain.RecordBranch;
import com.pruvn.rms.domain.RecordBranchDeliver;
import com.pruvn.rms.domain.RecordBranchReceive;
import com.pruvn.rms.domain.RecordBranchReturn;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.service.RecordBranchService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.STAGE;

@Controller
public class RecordBranchController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(RecordBranchController.class);

	private RecordBranchService recordBranchService;

	/**
	 * @return the LoanService
	 */
	public RecordBranchService getRecordBranchService() {
		return recordBranchService;
	}

	/**
	 * @param LoanService
	 *            the LoanService to set
	 */
	@Autowired
	public void setRecordBranchService(RecordBranchService recordBranchService) {
		this.recordBranchService = recordBranchService;
	}

	/****** RMT Loan Kit Send To Branch (STAGE = RMT_RECORD/RMT_CS_BRANCH) ******/
	@RequestMapping(value = "/recordbranch/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordBranch> list = recordBranchService.getAllRecordBranchs_ACL(username,
				STAGE.RMT_RECORD_RMT_CS_BRANCH.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordBranchList";
	}

	@RequestMapping(value = "/recordbranch/index", method = RequestMethod.POST)
	public String indexAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "reasonDesc", required = false) String reasonDesc,
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
						if (ACTIONS.RMT_CS_BRANCH_RECEIVE.name.equalsIgnoreCase(btnSubmit)) {
							recordBranchService.callInvolve(request, ACTIONS.RMT_CS_BRANCH_RECEIVE.toString(), username,
							STAGE.RMT_RECORD_RMT_CS_BRANCH.getName(), Integer.parseInt(id));

						} else if (ACTIONS.RMT_CS_BRANCH_RETURN.name.equalsIgnoreCase(btnSubmit)) {
							recordBranchService.callInvolve(request, ACTIONS.RMT_CS_BRANCH_RETURN.toString(), username,
							STAGE.RMT_RECORD_RMT_CS_BRANCH.getName(), Integer.parseInt(id), reason, reasonDesc);
						}
					}

				}
			} catch (Exception e) {
				logger.error("indexAction has error message=" + e.getMessage());
			}
			return "redirect:/recordbranch/index.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/recordbranch/index.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordBranch> list = recordBranchService.getAllRecordBranchs_ACL(username,
					STAGE.RMT_RECORD_RMT_CS_BRANCH.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordBranchList";
		}
	}

	@RequestMapping(value = "/recordbranch/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model detail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordBranch record = recordBranchService.getRecordBranchById(id);
		model.addAttribute("record", record);
		return model;
	}
	
	/****** END Loan Kit Send To Branch (STAGE = RMT_RECORD/RMT_CS_BRANCH) ******/
	
	/****** Loan Kit Receive At Branch (STAGE = RMT_RECORD/RMT_CS_BRANCH_RECEIVE) ******/
	@RequestMapping(value = "/recordbranch/receive", method = RequestMethod.GET)
	public String receive(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordBranchReceive> list = recordBranchService.getAllRecordBranchReceives_ACL(username,
				STAGE.RMT_RECORD_RMT_CS_BRANCH_RECEIVE.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordBranchReceiveList";
	}

	@RequestMapping(value = "/recordbranch/receive", method = RequestMethod.POST)
	public String receiveAction(
			@ModelAttribute("filterRecordForm") FilterRecordForm form,
			BindingResult result,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "reasonDesc", required = false) String reasonDesc,
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
						if (ACTIONS.RMT_CS_BRANCH_DELIVER.name.equalsIgnoreCase(btnSubmit)) {
							recordBranchService.callInvolve(request, ACTIONS.RMT_CS_BRANCH_DELIVER.toString(), username,
							STAGE.RMT_RECORD_RMT_CS_BRANCH_RECEIVE.getName(), Integer.parseInt(id));

						} else if (ACTIONS.RMT_CS_BRANCH_RETURN.name.equalsIgnoreCase(btnSubmit)) {
							recordBranchService.callInvolve(request, ACTIONS.RMT_CS_BRANCH_RETURN.toString(), username,
							STAGE.RMT_RECORD_RMT_CS_BRANCH_RECEIVE.getName(), Integer.parseInt(id), reason, reasonDesc);
						}
					}

				}
			} catch (Exception e) {
				logger.error("receiveAction has error message=" + e.getMessage());
			}
			return "redirect:/recordbranch/receive.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/recordbranch/receive.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordBranchReceive> list = recordBranchService.getAllRecordBranchReceives_ACL(username,
					STAGE.RMT_RECORD_RMT_CS_BRANCH_RECEIVE.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordBranchReceiveList";
		}
	}

	@RequestMapping(value = "/recordbranch/receive_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model receiveDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordBranchReceive record = recordBranchService.getRecordBranchReceiveById(id);
		model.addAttribute("record", record);
		return model;
	}
	/****** END Loan Kit Receive At Branch (STAGE = RMT_RECORD/RMT_CS_BRANCH_RECEIVE) ******/
	
	/****** Loan Kit Return From Branch (STAGE = RMT_RECORD/RMT_CS_BRANCH_RETURN) ******/
	@RequestMapping(value = "/recordbranch/return", method = RequestMethod.GET)
	public String returnList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordBranchReturn> list = recordBranchService.getAllRecordBranchReturns_ACL(username,
				STAGE.RMT_RECORD_RMT_CS_BRANCH_RETURN.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordBranchReturnList";
	}

	@RequestMapping(value = "/recordbranch/return", method = RequestMethod.POST)
	public String returnAction(
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
					recordBranchService.callInvolve(request, 
					ACTIONS.RMT_CS_RECEIVE_FROM_BRANCH_RETURN
							.toString(), username,
					STAGE.RMT_RECORD_RMT_CS_BRANCH_RETURN.getName(), Integer.parseInt(id));
				}
			} catch (Exception e) {
				logger.error("returnAction has error message=" + e.getMessage());
			}
			return "redirect:/recordbranch/return.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/recordbranch/return.html";
		} else {
			// get by filter
			Map<String, Object> filters = buildFilter(form);
			List<RecordBranchReturn> list = recordBranchService.getAllRecordBranchReturns_ACL(username,
					STAGE.RMT_RECORD_RMT_CS_BRANCH_RETURN.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RecordBranchReturnList";
		}
	}

	@RequestMapping(value = "/recordbranch/return_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model returnDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordBranchReturn record = recordBranchService.getRecordBranchReturnById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Return From Branch (STAGE = RMT_RECORD/RMT_CS_BRANCH_RETURN) ******/
	
	/****** Loan Kit Delivered (STAGE = RMT_RECORD/RMT_CS_BRANCH_DELIVER) ******/
	@RequestMapping(value = "/recordbranch/deliver", method = RequestMethod.GET)
	public String deliverList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordBranchDeliver> list = recordBranchService.getAllRecordBranchDelivers_ACL(username,
				STAGE.RMT_RECORD_RMT_CS_BRANCH_RETURN.getName(), filters);

		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RecordBranchDeliverList";
	}

	@RequestMapping(value = "/recordbranch/deliver_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model deliverDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordBranchDeliver record = recordBranchService.getRecordBranchDeliverById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Loan Kit Delivered (STAGE = RMT_RECORD/RMT_CS_BRANCH_DELIVER) ******/
}
