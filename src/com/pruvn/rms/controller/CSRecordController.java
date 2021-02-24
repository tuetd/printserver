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

import com.pruvn.rms.domain.CSLogTransaction;
import com.pruvn.rms.domain.CSRecordFL;
import com.pruvn.rms.domain.CSRecordRMT;
import com.pruvn.rms.domain.CSRecordRMTReceive;
import com.pruvn.rms.domain.LogTransaction;
import com.pruvn.rms.dto.DataTablesDto;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.service.CreditShieldService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.CS_STAGE;
import com.pruvn.rms.validator.FileExcelUploadValidator;

@Controller
public class CSRecordController extends BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(CSRecordController.class);

	private FileExcelUploadValidator fileUploadValidator;
	
	private CreditShieldService creditShieldService;

	public CreditShieldService getCreditShieldService() {
		return creditShieldService;
	}

	@Autowired
	public void setCreditShieldService(CreditShieldService creditShieldService) {
		this.creditShieldService = creditShieldService;
	}

	
	
	public FileExcelUploadValidator getFileExcelUploadValidator() {
		return fileUploadValidator;
	}
	
	@Autowired
	public void setFileExcelUploadValidator(FileExcelUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}
	
	
	/****** Disbursal Loan (STAGE = FRESH_LOAN) ******/
	@RequestMapping(value = "/cs/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach RecordController$recordlist...");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordFL> list = creditShieldService.getAllCSRecordFL_ACL(username,
				CS_STAGE.CREDIT_SHIELD.getName(), filters);
		
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "CreditShieldList";
	}



	@RequestMapping(value = "/cs/index", method = RequestMethod.POST)
	public String sendListToRMT(
			@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result, Model model,
			HttpServletRequest request,	HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (request.getParameter("btnSubmit") != null) {
			String btnSubmit = request.getParameter("btnSubmit");
			if(!CommonUtils.isNullOrEmpty(btnSubmit)){
				for (String id : ids) {
					try {
						creditShieldService.callInvolve(request, ACTIONS.SEND_CS_TO_RMT.toString(),
						username, CS_STAGE.CREDIT_SHIELD.getName(),Integer.parseInt(id));
					} catch (Exception e) {
					}
				}
			}
			return "redirect:/cs/index.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/cs/index.html";
		} else {
			// get by filter
			Map<String, Object> map = buildFilter(form);
			List<CSRecordFL> list = creditShieldService.getAllCSRecordFL_ACL(username,
					CS_STAGE.CREDIT_SHIELD.getName(), map);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "CreditShieldList";
		}
	}

	

	@RequestMapping(value = "/cs/detail", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetail(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach CSRecordController$recordlist...");
		CSRecordFL record = creditShieldService.getCSRecordFLById(id);
		model.addAttribute("record", record);
		return model;
	}

	/****** END Disbursal Loan ******/
	
	
	/**** Disbursal Loan RMT (STAGE = FRESH_LOAN/SEND_TO_RMT) ****/
	@RequestMapping(value = "/cs/rmt", method = RequestMethod.GET)
	public String rmtLoanTList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordRMT> list = creditShieldService.getAllCSRecordRMT_ACL(username,
				CS_STAGE.CREDIT_SHIELD_SEND_TO_RMT.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "CreditShieldAtRMTList";
	}

	@RequestMapping(value = "/cs/rmt", method = RequestMethod.POST)
	public String actionListToRMT(@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("checkbox");

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		String btnSubmit = request.getParameter("btnSubmit");
		if(!CommonUtils.isNullOrEmpty(btnSubmit)){
			for (String id : ids) {
				try {
					if (ACTIONS.RMT_RECEIVE_CS.name.equalsIgnoreCase(btnSubmit)) {
						creditShieldService.callInvolve(request, 
								ACTIONS.RMT_RECEIVE_CS.toString(),
								username, CS_STAGE.CREDIT_SHIELD_SEND_TO_RMT.getName(),
								Integer.parseInt(id));
					} 
					/*else if (ACTIONS.RMT_RETURN_TO_BN.name.equalsIgnoreCase(btnSubmit)) {
						 creditShieldService.callInvolve(
						 ACTIONS.RMT_RETURN_TO_BN.toString(),
						 username, STAGE.FRESH_LOAN_SEND_TO_RMT.getName(),
						 Integer.parseInt(id), reason, reasonDesc);
					}*/
				} catch (Exception e) {
					logger.error("actionListToRMT error is: " + e.getMessage());
				}
			}			
			return "redirect:/cs/rmt.html";
		} else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/cs/rmt.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordRMT> list = creditShieldService.getAllCSRecordRMT_ACL(username,
					CS_STAGE.CREDIT_SHIELD_SEND_TO_RMT.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "CreditShieldAtRMTList";
		}
		
	}

	@RequestMapping(value = "/cs/detail_rmt", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailRMT(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordRMT record = creditShieldService.getCSRecordRMTById(id);
		model.addAttribute("record", record);
		return model;
	}

	/**** END Disbursal Loan RMT ****/

	/**** RMT Loan Receive (STAGE = FRESH_LOAN/RMT_RECEIVE) ****/
	@RequestMapping(value = "/cs/rmtReceive", method = RequestMethod.GET)
	public String rmtReceiveList(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		FilterRecordForm form = createFilterForm(request);
		Map<String, Object> filters = buildFilter(form);
		List<CSRecordRMTReceive> list = creditShieldService.getAllRecordRMTReceived_ACL(
				username, CS_STAGE.CREDIT_SHIELD_RMT_RECEIVE.getName(), filters);
		model.addAttribute(form);
		model.addAttribute("recordList", list);
		model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
		return "CreditShieldReceiveList";
	}

	@RequestMapping(value = "/cs/rmtReceive", method = RequestMethod.POST)
	public String verifyListRecord(@ModelAttribute("filterRecordForm") FilterRecordForm form, BindingResult result, 
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
					if (ACTIONS.SEND_TO_PVN.name.equalsIgnoreCase(btnSubmit)) {
						creditShieldService.callInvolve(request, ACTIONS.SEND_TO_PVN.toString(),
								username, CS_STAGE.CREDIT_SHIELD_RMT_RECEIVE.getName(), recordId);
					} 
				} catch (Exception e) {
					logger.error("verifyListRecord error is: " + e.getMessage());
				}
			}
			return "redirect:/cs/rmtReceive.html";
		}else if (request.getParameter("btnFilter") != null
				&& request.getParameter("btnFilter").equals("clear")) {
			return "redirect:/cs/rmtReceive.html";
		} else {
			Map<String, Object> filters = buildFilter(form);
			List<CSRecordRMTReceive> list = creditShieldService.getAllRecordRMTReceived_ACL(
					username, CS_STAGE.CREDIT_SHIELD_RMT_RECEIVE.getName(), filters);
			model.addAttribute(form);
			model.addAttribute("recordList", list);
			model.addAttribute("pageSize", form.getPageSize() != 0 ? form.getPageSize() : list.size());
			return "CreditShieldReceiveList";
			
		}
		
		
	}

	@RequestMapping(value = "/cs/detail_rmtReceive", method = RequestMethod.GET)
	@ResponseBody
	public Model viewDetailRMTReceive(
			@RequestParam(value = "id", required = false) Integer id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		CSRecordRMTReceive record = creditShieldService.getCSRecordRMTReceiveById(id);
		model.addAttribute("record", record);
		return model;
	}
	/**** END Credit Shield Receive ****/

	@RequestMapping(value = "/csrecord/logs", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesDto<CSLogTransaction> getLogTransactions(
			@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam(required=false) Integer sSearch,
			@RequestParam Integer recordId,
            Model model, HttpServletRequest request, HttpServletResponse response)
	{
		DataTablesDto<CSLogTransaction> dt = new DataTablesDto<CSLogTransaction>();
		List<CSLogTransaction> logTransactions = creditShieldService
				.getCSLogTransactionsByCSRecordId(recordId);
		for (CSLogTransaction logTransaction : logTransactions) {
			try {
				logTransaction.setAction(Constant.ACTIONS.valueOf(logTransaction.getAction()).getName());
			} catch (Exception ex) {
				logTransaction.setAction(logTransaction.getAction() + " not define.");
			}
		}
		dt.setAaData(logTransactions);
		dt.setiTotalRecords(logTransactions.size());
		dt.setiTotalDisplayRecords(logTransactions.size());
		dt.setsEcho(sEcho);
		model.addAttribute("recordId", recordId);
		return dt;
	}

}
