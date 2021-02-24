package com.pruvn.rms.service.response;

import java.util.ArrayList;
import java.util.List;

import com.pruvn.rms.domain.StoredLoan;

public class UploadStoredLoanResponse {

	private List<StoredLoan> successList = new ArrayList<StoredLoan>();
	private List<StoredLoan> existList  = new ArrayList<StoredLoan>();
	public List<StoredLoan> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<StoredLoan> successList) {
		this.successList = successList;
	}
	public List<StoredLoan> getExistList() {
		return existList;
	}
	public void setExistList(List<StoredLoan> existList) {
		this.existList = existList;
	}
	
	
}
