package com.pruvn.rms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.StringUtils;
import com.pruvn.rms.model.AutoDebitForm;
import com.pruvn.rms.model.FilterRecordForm;
import com.pruvn.rms.service.AutoDebitService;
import com.pruvn.rms.service.BranchService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;

@Controller
public class BaseRecordController {
	private static final Logger logger = Logger
			.getLogger(BaseRecordController.class);
	//protected String curStage;
	
	private BranchService branchService;

	private AutoDebitService autoDebitServiceNew;
	

	/**
	 * @return the BranchService
	 */
	public BranchService getBranchService() {
		return branchService;
	}

	/**
	 * @param BranchService
	 *            the BranchService to set
	 */
	@Autowired
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	
	



	public AutoDebitService getAutoDebitServiceNew() {
		return autoDebitServiceNew;
	}
	@Autowired
	public void setAutoDebitServiceNew(AutoDebitService autoDebitServiceNew) {
		this.autoDebitServiceNew = autoDebitServiceNew;
	}





	protected String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	protected FilterRecordForm createFilterForm(HttpServletRequest request) {
		FilterRecordForm form = new FilterRecordForm();
		form.setId(request.getParameter("id"));
		form.setFromDate(request.getParameter("fromDate"));
		form.setToDate(request.getParameter("toDate"));
		form.setAgreementNo(request.getParameter("agreementNo"));
		form.setBranch(request.getParameter("branch"));
		form.setAppFormNo(request.getParameter("appFormNo"));
		form.setSendDate(request.getParameter("sendDate"));
		form.setSender(request.getParameter("sender"));
		form.setArea(request.getParameter("area"));
		if(!CommonUtils.isNullOrEmpty(request.getParameter("pageSize"))){
			form.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		} else {
			form.setPageSize(Constant.PAGE_SIZE);
		}
		form.setBranchs(branchService.findAll());
		return form;
	}
	
	protected AutoDebitForm createFilterADForm(HttpServletRequest request) {
		AutoDebitForm form = new AutoDebitForm();	
		form.setId(request.getParameter("id"));
		form.setBankCode(request.getParameter("bankCode"));
		form.setBranchDesc(request.getParameter("branchDesc"));
		form.setCustomerName(request.getParameter("customerName"));
		form.setAuthorizedDate(request.getParameter("authorizedDate"));
		form.setDisbursalDate(request.getParameter("disbursalDate"));
		form.setFirstDueDate(request.getParameter("firstDueDate"));
		form.setSendDate(request.getParameter("sendDate"));
		form.setLoanNo(request.getParameter("loanNo"));
		form.setRoName(request.getParameter("roName"));
		form.setStatus(request.getParameter("status"));
		form.setReason(request.getParameter("reason"));
		form.setTypeAutoDebit(request.getParameter("typeAutoDebit"));
		if(!StringUtils.isNullOrEmpty(request.getParameter("pageSize"))){
			form.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		} else {
			form.setPageSize(Constant.PAGE_SIZE);
		}
		form.setAutodebitList(autoDebitServiceNew.findAll());
		return form;
	}
	
	protected Map<String, Object> buildADFilter(
			AutoDebitForm form) {
		Map<String, Object> filters = new HashMap<String, Object>();
		if (!CommonUtils.isNullOrEmpty(form.getId())) {
			filters.put("Id", form.getId());
		}
		if (!CommonUtils.isNullOrEmpty(form.getPageSize().toString())) {
			filters.put("PageSize", form.getPageSize());
		}		
		if (!CommonUtils.isNullOrEmpty(form.getAuthorizedDate())) {
			filters.put("AuthorizedDate", form.getAuthorizedDate());
		}

		if (!CommonUtils.isNullOrEmpty(form.getBankCode())){
			filters.put("BankCode", form.getBankCode());
		}

		if (!CommonUtils.isNullOrEmpty(form.getCustomerName())) {
			filters.put("CustomerName", form.getCustomerName());
		}

		if (!CommonUtils.isNullOrEmpty(form.getDisbursalDate())) {
			filters.put("DisbursalDate", form.getDisbursalDate());
		}

		if (!CommonUtils.isNullOrEmpty(form.getFirstDueDate())) {
			filters.put("FirstDueDate", form.getFirstDueDate());
		}
		if (!CommonUtils.isNullOrEmpty(form.getSendDate())) {
			filters.put("SendDate", form.getSendDate());
		}
		if (!CommonUtils.isNullOrEmpty(form.getLoanNo())) {
			filters.put("loanNo", form.getLoanNo());
		}
		if (!CommonUtils.isNullOrEmpty(form.getRoName())) {
			filters.put("roName", form.getRoName());
		}
		if (!CommonUtils.isNullOrEmpty(form.getStatus())) {
			filters.put("status", form.getStatus());
		}
		if (!CommonUtils.isNullOrEmpty(form.getReason())) {
			filters.put("reason", form.getReason());
		}
		if (!CommonUtils.isNullOrEmpty(form.getTypeAutoDebit())) {
			filters.put("TypeAutoDebit", form.getTypeAutoDebit());
		}
		if (!CommonUtils.isNullOrEmpty(form.getSendername())) {
			filters.put("sendername", form.getSendername());
		}
		if (!CommonUtils.isNullOrEmpty(form.getBranchDesc())) {
			filters.put("branchDesc", form.getBranchDesc());
		}
		form.setAutodebitList(autoDebitServiceNew.findAll());
		return filters;
	}
	
	protected Map<String, Object> buildFilter(
			FilterRecordForm form) {
		Map<String, Object> filters = new HashMap<String, Object>();
		if (!CommonUtils.isNullOrEmpty(form.getId())) {
			filters.put("Id", form.getId());
		}
		if (!CommonUtils.isNullOrEmpty(form.getFromDate())) {
			filters.put("FromDate", form.getFromDate());
		}

		if (!CommonUtils.isNullOrEmpty(form.getToDate())){
			filters.put("ToDate", form.getToDate());
		}

		if (!CommonUtils.isNullOrEmpty(form.getAgreementNo())) {
			filters.put("AgreementNo", form.getAgreementNo());
		}

		if (!CommonUtils.isNullOrEmpty(form.getBranch())) {
			filters.put("Branch", form.getBranch());
		}

		if (!CommonUtils.isNullOrEmpty(form.getAppFormNo())) {
			filters.put("AppFromNo", form.getAppFormNo());
		}
		if (!CommonUtils.isNullOrEmpty(form.getSendDate())) {
			filters.put("SendDate", form.getSendDate());
		}
		if (!CommonUtils.isNullOrEmpty(form.getSender())) {
			filters.put("Sender", form.getSender());
		}
		if (!CommonUtils.isNullOrEmpty(form.getArea())) {
			filters.put("Area", form.getArea());
		}
		form.setBranchs(branchService.findAll());
		return filters;
	}
}
