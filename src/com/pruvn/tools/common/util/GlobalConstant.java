package com.pruvn.tools.common.util;

public class GlobalConstant {	
	public static final String MASTERFTPHOST = "128.235.133.19";
//	public static final String MASTERFTPHOST = "128.235.133.4";
	public static final Integer MASTERFTPPORT = new Integer(21);

	public static final String MASTERDBHOST = "128.235.133.19";	
//	public static final String MASTERDBHOST = "128.235.133.4";
	public static final String MASTERDBPORT = "3306";	
	
	public static final String FINNONEPRODUCTIONSYSTEMNAME = "FINNONEPRODUCTION";
	public static final String UATFTPSYSTEMNAME = "UATFTP";
	public static final String SYSTEMNAME_FTPSERVER = "SMSFPTSERVER";
	
	/**
	 * dd/MM/yyyy
	 */
	public static final String DATEPATTERN_1 = "dd/MM/yyyy";
	
	/**
	 * MM-dd-yyyy
	 */
	public static final String DATEPATTERN_12 = "MM-dd-yyyy";
	
	/**
	 * dd.MM.yy
	 */
	public static final String DATEPATTERN_2 = "dd.MM.yy";
	/**
	 * yyMMdd
	 */
	public static final String DATEPATTERN_3 = "yyMMdd";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATEPATTERN_4 = "yyyy-MM-dd";	
	
	
	/**
	 * ddMMyyhhmmss
	 */
	public static final String DATETIMEPATTERN_1 = "ddMMyyhhmmss";
	/**
	 * dd/MM/yy hh:mm:ss
	 */
	public static final String DATETIMEPATTERN_2 = "dd/MM/yy hh:mm:ss";
	/**
	 * dd/MM/yy HH:mm:ss
	 */
	public static final String DATETIMEPATTERN_6 = "dd/MM/yy HH:mm:ss";
	

	public static final String DATETIMEPATTERN_8 = "dd/MM/yyyy HH:mm:ss";
	
	public static final String DATETIMEPATTERN_7 = "yyyyMMddhhmmss";
	
	public static final char DOUBLEQUOTE = '\"';	
	public static final String SINGLEQUOTE = "\'";
	public static final String NEWLINE = "\n";
	public static final char TAB = '\t';
	public static final String BACKSPACE = "\b";
	public static final String FORMFEED = "\f";
	public static final String RETURN = "\r";
	public static final String BACKSLASH = "\\";
	
	public static final int SUCCESS = 1000000;
	public static final int FAIL = 1000001;	
	
	public static final String PARAMNAME_SMSURLPARAMNAME = "SMSURL";
	public static final String PARAMNAME_SMSSPECIALCODE = "SMSSPECIALCODE";
	public static final String PARAMNAME_SMSSPACECODE = "SMSSPACECODE";
	
	public static final String FILETYPE_EXCEL = "EXCEL";
	public static final String FILETYPE_TEXTDELIMITER = "TEXT DELIMITER";
	
	public static final String STATUS_CANCEL = "CANCEL";
	
	public static final String SYSTEMTYPE_ORACLEDB = "ORACLE DATABASE";
	public static final String SYSTEMTYPE_SQLSERVERDB = "SQL SERVER DATABASE";
	public static final String SYSTEMTYPE_MYSQLDB = "MYSQL DATABASE";
	
	public static final String DATATYPE_TEXT = "TEXT";
	public static final String DATATYPE_NUMBER = "NUMBER";
	public static final String DATATYPE_DATE = "DATE";
	public static final String DATATYPE_DATETIME = "DATETIME";
	
	/**
	 * ###,###,###
	 */
	public static final String TEXTFORMAT_NUMBERPATTERN1 = "###,###,###";
	
	/**
	 * added 17-10-08
	 * MM/dd/yyyy
	 * 
	 */
	public static final String DATEPATTERN_5 = "MM/dd/yyyy";
	
	/**
	 * added 30-10-2008
	 */
	public static final String DWH = "DWH";
	
