package com.pruvn.rms.utils;

public class Constant {
	public static final String RESET_MAIL_HEADER = "[RMS]New password from Record Management System Web";
	public static final String RESET_PWD_CONTENT = "Hello \"%s\", \n\nNew password of user \"%s\" was reset to \"%s\"";
	public static final String CREATE_USER_CONTENT = "Hello \"%s\", \n\nPassword of user \"%s\" was generated to \"%s\"";
	public static final String AUTOGENERATED_EMAIL_CONTENT = "\n\n\nThis is auto-generated email, do not reply";
	public static final String DOMAIN_NAME = "pru_vn";
	public static final String UTILITY_INIT_PASS = "abc123";
	public static final String ROLE_CM_PRINT_DOC = "ROLE_CM_PRINT_DOC";
	public static final String LOGIN="LOGIN";
	public static final String LOGOUT="LOGOUT";
	public static final int NUM_PASSWORD_HISTORY = 5;
	public static final int PAGE_SIZE = 25;
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String OTHER_REASON = "Other reason";
	public static final String CONFIRM = "Confirm";
	
	public static final String CONST_FILE_CONFIGURATION = "config.properties";
	public static final String CONST_API_FORMAT_DATE_HEADER = "yyyyMMddhhmmss"; //use for header call ESB
	public static final String CONST_API_FORMAT_DATE_FULL_HEADER = "yyMMddhhmmss.SSS";
	public static final String CONST_WS_RESULT_DOC_REF_ID = "Doc_Ref_ID";
	 public static final String CONST_WS_RESULT_CODE = "Result_Code";
	  public static final String CONST_WS_RESULT_MSG = "Result_Msg";
	  public static final String CONST_WS_RESULT_CODE_SUCCESS = "00000";
	  public static final String CONST_WS_RESULT_FILE_CONTENT = "FileContent";
	
	public enum ParameterApplication{
		 ACTIVE(1,"ACTIVE"),NOACTIVE(0,"NO ACTIVE"),DELETE(3,"DELETE"),BLOCK(2,"LOCKED");
		 private int status;
		 private String name;
		 private ParameterApplication(int i,String name){
			 this.status = i;
			 this.name = name;
		 }
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public static String convertStatus(int statuspar){
			String result="";
			switch (statuspar) {
			case 1:
				result = "ACTIVE" ;
				break;
			case 0:
				result = "NO ACTIVE" ;
				break;
			case 3:
				result = "DELETE" ;
				break;
			case 2:
				result = "LOCKED" ;
				break;
		  }
			return result;
		}
	}
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
	 
	 public enum STAGE{
		 FRESH_LOAN("FRESH_LOAN"),
		 FRESH_LOAN_SEND_TO_RMT("FRESH_LOAN/SEND_TO_RMT"),
		 FRESH_LOAN_RMT_RECEIVE("FRESH_LOAN/RMT_RECEIVE_ACK"),
		 //FRESH_LOAN_RMT_RETURN("FRESH_LOAN/RMT_RETURN"),
		 FRESH_LOAN_RMT_WAIT_LOAN("FRESH_LOAN/RMT_WAIT_LOAN"),
		 FRESH_LOAN_SUSPENSE("FRESH_LOAN/SUSPENSE"),
		 RMT_RECORD_CS_WAITING("RMT_RECORD/CS_WAITING"),
		 RMT_RECORD_CS_COMPLETED("RMT_RECORD/CS_COMPLETED"),
		 
		 
		 RMT_RECORD_VERIFIED("RMT_RECORD/VERIFIED"),
		 RMT_RECORD("RMT_RECORD"),
		 RMT_RECORD_RMT_MARK_CS_WAIVED ("RMT_RECORD/RMT_MARK_CS_WAIVED"),
		 RMT_RECORD_RMT_MARK_DONE("RMT_RECORD/RMT_MARK_DONE"),
		 RMT_RECORD_NOT_SEND("RMT_RECORD/NOT_SEND"),
		 RMT_RECORD_PENDING("RMT_RECORD/PENDING"),
		 RMT_RECORD_RMT_CS_POST("RMT_RECORD/RMT_CS_POST"),
		 RMT_RECORD_RMT_CS_POST_RETURN("RMT_RECORD/RMT_CS_POST_RETURN"),
		 RMT_RECORD_RMT_CS_BRANCH("RMT_RECORD/RMT_CS_BRANCH"),
		 RMT_RECORD_RMT_CS_BRANCH_RECEIVE("RMT_RECORD/RMT_CS_BRANCH_RECEIVE"),
		 RMT_RECORD_RMT_CS_BRANCH_RETURN("RMT_RECORD/RMT_CS_BRANCH_RETURN"),
		 
