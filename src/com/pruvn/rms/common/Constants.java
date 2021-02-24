package com.pruvn.rms.common;


public class Constants {
	public static final String DOMAIN_NAME = "pru_vn";
	
	public static final String BANK_TEMPLATE_SHEETNAME = "Bank Template";
	public static final String BANK_HEADER_A = "AccountID";
	public static final String BANK_HEADER_B = "TransCode";
	public static final String BANK_HEADER_C = "DebitAmount";
	public static final String BANK_HEADER_D = "CreditAmount";
	public static final String BANK_HEADER_E = "TransDate";
	public static final String BANK_HEADER_F = "BenAccount";
	public static final String BANK_HEADER_G = "Description";
	public static final String BANK_HEADER_H = "Commission";
	public static final String BANK_HEADER_I = "Charge";
	public static final String BANK_HEADER_J = "Tax";
	public static final String BANK_HEADER_K = "SenderAccount";
	public static final String BANK_HEADER_L = "SenderName";
	public static final String BANK_HEADER_M = "SenderAddress";
	public static final String BANK_HEADER_N = "BenCustomer";
	public static final String BANK_HEADER_O = "BenPayDetails";

	public static final String CUST_INFO_HEADER_A = "Loanno";
	public static final String CUST_INFO_HEADER_B = "CustID";
	public static final String CUST_INFO_HEADER_C = "NationalID";
	public static final String CUST_INFO_HEADER_D = "DOB";
	public static final String CUST_INFO_HEADER_E = "CustName";
	public static final String CUST_INFO_HEADER_F = "CompanyName";
	
	public static final String LOAN_INFO_HEADER_A = "Loanno";
	public static final String LOAN_INFO_HEADER_B = "Application form no";
	public static final String LOAN_INFO_HEADER_C = "Customerid";
	public static final String LOAN_INFO_HEADER_D = "ProductFlag";
	public static final String LOAN_INFO_HEADER_E = "Financed Amount (AMTFIN)";
	public static final String LOAN_INFO_HEADER_F = "DisbursalDate";
	public static final String LOAN_INFO_HEADER_G = "DisbursedAmount";
	public static final String LOAN_INFO_HEADER_H = "EMI";
	public static final String LOAN_INFO_HEADER_I = "DUE DAY";
	public static final String LOAN_INFO_HEADER_J = "Tenure";
	public static final String LOAN_INFO_HEADER_K = "Bucket";
	public static final String LOAN_INFO_HEADER_L = "NPA_STAGEID";
	public static final String LOAN_INFO_HEADER_M = "Status";
	public static final String LOAN_INFO_HEADER_N = "Closed date";
	public static final String LOAN_INFO_HEADER_O = "Closed type";
	public static final String LOAN_INFO_HEADER_P = "Scheme id";
	public static final String LOAN_INFO_HEADER_Q = "Scheme desc";
	public static final String LOAN_INFO_HEADER_R = "Branch id";
	public static final String LOAN_INFO_HEADER_S = "Branch desc";
	
	public static final String CUST_ADDR_INFO_HEADER_A = "Loanno";
	public static final String CUST_ADDR_INFO_HEADER_B = "AddressLine1";
	public static final String CUST_ADDR_INFO_HEADER_C = "AddressLine2";
	public static final String CUST_ADDR_INFO_HEADER_D = "AddressLine3";
	public static final String CUST_ADDR_INFO_HEADER_E = "Phone";
	
	public static final String BANK_NAME_ACB = "ACB";
	public static final String BANK_NAME_CITI_NEW = "CITI_NEW";
	public static final String BANK_NAME_CITI_OLD = "CITI_OLD";
	public static final String BANK_NAME_VCB = "VCB";
	public static final String BANK_NAME_SMARTLINK = "SMARTLINK";
	public static final String BANK_NAME_HDBANK = "HDBANK";
	public static final String BANK_NAME_VNPOST = "VNPOST";
	
	public static final String BANK_CREDIT_TYPE = "C";
	public static final String BANK_DEBIT_TYPE = "D";
	
	public static final char DOUBLEQUOTE = '\"';	
	public static final String SINGLEQUOTE = "\'";
	public static final String NEWLINE = "\n";
	public static final char TAB = '\t';
	public static final String BACKSPACE = "\b";
	public static final String FORMFEED = "\f";
	public static final String RETURN = "\r";
	public static final String BACKSLASH = "\\";
	public static final char COMMA = ',';

	public static final String BANKSTMT_CONVERT_RESULT_PLACE = "BANKSTMT_CONVERT_RESULT_PLACE";
	public static final String FTP_ENABLE = "FTP_ENABLE";
	
	public static final Integer RMDB_OBJ_LIBRARYID = 1;
	public static final Integer RMDB_OBJ_VERSION = 1;
	public static final Integer RMDB_OBJ_COLLECTIONID = 1;
	
	public static final String QDE_CM_STATUS_PENDING = "PENDING";
	public static final String QDE_CM_STATUS_ASSIGNEDTO = "ASSIGNEDTO";
	public static final String QDE_CM_STATUS_FINISHED = "FINISHED";
	/*
	 * * deviation status
	 */
	public static final String DEVIATION_STATUS_PROCESSING = "PROCESSING";
	public static final String DEVIATION_STATUS_FORWARD_REVIEWER = "FORWARD_REVIEWER";
	public static final String DEVIATION_STATUS_FORWARD_APPROVAL = "FORWARD_APPROVAL";
	public static final String DEVIATION_STATUS_FORWARD_CREATE = "FORWARD_CREATE";
	public static final String DEVIATION_STATUS_DENIED = "DENIED";
	public static final String DEVIATION_STATUS_APPROVED  = "APPROVED";
	public static final String DEVIATION_STATUS_FINISH  = "FINISHED";
	/*
	 *  deviation role
	 */
	public static final String ROLE_CREATE_DEVIATION = "ROLE_CREATE_DEVIATION";
	public static final String ROLE_REVIEWER_DEVIATION = "ROLE_REVIEWER_DEVIATION";
	public static final String ROLE_APPROVAL_DEVIATION = "ROLE_APPROVAL_DEVIATION";
	public static final String ROLE_FINISHER_DEVIATION = "ROLE_FINISHER_DEVIATION";
	public static final String ROLE_REPORT_DEVIATION = "ROLE_REPORT_DEVIATION";
	/*
	 *  devaition log
	 */
	public static final String LOGIN = "LOGIN";
	public static final String LOGOUT = "LOGOUT";
	public static final String INVALID_LOGIN = "Invalid login";
	public static final String SUCCESS_LOGIN = "Success login";
	public static final String SUCCESS_LOGOUT = "Success logout";
	public static final String SUCCESS_RESET_PW = "Success reset pw";
	public static final String SUCCESS_GRANT_PER = "Success grant permission";
	public static final String TIME_EXPIRED = "Exceed the limit of the times logged in";
	public static final String LOGIN_EXPIRED = "Exceed in number of times logged in";
	public static final String ISACTIVE = "Active";
	public static final String BLOCK = "Block";
}

