package com.pruvn.rms.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.pruvn.rms.domain.FollowUp;
import com.pruvn.rms.domain.RecordFL;
import com.pruvn.rms.domain.RecordFL1;
import com.pruvn.rms.domain.RecordFL2;
import com.pruvn.rms.domain.RecordSuspense;
import com.pruvn.rms.domain.RecordWait;
import com.pruvn.rms.dto.DataTablesDto;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.service.LoanService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.FollowUpStatus;
import com.pruvn.rms.utils.Constant.STAGE;

@Controller
public class LoanController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(LoanController.class);

	private LoanService loanService;

	/**
	 * @return the LoanService
	 */
	public LoanService getLoanService() {
		return loanService;
	}

	/**
	 * @param LoanService
	 *            the LoanService to set
	 */
	@Autowired
	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	/****** Disbursal Loan (STAGE = FRESH_LOAN) ******/
	@RequestMapping(value = "/loan/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordFL> list = loanService.getAllRecords_ACL(username,
				STAGE.FRESH_LOAN.getName(), filters);
		
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "FreshLoanList";
	}



	@RequestMapping(value = "/loan/index", method = RequestMethod.POST)
	public String sendListToRMT(
			@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result, 
			@RequestParam(value = "note", required = false) String note, Model model,
			HttpServletRequest request,	HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			String btnSubmit = request.getParameter("btnSubmit");
			if(!CommonUtils.isNullOrEmpty(btnSubmit)){
				for (String id : ids) {
					try {
						if ("Send to RMT".equalsIgnoreCase(btnSubmit)) {
							loanService.callInvolve(request, ACTIONS.SEND_TO_RMT.toString(),
							username, STAGE.FRESH_LOAN.getName(),Integer.parseInt(id));
						} else if ("Mark as send to branch".equalsIgnoreCase(btnSubmit)) {
							loanService.callInvolve(request, ACTIONS.MARK_AS_SEND_TO_BRANCH.toString(), username,
							STAGE.FRESH_LOAN.getName(), Integer.parseInt(id), note + " ");
						} else if ("Suspense".equalsIgnoreCase(btnSubmit)) {
							loanService.callInvolve(request, ACTIONS.RMT_SUSPENSE.toString(), username,
							STAGE.FRESH_LOAN.getName(), Integer.parseInt(id), note + " ");
						}
					} catch (Exception e) {

					}
				}
			}
			return "redirect:/loan/index.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/loan/index.html";
		} else {
			// get by filter
			Map<String, Object> map = buildFilter(form);
			List<RecordFL> list = loanService.getAllRecords_ACL(username,
					STAGE.FRESH_LOAN.getName(), map);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "FreshLoanList";
		}
	}

	

	@RequestMapping(value = "/loan/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		RecordFL record = loanService.getRecordFLById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Disbursal Loan ******/

	/**** Disbursal Loan RMT (STAGE = FRESH_LOAN/SEND_TO_RMT) ****/
	@RequestMapping(value = "/loan/rmt", method = RequestMethod.GET)
	public String rmtLoanTList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordFL1> list = loanService.getAllRecordsToRMT_ACL(username,
				STAGE.FRESH_LOAN_SEND_TO_RMT.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RMTLoanList";
	}

	@RequestMapping(value = "/loan/rmt", method = RequestMethod.POST)
	public String actionListToRMT(@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "reasonDesc", required = false) String reasonDesc,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		String btnSubmit = request.getParameter("btnSubmit");
		if(!CommonUtils.isNullOrEmpty(btnSubmit)){
			for (String id : ids) {
				try {
					if (ACTIONS.RMT_RECEIVE_ACK.name.equalsIgnoreCase(btnSubmit)) {
						loanService.callInvolve(request, 
								ACTIONS.RMT_RECEIVE_ACK.toString(),
								username, STAGE.FRESH_LOAN_SEND_TO_RMT.getName(),
								Integer.parseInt(id));
					} else if (ACTIONS.RMT_RETURN_TO_BN.name.equalsIgnoreCase(btnSubmit)) {
						 loanService.callInvolve(request, 
						 ACTIONS.RMT_RETURN_TO_BN.toString(),
						 username, STAGE.FRESH_LOAN_SEND_TO_RMT.getName(),
						 Integer.parseInt(id), reason, reasonDesc);
					}
				} catch (Exception e) {
					logger.error("actionListToRMT error is: " + e.getMessage());
				}
			}			
			return "redirect:/loan/rmt.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/loan/rmt.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<RecordFL1> list = loanService.getAllRecordsToRMT_ACL(username,
					STAGE.FRESH_LOAN_SEND_TO_RMT.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RMTLoanList";
		}
		
	}

	@RequestMapping(value = "/loan/detail_rmt", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailRMT(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordFL1 record = loanService.getRecordRMTById(id);
		model.addAttribute("record", record);
		return model;
	}

	/**** END Disbursal Loan RMT ****/

	/**** RMT Loan Receive (STAGE2 = FRESH_LOAN/RMT_RECEIVE) ****/
	@RequestMapping(value = "/loan/rmtReceive", method = RequestMethod.GET)
	public String rmtReceiveList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordFL2> list = loanService.getAllRecordsReceivedRMT_ACL(
				username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RMTReceiveList";
	}

	@RequestMapping(value = "/loan/rmtReceive", method = RequestMethod.POST)
	public String verifyListRecord(@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result, 
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "description", required = false) String description,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		String btnSubmit = request.getParameter("btnSubmit");
		if(!CommonUtils.isNullOrEmpty(btnSubmit)){
			for (String id : ids) {
				try {
					int recordId = Integer.parseInt(id);
					if (ACTIONS.PREPARE_TO_SCAN.name.equalsIgnoreCase(btnSubmit)) {
						loanService.callInvolve(request, ACTIONS.PREPARE_TO_SCAN.toString(),
								username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), recordId);
					/*} else if ("Return".equalsIgnoreCase(btnSubmit)) {
						 loanService.callInvolve(ACTIONS.RMT_RETURN_TO_BN.toString(),
						 username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), recordId, reason, reasonDesc);*/
					} else if ("Wait".equalsIgnoreCase(btnSubmit)) {
						 loanService.callInvolve(request, ACTIONS.RMT_WAIT_LOAN.toString(),
						 username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), recordId, category, description);
						 saveFollowUpSubmit(recordId, category, description, request, response);
					} else if("Save".equalsIgnoreCase(btnSubmit)) {
						String cat = request.getParameter("category" + id);
						String note = request.getParameter("textnote" + id);
						if(CommonUtils.isNullOrEmpty(cat)){
							loanService.callInvolve(request, ACTIONS.PREPARE_TO_SCAN.toString(),
									username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), recordId);
						} else {
							loanService.callInvolve(request, ACTIONS.RMT_WAIT_LOAN.toString(),
									 username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), recordId, cat, note);
							 saveFollowUpSubmit(recordId, cat, note, request, response);
						}
						//System.out.println(note);					
					}
				} catch (Exception e) {
					logger.error("verifyListRecord error is: " + e.getMessage());
				}
			}
			return "redirect:/loan/rmtReceive.html";
		}else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/loan/rmtReceive.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<RecordFL2> list = loanService.getAllRecordsReceivedRMT_ACL(
					username, STAGE.FRESH_LOAN_RMT_RECEIVE.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "RMTReceiveList";
			
		}
		
		
	}

	@RequestMapping(value = "/loan/detail_rmtReceive", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailRMTReceive(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordFL2 record = loanService.getRecordRMTRecieveById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Loan Receive ****/

	
	/**** Loan Return From RMT (STAGE = FRESH_LOAN/RMT_RETURN) ****/
	/*@RequestMapping(value = "/loan/rmtReturn", method = RequestMethod.GET)
	public String rmtReturnList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordFL3> list = loanService.getAllRecordsReturnRMT_ACL(
				username, STAGE.FRESH_LOAN_RMT_RETURN.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "RMTReturnList";
	}

	@RequestMapping(value = "/loan/rmtReturn", method = RequestMethod.POST)
	public String receiveListRecord(@ModelAttribute("filterRecordForm") FilterRecordForm filterRecordForm,
			BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		String btnSubmit = request.getParameter("btnSubmit");
		if(!CommonUtils.isNullOrEmpty(btnSubmit)){
			for (String id : ids) {
				try {
					loanService.callInvolve(
							ACTIONS.BN_RECEIVE_ACK.toString(), username,
							STAGE.FRESH_LOAN_RMT_RETURN.getName(),
							Integer.parseInt(id));

				} catch (Exception e) {
					logger.error("receiveListRecord error is: " + e.getMessage());
				}
			}
			return "redirect:/loan/rmtReturn.html";
	
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/loan/rmtReturn.html";
		} else {
			Map<String, Object> filters = buildFilter(filterRecordForm);
			List<RecordFL3> list = loanService.getAllRecordsReturnRMT_ACL(
					username, STAGE.FRESH_LOAN_RMT_RETURN.getName(), filters);
			model.addAttribute(filterRecordForm);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			
			return "RMTReturnList";
		}
	}

	@RequestMapping(value = "/loan/detail_rmtReturn", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailRMTReturn(@RequestParam(value = "id") Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordFL3 record = loanService.getRecordRMTReturnById(id);
		model.addAttribute("record", record);
		return model;
	}*/
	/**** END Loan Return From RMT ****/
	
	
	/**** Loan Waiting (STAGE = FRESH_LOAN/RMT_WAIT_LOAN) ****/
	@RequestMapping(value = "/loan/wait", method = RequestMethod.GET)
	public String loanWaitingList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordWait> list = loanService.getAllRecordWait_ACL(
				username, STAGE.FRESH_LOAN_RMT_WAIT_LOAN.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "LoanWaitList";
	}

	@RequestMapping(value = "/loan/wait", method = RequestMethod.POST)
	public String loanWaitingListAction(@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result, 
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam(value = "reasonDesc", required = false) String reasonDesc,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		String btnSubmit = request.getParameter("btnSubmit");
		if(!CommonUtils.isNullOrEmpty(btnSubmit)){
			for (String id : ids) {
				try {
					if (ACTIONS.PREPARE_TO_SCAN.name.equalsIgnoreCase(btnSubmit)) {
						loanService.callInvolve(request, 
								ACTIONS.PREPARE_TO_SCAN.toString(),
								username, STAGE.FRESH_LOAN_RMT_WAIT_LOAN.getName(),
								Integer.parseInt(id));
					}
				} catch (Exception e) {
					logger.error("loanWaitingListAction error is: " + e.getMessage());
				}
			}
			return "redirect:/loan/wait.html";
		}else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/loan/wait.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<RecordWait> list = loanService.getAllRecordWait_ACL(
					username, STAGE.FRESH_LOAN_RMT_WAIT_LOAN.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "LoanWaitList";
			
		}
		
		
	}

	@RequestMapping(value = "/loan/wait_detail", method = RequestMethod.GET)
	@ResponseBody
	public Model loanWaitingDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordWait record = loanService.getRecordWaitById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Loan Waiting ****/
	
	private UserMService userMService;
	/**
	 * @return the userMService
	 */
	public UserMService getUserMService() {
		return userMService;
	}

	/**
	 * @param userMService the userMService to set
	 */
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}
	
	/**** BEGIN Follow Up List ****/
	@RequestMapping(value = "/loan/followUp", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesDto<FollowUp> followUpDetails(
			@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam(required=false) Integer sSearch,
			@RequestParam Integer recordId,
            Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach LoanController$followUpDetails...");
		
		DataTablesDto<FollowUp> dt = new DataTablesDto<FollowUp>();

		RecordWait record = loanService.getRecordWaitById(recordId);
		List<FollowUp> list = null;
		if(record != null) {
			list = loanService.getFollowUpsByRecordId(recordId);
		}
		dt.setAaData(list);
		dt.setiTotalRecords(list.size());
		dt.setiTotalDisplayRecords(list.size());
		dt.setsEcho(sEcho);
		model.addAttribute("recordId", recordId);
		return dt;
	}
	
	@RequestMapping(value = "/loan/followUpMod", method = RequestMethod.POST)
	@ResponseBody
	public String saveFollowUpSubmit(
			@RequestParam Integer recordId,
			@RequestParam(required=false) String category,
			@RequestParam(required=false) String description,
			HttpServletRequest request,
			HttpServletResponse response)  throws Exception
	{
		
		FollowUp followUp = new FollowUp();
		followUp.setRecordId(recordId);
		followUp.setCategory(category);
		followUp.setDescription(description);
		followUp.setCreateDate(Calendar.getInstance().getTime());
		followUp.setCreater(SecurityContextHolder.getContext().getAuthentication().getName());
		followUp.setStatus(FollowUpStatus.INITIAL.getName());
		
		loanService.saveFollowUp(followUp);
		
		return "success";
	}
	
	@RequestMapping(value = "/loan/updateFollowUp", method = RequestMethod.POST)
	@ResponseBody
	public String updateFollowUp(
			@RequestParam Integer followUpId,
			@RequestParam String status,
			@RequestParam Integer recordId,
			HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		List<FollowUp> list = new ArrayList<FollowUp>();
		if(followUpId == 0) {
			list = loanService.getFollowUpsByRecordId(recordId);
		} else {
			FollowUp followUp = loanService.getFollowUpById(followUpId);
			if(followUp != null) {
				list.add(followUp);
			}
		}
		for (FollowUp fu : list) {
			if(fu != null) {
				fu.setStatus(status);
				fu.setUpdateDate(Calendar.getInstance().getTime());
				fu.setUpdater(SecurityContextHolder.getContext().getAuthentication().getName());
				loanService.saveFollowUp(fu);
			}
		}
		
		return "success";
	}
	
	/**** END Follow Up List ****/
	
	
	/**** Suspense List (STAGE = FRESH_LOAN/SUSPENSE) ****/
	@RequestMapping(value = "/loan/suspense", method = RequestMethod.GET)
	public String suspenseList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<RecordSuspense> list = loanService.getAllRecordSuspense_ACL(username,
				STAGE.FRESH_LOAN_SUSPENSE.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "SuspenseList";
	}

	@RequestMapping(value = "/loan/suspense", method = RequestMethod.POST)
	public String actionSuspenseList(@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result,
			@RequestParam(value = "note", required = false) String note, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");		
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		String btnSubmit = request.getParameter("btnSubmit");
		if(!CommonUtils.isNullOrEmpty(btnSubmit)){
			for (String id : ids) {
				try {
					if (ACTIONS.RECOVER_LOAN.name.equalsIgnoreCase(btnSubmit)) {
						 loanService.callInvolve(request, 
						 ACTIONS.RECOVER_LOAN.toString(),
						 username, STAGE.FRESH_LOAN_SUSPENSE.getName(),
						 Integer.parseInt(id), note);
					}
				} catch (Exception e) {
					logger.error("actionSuspenseList error is: " + e.getMessage());
				}
			}			
			return "redirect:/loan/suspense.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/loan/suspense.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<RecordSuspense> list = loanService.getAllRecordSuspense_ACL(username,
					STAGE.FRESH_LOAN_SUSPENSE.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "SuspenseList";
		}
		
	}

	@RequestMapping(value = "/loan/detail_suspense", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailSuspense(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		RecordSuspense record = loanService.getRecordSuspenseById(id);
		model.addAttribute("record", record);
		return model;
	}

	/**** END Suspense List (STAGE = FRESH_LOAN/SUSPENSE)  ****/
	
	
	
	
}