		 ;
		 //RMT_CHECKED("RMT_CHECKED"),
		 //CREDIT_SHIELD("CREDIT_SHIELD");
		 
		 private String name;
		 private STAGE(String name){
			 this.name = name;
		 }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	 }
	 
	public enum STAGE2 {
		RMT_SCAN_CM("RMT_SCAN_CM"),
		RMT_SCAN_CM_CHECK("RMT_SCAN_CM_CHECK"),
		RMT_RECORD_IN_BOX("RMT_RECORD_IN_BOX");
		private String name;

		private STAGE2(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	 
	 public enum ACTIONS{
		 SEND_TO_RMT("Send to RMT"), 
		 MARK_AS_SEND_TO_BRANCH("Mark as send to branch"), 
		 RMT_RECEIVE_ACK("Receive"),
		 RMT_RETURN_TO_BN("Return"),
		 BN_RECEIVE_ACK("Receive"),
		 RMT_MARK_VERIFIED("Mark as verified"),
		 RMT_WAIT_LOAN("Wait"),
		 RMT_SUSPENSE("Suspense"),
		 RECOVER_LOAN("Recover Loan"),
		 
		 RMT_PRINT_WELCOME_LETTER("Print welcome letter"),
		 //RMT_MARK_CS_UNWAIVED("Mark CS unwaived"),
		 //RMT_MARK_CS_WAIVED("Mark CS waived"),
		 RMT_MARK_DONE("Mark as done"),
		 RMT_CS_POST("Send by post"),
		 RMT_CS_BRANCH("Send to branch"),
		 RMT_CS_NOT_SEND("Mark as not send"),
		 RMT_CS_PENDING("Mark as pending"),
		 
		 
		 RMT_CS_POST_RETURN("Post return"),
		 RMT_UPDATE_PO_BILL_NO("Update bill no"),
		 RMT_CS_RECIEVE_FROM_POST_RETURN("Receive"),
		 RMT_CS_BRANCH_RECEIVE("Receive"),
		 RMT_CS_BRANCH_RETURN("Return"),
		 RMT_CS_BRANCH_DELIVER("Mask as delivered"),
		 RMT_CS_RECEIVE_FROM_BRANCH_RETURN("Receive"),
		 PREPARE_TO_SCAN("Prepare to scan"),
		 SCAN_TO_CM("Mark as scaned to CM"),
		 SCAN_QUALITY_CHECK("Scan quality check"),
		 FINAL_CHECK("Final check"),
		 REVERT_LOAN("Revert Loan"),
		 REVERT_LOAN2("Revert Loan"),
		 RMT_MARK_PREPARED("Mark as prepared"),
		 RMT_MARK_RE_READY("Re-Ready"),
		 NOT_DEFINE("Not Define"), 
		 RMT_WAITING_CS("Waiting CS"), 
		 RMT_COMPLETED_CS("Completed CS"), 
		 
		 
		 //Action in Credit Shield
		 SEND_CS_TO_RMT("Send to RMT"), 
		 RMT_RECEIVE_CS("Receive"),
		 SEND_TO_PVN("Send to PVN"),
		 IC_PENDING("Pending"),
		 IC_FAILED("Failed"),
		 IC_COMPLETED("Completed"),
		 IC_FAILED_TO_COMPLETED("Failed to Completed"),
		 IC_COMPLETED_TO_FAILED("Completed to Failed"),		
		 CS_REVERT_LOAN("Revert Credit Shield"),		
		 
		 LOGIN("Login"),
		 LOGOUT("Logout"),
		 RESET_PASSWORD("Reset Password"),
		 CHANGE_PW("Change Pawword"),
		 GRANT_PERMISSION("Grant Permission"),
		 CREATE_USER("Create User"),
		 EDIT_USER("Edit User"),
		 DEACTIVE_USER("Deactive User"),
		 
		 TB6_UPLOAD("Upload TB6"),
		 TB6_REMARK("Remark"),
		 TB6_SEND_TO_RMT("Send To RMT"),
		 TB6_RECEIVE("Receive"),
		 TB6_COMPLETE("Mask As Complete"),
		 TB6_WAITING("Mark As Waiting"),
		 TB6_RETURN("Return"),
		 FORECLOSURE_UPLOAD("Upload"),
		 STORED_LOAN_UPLOAD("Upload"),
		 STORED_LOAN_UPDATE("Update"),
		 FORECLOSURE_REMARK("Remark"),
		 FORECLOSURE_SEND("SEND"),
		 FORECLOSURE_WAITING("Mark As Waiting"),
		 FORECLOSURE_COMPLETE("Mark As Complete"),
		 FORECLOSURE_RETURN("Return"),
		 INSURANCE_UPLOAD("Upload"),
		 INSURANCE_REMARK("Remark")		 
		 ;
		 
		 public String name;

		private ACTIONS(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	 
	 public enum FollowUpStatus{
		 INITIAL("Initial"),
		 INPROGRESS("In Progress"),
		 COMPLETE("Complete"),
		 CONFIM("Confirm"),
		 REOPEN("Reopen")

		 ;
		 private String name;
		 private FollowUpStatus(String name){
			 this.name = name;
		 }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	 }
	 
	 public enum UPLOAD_STATUS {
		 SUCCESS, FAIL
	 }
	 
	 public enum UPLOAD_TYPES {
		 POST_RETURN, BILL_NO,
		 IC_COMPLETED,
		 IC_FAILED,
		 IC_PENDING,
		 IC_FAILED_TO_COMPLETE,
		 IC_COMPLETED_TO_FALIED,
	 }
	 
	 public static void main(String[] args) {
		 System.out.println("dfdsfsf");
		 String a = Constant.ACTIONS.valueOf("RMT_CS_RECIEVE_FROM_POST_RETURN").name;
		 System.out.println("aaa = " + a);
	}
	 
	 
	 
	 
	 public enum CS_STAGE {
		 CREDIT_SHIELD							("CREDIT_SHIELD"),
		 CREDIT_SHIELD_SEND_TO_RMT				("CREDIT_SHIELD/SEND_TO_RMT"),
		 CREDIT_SHIELD_RMT_RECEIVE				("CREDIT_SHIELD/RMT_RECEIVE_CS"),
		 INSURANCE_CERTIFICATE_WAITING_LIST		("INSURANCE_CERTIFICATE/WAITING_LIST"),
		 INSURANCE_CERTIFICATE_PENDING_LIST		("INSURANCE_CERTIFICATE/PENDING_LIST"),
		 INSURANCE_CERTIFICATE_FAILED_LIST		("INSURANCE_CERTIFICATE/FAILED_LIST"),
		 INSURANCE_CERTIFICATE_COMPLETED_LIST	("INSURANCE_CERTIFICATE/COMPLETED_LIST"),
		 INSURANCE_CERTIFICATE_SENT_LIST		("INSURANCE_CERTIFICATE/SENT_LIST"),
		 ;
		 //RMT_CHECKED("RMT_CHECKED"),
		 //CREDIT_SHIELD("CREDIT_SHIELD");
		 
		 private String name;
		 private CS_STAGE(String name){
			 this.name = name;
		 }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	 }
	 
	 public enum AUTO_DEBIT{
		 SEND_TO_OP("Send to OP"), 
		 RETURN_WITH_REASON("Return with reason"), 
		 RECEIVE_WITH_REASON("Receive with reason"), 
		 ;
		 
		 public String name;

		private AUTO_DEBIT(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	 
	 public enum LOG_TYPE {
		 AUTHENTICATION, DISBURSAL_LOAN, CREDIT_SHIELD, AUTO_DEBIT,TB6,FORECLOSURE,CANCEL_INSURANCE,UPLOAD_LOANKIT_SEND_BY_POST,
		 UPLOAD_LOANRMT_RECEIVE,UPLOAD_LOANRECEIVE_WAITING,UPLOAD_LOANWAITING_DOCUMENTS_SCAN,UPLOAD_LOANRD_PENDING,STORED_LOAN
	 }
	 
	 public enum LOG_STATUS {
		 SUCCESS, FAILURE
	 }
	 
	 public enum TB6_STAGE{
		 TB6_UPLOAD, TB6_SEND, TB6_WAITING, TB6_COMPLETE,
	 }
	 
	 public enum FORECLOSURE_STAGE{
		 FORECLOSURE_UPLOAD, FORECLOSURE_SEND, FORECLOSURE_WAITING, FORECLOSURE_COMPLETE,
	 }
	 
	 public enum INSURANCE_STAGE{
		 INSURANCE_UPLOAD, INSURANCE_REMARK
	 }
}
