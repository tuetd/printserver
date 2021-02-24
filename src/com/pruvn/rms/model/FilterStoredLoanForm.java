package com.pruvn.rms.model;


public class FilterStoredLoanForm {
	private String id;
	private String loanNo;
	private String barCode;
	private String nameBox;
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
	
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getNameBox() {
		return nameBox;
	}
	public void setNameBox(String nameBox) {
		this.nameBox = nameBox;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
