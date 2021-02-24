package com.pruvn.rms.model;

import java.util.List;

import com.pruvn.rms.domain.Branch;



public class FilterRecordForm {
	private String Id;
	private String agreementNo;
	private String branch;
	private String panNo;
	private String appFormNo;
	private String toDate;
	private String fromDate;
	private String sendDate;
	private String sender;
	private String area;
	private Integer pageSize;
	
	private List<Branch> branchs;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getAppFormNo() {
		return appFormNo;
	}
	public void setAppFormNo(String appFormNo) {
		this.appFormNo = appFormNo;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<Branch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}
	public Integer getPageSize() {
		return pageSize != null ? pageSize : 0;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}	
}
