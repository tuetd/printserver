package com.pruvn.rms.model;

import java.util.List;

import com.pruvn.rms.domain.AutoDebit;



public class AutoDebitForm {
	
	private String Id;
	
	private String customerName;
		
	private String status;
		
	private String bankCode;
		
	private String typeAutoDebit;
		
	private String loanNo;
		
	private String roName;
	
	private String disbursalDate;
		
	private String firstDueDate;
		
	private String authorizedDate;
	
	private String reason;
	
	private String branchDesc;
	
	private Integer pageSize;
	
	private List<AutoDebit> autodebitList;
	
	private String sendername;
	
	private String sendDate;
	
	
	
	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getBranchDesc() {
		return branchDesc;
	}

	public void setBranchDesc(String branchDesc) {
		this.branchDesc = branchDesc;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTypeAutoDebit() {
		return typeAutoDebit;
	}

	public void setTypeAutoDebit(String typeAutoDebit) {
		this.typeAutoDebit = typeAutoDebit;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRoName() {
		return roName;
	}

	public void setRoName(String roName) {
		this.roName = roName;
	}
	
	
	public String getDisbursalDate() {
		return disbursalDate;
	}

	public void setDisbursalDate(String disbursalDate) {
		this.disbursalDate = disbursalDate;
	}

	public String getFirstDueDate() {
		return firstDueDate;
	}

	public void setFirstDueDate(String firstDueDate) {
		this.firstDueDate = firstDueDate;
	}

	public String getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<AutoDebit> getAutodebitList() {
		return autodebitList;
	}

	public void setAutodebitList(List<AutoDebit> autodebitList) {
		this.autodebitList = autodebitList;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