	/**
	 * added 6-11-08
	 * dd-MON-YYYY
	 * 
	 */
	public static final String DATEPATTERN_6 = "dd-MMM-yyyy";
	
	/**
	 * added 6-11-08
	 * yyyyMMdd
	 * 
	 */
	public static final String DATEPATTERN_7 = "yyyyMMdd";
	
	/**
	 * ddMMyy
	 * 
	 */
	public static final String DATEPATTERN_10 = "ddMMyy";
	
	/**
	 * added 19-11-08
	 * dd-MM-yy hh-mm-ss
	 */
	public static final String DATETIMEPATTERN_3 = "dd-MM-yy hh-mm-ss";
	
	/**
	 * 
	 * MM/dd/yy
	 * 
	 */
	public static final String DATEPATTERN_8 = "MM/dd/yy";
	
	/**
	 * hh:mm:ss
	 * 
	 */
	public static final String TIMEPATTERN_1 = "hh:mm:ss";
	
	/**
	 * HHmmss
	 * 
	 */
	public static final String TIMEPATTERN_2 = "HHmmss";
	
	/**
	 * HH:mm:ss
	 * 
	 */
	public static final String TIMEPATTERN_3 = "HH:mm:ss";
	
	
	public static final String EXPORTPDF = "Export to PDF";
	
	public static final String OPENOFFICEUNOSERVER = "OPENOFFICEUNOSERVER";
	
	public static final String IBMCM = "IBMCM";
	
	public static final String PDFTEMPFILE = "C:\\PS_TEMP.PDF";
	
	/** 
	 * yyyy-MM-dd hh:mm:ss
	 */
	public static final String DATETIMEPATTERN_4 = "yyyy-MM-dd hh:mm:ss";
	/**
	 * ddMMyyHHmmss
	 */
	public static final String DATETIMEPATTERN_5 = "ddMMyyHHmmss";
	/**
	 * 
	 * ddMMyyyy
	 * 
	 */
	public static final String DATEPATTERN_9 = "ddMMyyyy";
	/**
	 * 
	 * MM/yyyy
	 * 
	 */
	public static final String DATEPATTERN_11 = "MM/yyyy";
	
	public static final String HIBERNATE_FILE_CONFIG_BATCH_JOB_LOG = "/ndlong/common/hibernate/batch_job_log/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_CFCLONGDEV = "/ndlong/common/hibernate/cfclongdev/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_DWH = "/ndlong/common/hibernate/standalone/conf/dwh.hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_E_REFERAL = "/ndlong/common/hibernate/e_referal.hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNBANK = "/ndlong/common/hibernate/finnbank/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_SMSD = "/ndlong/common/hibernate/smsd.hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE = "/ndlong/common/hibernate/finnone.old.hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_LENDING = "/ndlong/common/hibernate/finnone/lending/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_GDC = "/ndlong/common/hibernate/finnone/gdc/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_PRINTSRV = "/ndlong/common/hibernate/finnone/printsrv/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_CAS = "/ndlong/common/hibernate/finnone/cas/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_FA = "/ndlong/common/hibernate/finnone/fa/hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_ALL = "/ndlong/common/hibernate/finnone.hibernate.cfg.xml";
	public static final String HIBERNATE_FILE_CONFIG_FINNONE_STANDALONE = "/ndlong/common/hibernate/standalone/conf/finnone.hibernate.cfg.xml";
	
	//Added by Anh Ha
	public static final String HIBERNATE_FILE_CONFIG_WOCUSTOMERDEV = "/ndlong/common/hibernate/wocustomerdev/hibernate.cfg.xml";
	
	public static final String DOMAIN_NAME = "pru_vn";//pruasia
	public static final String LOGIN_SUCCESS = "LOGIN SUCCESS";
	
	public static final String EMAIL_SYS_MONITOR_SAS_PRD_ETL = "SAPRET";
	public static final String EMAIL_SYS_MONITOR_SAS_UAT_ETL = "SAUAET";
	
