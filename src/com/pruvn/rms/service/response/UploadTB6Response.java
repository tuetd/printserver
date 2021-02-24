package com.pruvn.rms.service.response;

import java.util.ArrayList;
import java.util.List;

import com.pruvn.rms.domain.TB6;

public class UploadTB6Response {

	private List<TB6> successList = new ArrayList<TB6>();
	private List<TB6> existList  = new ArrayList<TB6>();
	public List<TB6> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<TB6> successList) {
		this.successList = successList;
	}
	public List<TB6> getExistList() {
		return existList;
	}
	public void setExistList(List<TB6> existList) {
		this.existList = existList;
	}
	
	
}
