package com.pruvn.rms.service.response;

import java.util.ArrayList;
import java.util.List;

import com.pruvn.rms.domain.Foreclosure;

public class UploadForeclosureResponse {

	private List<Foreclosure> successList = new ArrayList<Foreclosure>();
	private List<Foreclosure> existList  = new ArrayList<Foreclosure>();
	public List<Foreclosure> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<Foreclosure> successList) {
		this.successList = successList;
	}
	public List<Foreclosure> getExistList() {
		return existList;
	}
	public void setExistList(List<Foreclosure> existList) {
		this.existList = existList;
	}
	
	
}
