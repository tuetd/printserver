package com.pruvn.rms.dto;

public class IBMCMDto implements java.io.Serializable {
	/**
	 * Default serial version
	 */
	private String docRefId;
	private String agreementNo;
	public String getDocRefId() {
		return docRefId;
	}
	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	
}
