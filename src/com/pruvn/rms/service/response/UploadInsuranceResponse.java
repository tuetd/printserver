package com.pruvn.rms.service.response;

import java.util.ArrayList;
import java.util.List;

import com.pruvn.rms.domain.Insurance;

public class UploadInsuranceResponse {

	private List<Insurance> successList = new ArrayList<Insurance>();
	private List<Insurance> existList  = new ArrayList<Insurance>();
	public List<Insurance> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<Insurance> successList) {
		this.successList = successList;
	}
	public List<Insurance> getExistList() {
		return existList;
	}
	public void setExistList(List<Insurance> existList) {
		this.existList = existList;
	}
	
	
}
