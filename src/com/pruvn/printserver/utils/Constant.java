package com.pruvn.printserver.utils;


public class Constant {
	public static final String WEBSERVICE_CODE_SUCCESS = "00000";
	public static final String WEBSERVICE_CODE_ERROR = "99999";
	
	//DOC NAME:
	public static final String DOCNAME_CLOSERLETTER = "CloserLetter";
	public static final String DOCNAME_REJECTLETTER = "RejectLetter";
	public static final String DOCNAME_WELCOMELETTER = "Welcomeletter";
	public static final String DOCNAME_SUMMARY_VIOLATIONS = "Summaryviolations";
	public static final String DOCNAME_FEE_SCHEDULE = "FeeSchedule";
	public static final String DOCNAME_FEE_SCHEDULE_MGAGE = "FeeScheduleMgage";
	public static final String DOCNAME_CERTIFICATE = "Certificate";
	public static final String DOCNAME_AUTODEBITFORM = "AutodebitForm";
	public static final String DOCNAME_EARLYTERMINATION = "EarlyTermination";
	public static final String DOCNAME_TERMSCONDITIONS = "TermsConditions";
	public static final String DOCNAME_PAYMENTGUIDANCEANDACCOUNT = "PayGuiAndAccount";
	public static final String DOCNAME_EARLYTERMINATIONANDTRANSFERPAYMENT = "EarlyAndTransfer";
	public static final String DOCNAME_AGREEMENTMORTGAGE = "AgreementMgage";
	public static final String DOCNAME_HANDOVERRECEIPTMORTGAGE = "HandReceiptMgage";
	public static final String DOCNAME_PROPERTYINSURANCEMORTGAGE = "ProInsuranceMgage";
	public static final String DOCNAME_COLLATERALREGISTRATIONMORTGAGE = "RegistrationMgage";
	public static final String DOCNAME_HANDOVERMINUTECUSMORTGAGE = "HandMinuteMgage";
	public static final String DOCNAME_HANDOVERMINUTERECORDMORTGAGE = "HandRecordMgage";
	public static final String DOCNAME_CERTIFICATEMGAGE = "CertificateMgage";
	public static final String DOCNAME_REPAYMENTSCHEDUE = "RepaymentSchedue";
	//WEBSERVICE NAME
	public static final String WEBSERVICE_WS_CLOSURE_LETTER = "WS_CLOSURE_LETTER";
	public static final String WEBSERVICE_WS_REJECT_LETTER = "WS_REJECT_LETTER";
	public static final String WEBSERVICE_WS_WELCOME_LETTER = "WS_WELCOME_LETTER";
	public static final String WEBSERVICE_WS_CERTIFICATE = "WS_CERTIFICATE";
	public static final String WEBSERVICE_WS_AUTO_DEBIT_FORM = "WS_AUTO_DEBIT_FORM";
	public static final String WEBSERVICE_WS_ONT_FCL_REQUEST = "WS_ONT_FCL_REQUEST";
	public static final String WEBSERVICE_WS_GEN_DISB_REQ_TOPUP_V10 = "WS_GEN_DISB_REQ_TOPUP_V10";
	public static final String WEBSERVICE_WS_AGREEMENT_MGAGE = "WS_AGREEMENT_MGAGE";
	public static final String WEBSERVICE_WS_INSURANCE_MGAGE = "WS_INSURANCE_MGAGE";
	public static final String WEBSERVICE_WS_RECEIPT_MGAGE = "WS_RECEIPT_MGAGE";
	public static final String WEBSERVICE_WS_REGISTRATION_MGAGE = "WS_REGISTRATION_MGAGE";
	public static final String WEBSERVICE_WS_RECORD_MGAGE = "WS_RECORD_MGAGE";
	public static final String WEBSERVICE_WS_HANDOVER_DOC_MGAGE = "WS_HANDOVER_DOC_MGAGE";
	public static final String WEBSERVICE_WS_CERTIFICATE_MGAGE = "WS_CERTIFICATE_MGAGE";
	//
	public static final String SQLPARAMMASTER_FIELDTYPE_COMBOBOX = "COMBOBOX";
	public static final String SQLPARAMMASTER_DETAIL_REASON_FCL = "REASON_FCL";
	 public enum REASON_LOCK{
		 TIME_EXPIRED("Exceed the limit of the times logged in"),LOGIN_EXPIRED("Exceed in number of times logged in");
		 private String name;
		 private REASON_LOCK(String name){
			 this.name = name;
		 }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		 
	 }
	 public static String LOGIN = "LOGIN";
	 public static String LOGOUT = "LOGOUT";
	 public static String LOGIN_SUCCESS = "SUCCESS";
	 public static String LOGIN_FAIL = "FAIL";
}