	public static final String SESSION_KEY_FINNBANK_CONN = "finnbank_conn";
	public static final String SESSION_KEY_CFCLONGDEV_CONN = "cfclongdev_conn";
	public static final String SESSION_KEY_FINNONE_CONN = "finnone_conn";
	public static final String SESSION_KEY_DWH_CONN = "dwh_conn";
	public static final String SESSION_KEY_LAST_UPDATE = "last_update";	
	public static final String SESSION_KEY_USERNAME = "username";
	public static final String SESSION_KEY_ROLE_NAME = "role_id";
	public static final String SESSION_KEY_ROLE_ID = "role_id";
	public static final String SESSION_KEY_USER_ID = "user_id";
	
	public static final String SESSION_KEY_USER_DESC = "userdesc";
	public static final String SESSION_KEY_USER_PLACE = "userplace";
	
	public static final String HIBERNATE_OPERATOR_EQUAL = "EQUAL";
	public static final String HIBERNATE_OPERATOR_NOT_EQUAL = "NOT_EQUAL";
	public static final String HIBERNATE_OPERATOR_IN_LIST = "IN_LIST";
	public static final String HIBERNATE_OPERATOR_LIKE = "LIKE";
	public static final String HIBERNATE_OPERATOR_SORT_ASC = "SORT_ASC";
	public static final String HIBERNATE_OPERATOR_SORT_DESC = "SORT_DESC";
	public static final String HIBERNATE_OPERATOR_GREATER_EQUAL = "GREATER_EQUAL";
	public static final String HIBERNATE_OPERATOR_GREATER_THAN = "GREATER_THAN";
	public static final String HIBERNATE_OPERATOR_LESSER_EQUAL = "LESSER_EQUAL";
	public static final String HIBERNATE_OPERATOR_LESS_THAN = "LESS_THAN";
	public static final String HIBERNATE_OPERATOR_IS_NOT_NULL = "IS_NOT_NULL";
	
	public static final String HIBERNATE_PROJECTION_MIN = "PROJECTION_MIN";
	public static final String HIBERNATE_PROJECTION_MAX = "PROJECTION_MAX";
	public static final String HIBERNATE_PROJECTION_SUM = "PROJECTION_SUM";
	public static final String HIBERNATE_PROJECTION_LIST = "PROJECTION_LIST";
	
	public static final String LMS_BANKNAME_TECHCOM = "TECHCOM BANK";
	
	
	//FinnBANK status
	public static final String FINNBANK_STATUS_PENDING = "PENDIN";
	public static final String FINNBANK_STATUS_CANCEL = "CANCEL";
	public static final String FINNBANK_STATUS_PASS = "PASSED";
	public static final String FINNBANK_STATUS_OK = "OK";
	public static final String FINNBANK_STATUS_FORECLOSURE_SUSPECT = "FCLSUS";
	public static final String FINNBANK_STATUS_EXPORT_CSV = "EXPCSV";
	public static final String FINNBANK_STATUS_FORECLOSURE_CASES = "FRCLSR";
	public static final String FINNBANK_STATUS_FORECLOSURE_EXPORT_READY = "FLEXRD";
	public static final String FINNBANK_STATUS_CORPORATE_REPAYMENT = "CORPMT";
	public static final String FINNBANK_STATUS_REFUND = "REFUND";
	public static final String FINNBANK_STATUS_REFUND_WITH_FEE = "REFFEE";
	public static final String FINNBANK_STATUS_AUDIT_FOLLOW_UP = "FOLLUP";
	public static final String FINNBANK_STATUS_TB6 = "TB6";
	public static final String FINNBANK_STATUS_REJECT = "REJECT";
	public static final String FINNBANK_STATUS_FORECLOSURE_FORMS = "FORMS";
	
	
	public static final String OOO_NEWLINE_INDICATOR = "@newline@";
	public static final String OOO_TAB_INDICATOR = "@tab@";
	
}

