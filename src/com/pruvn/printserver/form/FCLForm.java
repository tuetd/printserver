package com.pruvn.printserver.form;

import java.util.Date;

public class FCLForm {
	private String agreementno;
	private int instlnum;
	private Date duedate;
	private Date startIntDate;
	public String getAgreementno() {
		return agreementno;
	}
	public void setAgreementno(String agreementno) {
		this.agreementno = agreementno;
	}
	public int getInstlnum() {
		return instlnum;
	}
	public void setInstlnum(int instlnum) {
		this.instlnum = instlnum;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getStartIntDate() {
		return startIntDate;
	}
	public void setStartIntDate(Date startIntDate) {
		this.startIntDate = startIntDate;
	}

}
