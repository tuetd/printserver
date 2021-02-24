package com.pruvn.printserver.form;

import java.util.List;

public class WsRejectLetterForm {
	private String Result_Code;
	private String Result_Msg;
	private List<RejectLetterForm> lstReject;
	
	
	public String getResult_Code() {
		return Result_Code;
	}
	public void setResult_Code(String result_Code) {
		Result_Code = result_Code;
	}
	public String getResult_Msg() {
		return Result_Msg;
	}
	public void setResult_Msg(String result_Msg) {
		Result_Msg = result_Msg;
	}
	public List<RejectLetterForm> getLstReject() {
		return lstReject;
	}
	public void setLstReject(List<RejectLetterForm> lstReject) {
		this.lstReject = lstReject;
	}
	

}
