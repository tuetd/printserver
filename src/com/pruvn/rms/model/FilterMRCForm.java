package com.pruvn.rms.model;


public class FilterMRCForm {
	private String id;
	private String loanNo;
	private String dateSend;
	private int pageSize;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLoanNo() {
		return loanNo;
	}
	
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getDateSend() {
		return dateSend;
	}
	public void setDateSend(String dateSend) {
		this.dateSend = dateSend;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
