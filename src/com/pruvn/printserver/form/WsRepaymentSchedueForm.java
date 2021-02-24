package com.pruvn.printserver.form;

import java.util.List;

public class WsRepaymentSchedueForm {
	private String Result_Code;
	private String Result_Msg;
	private String APP_ID_C;
	private String RO_NAME;
	private String LOANNO;
	private String LOANAMOUNT;
	private String CUSTOMERNAME;
	private String PAN_NO;
	private String PAN_NO_ISSUE_DATE;
	private String TOTALINSTALLMENT;
	private List<RepaymentPLForm> listpaymet;
	private String TENURE;
	public String getAPP_ID_C() {
		return APP_ID_C;
	}
	public void setAPP_ID_C(String aPP_ID_C) {
		APP_ID_C = aPP_ID_C;
	}
	public String getRO_NAME() {
		return RO_NAME;
	}
	public void setRO_NAME(String rO_NAME) {
		RO_NAME = rO_NAME;
	}
	public String getLOANNO() {
		return LOANNO;
	}
	public void setLOANNO(String lOANNO) {
		LOANNO = lOANNO;
	}
	public String getLOANAMOUNT() {
		return LOANAMOUNT;
	}
	public void setLOANAMOUNT(String lOANAMOUNT) {
		LOANAMOUNT = lOANAMOUNT;
	}
	public String getCUSTOMERNAME() {
		return CUSTOMERNAME;
	}
	public void setCUSTOMERNAME(String cUSTOMERNAME) {
		CUSTOMERNAME = cUSTOMERNAME;
	}
	public String getPAN_NO() {
		return PAN_NO;
	}
	public void setPAN_NO(String pAN_NO) {
		PAN_NO = pAN_NO;
	}
	public String getPAN_NO_ISSUE_DATE() {
		return PAN_NO_ISSUE_DATE;
	}
	public void setPAN_NO_ISSUE_DATE(String pAN_NO_ISSUE_DATE) {
		PAN_NO_ISSUE_DATE = pAN_NO_ISSUE_DATE;
	}
	
	public List<RepaymentPLForm> getListpaymet() {
		return listpaymet;
	}
	public void setListpaymet(List<RepaymentPLForm> listpaymet) {
		this.listpaymet = listpaymet;
	}
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
	public String getTOTALINSTALLMENT() {
		return TOTALINSTALLMENT;
	}
	public void setTOTALINSTALLMENT(String tOTALINSTALLMENT) {
		TOTALINSTALLMENT = tOTALINSTALLMENT;
	}
	public String getTENURE() {
		return TENURE;
	}
	public void setTENURE(String tENURE) {
		TENURE = tENURE;
	}
	

}
