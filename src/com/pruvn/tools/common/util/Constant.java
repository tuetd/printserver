package com.pruvn.tools.common.util;

import java.util.Map;

public class Constant {
	public static final String SYSTEM_USER = "administrator";
	public static final String SYSTEM_PASSWORD = "hongbiet";	
	
	public static final String OO_SERVER = "localhost";
//	public static final int OO_PORT = 8100;
//	public static final String OO_PROGRAM_LOCATION = "C:\\Program Files\\OpenOffice.org 3.4.1\\program";													  
//	public static final String OO_PROGRAM_LOCATION = "/opt/openoffice.org3/program";
	
	public static final String STATUS_PRINTED = "PRINTED";
	public static final String STATUS_NOT_PRINTED = "NOT_PRINTED";
	public static final String SESSION_KEY_LOG_PLACE = "LOG_PLACE";
	
	public static final String FILE_PATH_PRINTED = "C:/";
//	public static final String FILE_PATH_PRINTED = "/home/cfcprintsvr/doc/";
//	public static final String FILE_PATH_PRINTED = "D:/app/printserver/doc/";
//	public static final String FILE_PATH_PRINTED = "C:/LONG/app/printserver/doc/";
	
	public static final String FILE_PATH_TEMPLATE = "C:/";
//	public static final String FILE_PATH_TEMPLATE = "/home/cfcprintsvr/templatefile/";
//	public static final String FILE_PATH_TEMPLATE = "D:/app/printserver/templatefile/";
//	public static final String FILE_PATH_TEMPLATE = "C:/LONG/app/printserver/templatefile/";
	
	public static final String DIFFERENTFLATRATE = "Flat rate is not same from FinnOne system, please double check whether you put right flat rate in system";
	public static final String DIFFERENTINSTAMT = "Installment amount is not same from FinnOne system, please double check the input values";
	public static final String NOTAPPROVED = "This application has not been approved yet";		
	public static final String NOTPERSONALLOAN = "This is not a personal loan product, please double check the input values";
	public static final String WRONGTENURE = "The tenure of the application is wrong, please double check the input values";
	public static final String APPLICATIONNOTEXIST = "This application does not exist";
	public static final String NOREPAYMENTSCHEDULE = "The repayment schedule for this application has not been generated";
	public static final String APPLICATION_REACHED_FINISH_STAGE = "This application reached finish stage";
	
	public static final String DOCNAME_AUTHORIZE_FORM = "DOCNAME_AUTHORIZE_FORM";
	public static final String DOCNAME_AUTODEBIT_FORM = "DOCNAME_AUTODEBIT_FORM";
	public static final String DOCNAME_DISBURSAL_REQUEST = "DOCNAME_DISBURSAL_REQUEST";
	public static final String DOCNAME_REPAYMENT_SCHEDULE = "DOCNAME_REPAYMENT_SCHEDULE";
	public static final String DOCNAME_CERTIFICATE = "DOCNAME_CERTIFICATE";
	public static final String DOCNAME_PROMISSORY_NOTE = "DOCNAME_PROMISSORY_NOTE";
	public static final String DOCNAME_FCL_REQ_1A3 = "DOCNAME_FCL_REQ_1A3";
	public static final String DOCNAME_FCL_REQ_2 = "DOCNAME_FCL_REQ_2";
	public static final String DOCNAME_INFO_CARD = "DOCNAME_INFO_CARD";
	public static final String DOCNAME_INFO_CARD_IMPORT = "DOCNAME_INFO_CARD_IMPORT";
	public static final String DOCNAME_FINAL_SANCTION_LETTER = "DOCNAME_FINAL_SANCTION_LETTER";
	public static final String DOCNAME_SF_VERSION_6_1_CERTIFICATE = "DOCNAME_SF_VERSION_6_1_CERTIFICATE";
	public static final String DOCNAME_SF_PHU_LUC_1_V6_1 = "DOCNAME_SF_PHU_LUC_1_V6_1";
	public static final String SF_VERSION_5_PROMISSORY_NOTES = "SF_VERSION_5_PROMISSORY_NOTES";
	public static final String SF_VERSION_5_CERTIFICATE = "SF_VERSION_5_CERTIFICATE";
	public static final String FORECLOSURE_FOR_RECOVERY = "FORECLOSURE_FOR_RECOVERY";
	public static final String CLOSE_AGREEMENT_FOR_RECOVERY = "CLOSE_AGREEMENT_FOR_RECOVERY";
	public static final String DATASOURCE_NAME_INFO_CARD = "INFO_CARD";
	public static final String NOSIGN_DOCNAME_SUFFIX = "_nosign";

	//Mapping document, this stores keys, values will be retrieved from parammaster table
	public static final String PL_Certificate_Third = "PL_Certificate_Third";
	public static final String PLRS30 = "PLRS30";
	public static final String PLRS48 = "PLRS48";
	public static final String PLRS36 = "PLRS36";
	public static final String PLRS24 = "PLRS24";
	public static final String PLRS18 = "PLRS18";
	public static final String PLRS12 = "PLRS12";
	public static final String ROREPAYMENTTERM12 = "ROREPAYMENTTERM12";
	public static final String ROREPAYMENTTERM18 = "ROREPAYMENTTERM18";
	public static final String ROREPAYMENTTERM24 = "ROREPAYMENTTERM24";
	public static final String ROREPAYMENTTERM36 = "ROREPAYMENTTERM36";
	public static final String ROREPAYMENTTERM48 = "ROREPAYMENTTERM48";
	public static final String ROREPAYMENTTERM30 = "ROREPAYMENTTERM30";
	public static final String DOCNAME_SF_PROMNOTE = "DOCNAME_SF_PROMNOTE";
	public static final String DOCNAME_SF_CERTIFICATE = "DOCNAME_SF_CERTIFICATE";
	public static final String RO_REPAY_SCH = "RO_REPAY_SCH";
	public static final String RO_PROM_NOTE = "RO_PROM_NOTE";
	public static final String RO_CERTIFICATE = "RO_CERTIFICATE";
	public static final String PS_DOC_PRT_PLC = "PS_DOC_PRT_PLC";
	public static final String FCL_FORM_PLC = "FCL_FORM_PLC";
	public static final String ONT_SIMPLE_SOA = "ONT_SIMPLE_SOA";
	public static final String PS_TMPL_PLC = "PS_TMPL_PLC";
	
	public static Map<String, String> docMapping = null;
	public static Map<String, String> docMappingReverse = null;
	
	//SR452
	public static final String DOCNAME_FCL_REQ_ESTIMATED = "DOCNAME_FCL_REQ_ESTIMATED";
	//SR692
	public static final String DOCNAME_FCL_REQ_ESTIMATED_CREDIT_SHEILD = "DOCNAME_FCL_REQ_ESTIMATED_CREDIT_SHEILD";
}
