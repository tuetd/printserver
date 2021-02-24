package com.pruvn.rms.utils;

public class SqlConstant {
	
	public static final String ORDER_BY = " ORDER BY BRANCHDESC, DISBURSALDATE, AGREEMENTNO";
	public static final String CONST_API_FORMAT_DATE_HEADER = "yyyyMMddhhmmss"; //use for header call ESB	
	public static final String DELETE_ICM_DOCUMENT = " DELETE FROM RM_LOAN ";
	public static final String CONST_WS_REF_ID_REFIX = "RMS_";
	
	public static final String BUILD_ICM_DOCUMENT =  "select LOANAGRNO  ,DOCREFID,APPREFNO,Filename,Filepath,Mimetype " 
	+" from (select opt.LOANNO as LOANAGRNO,doc.DOCREFID,doc.APPREFNO,doc.Filename,"
	+" doc.Filepath,doc.Mimetye,row_number() over(partition by doc.APPREFNO,doc.DOCID"
	+" order by doc.SCANNEDDATE desc ) rn "
	+" from icm_document doc,RM_LOAN opt "
	+"  where 1=1 "
	+" and doc.LOANAGRNO=opt.LOANNO"
    +" and doc.docid = '53' "
    +" order by doc.CREATE_DT,doc.APPREFNO )where rn=1";
	
	//View chứa ho so giai ngan ban dau
	public static final String SELECT_FRESH_LOAN_LIST_ACL = "SELECT * FROM RM_RECORD_FL WHERE 1= 1 " 
			+ "AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	// view lấy toàn bộ dữ liệu cho autodebit
	public static final String SELECT_AUTO_DEBIT_LIST_AD = 
					"SELECT AD1.* " +
					"  FROM AD_RECORD AD1"+
					"  WHERE 1=1 " +
					"  #Clause# " +						
					" AND check_privs_AD(:username,:stage,id,branchdesc) = 1 ORDER BY AD1.TYPEAUTODEBIT desc, AD1.BANKNAME asc" ;
	
	// view lấy toàn bộ dữ liệu cho document maintenance
		public static final String SELECT_DOCUMENT_MAINTENANCE_LST = 
						"SELECT AD1.* " +
						"  FROM DOCUMENT_MAINTENANCE AD1"+
						"  WHERE 1=1 " +
						//"  #Clause# " +						
						" AND check_privs_AD(:username,:stage,CIF,UPLOADCHANNEL) = 1 ORDER BY AD1.CIF desc, AD1.LOANID asc" ;
	
	
	// count toàn bộ dữ liệu autodebit
	
	public static final String COUNT_AUTO_DEBIT_LIST_AD = 
			"SELECT count(*) " +
					"  FROM AD_RECORD AD1"+
					"  WHERE 1=1 " +
					"  #Clause# " +						
					" AND check_privs_AD(:username,:stage,id,branchdesc) = 1 ORDER BY AD1.TYPEAUTODEBIT desc, AD1.BANKNAME asc " ;
	
	// getbanklist
	public static final String getListBank = 
			
			//"select * from dwh_etl.LENDING#CR_GENE_PARA";
	            "select * from CR_GENE_PARA";
	
	// getbranchlist
		public static final String getListBranch = 
				
				"  select BRANCHDESC from ad_record  GROUP BY BRANCHDESC" ;
	             		
		
	//find by id
	public static final String FIND_BY_ID = "SELECT * FROM AD_RECORD WHERE ID= :ID " ;
	
	//View chứa hồ sơ đã gởi từ Branch đến RMT
	public static final String SELECT_RMT_LIST_ACL = "SELECT * FROM RM_RECORD_FL1 WHERE 1= 1 " 
	+ " AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//view chua DS da nhan tu RMT
	public static final String SELECT_RMT_REVEIVE_LIST_ACL = "SELECT * FROM RM_RECORD_FL2 WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//view chua DS Loan da nhan nhung dang wait
	public static final String SELECT_RMT_WAIT_LIST_ACL = "SELECT * FROM RM_RECORD_WAIT WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	//View chua DS tra lai cho Branch Network
	//public static final String SELECT_RMT_RETURN_LIST_ACL = "SELECT * FROM RM_RECORD_FL3 WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View cua DS RMT da xac nhan
	public static final String SELECT_RMT_RECORD_VERIFIED_LIST_ACL = "SELECT * FROM RM_RECORD_VERIFIED WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View cua DS RMT da prepared
	public static final String SELECT_RMT_RECORD_PREPARED_LIST_ACL = "SELECT * FROM RM_RECORD_PREPARED WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chua DS ho so da xac nhan, chuan bi goi cho khach hang qua buu dien/chi nhanh
	public static final String SELECT_RMT_RECORD_READY_LIST_ACL = "SELECT * FROM RM_RECORD_READY WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View cua DS ho so khong goi cho khach hang
	public static final String SELECT_RMT_RECORD_NOT_SEND_LIST_ACL = "SELECT * FROM RM_RECORD_NOT_SEND WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View cua DS ho so dang pending lai, se goi cho khach hang sau.
	public static final String SELECT_RMT_RECORD_PENDING_LIST_ACL = "SELECT * FROM RM_RECORD_PENDING WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chua DS ho so đã goi cho khach hang qua buu dien
	public static final String SELECT_RMT_RECORD_POST_LIST_ACL = "SELECT * FROM RM_RECORD_PO WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chua DS ho so qua buu dien nhung bi tra ve
	public static final String SELECT_RMT_RECORD_POST_RETURN_LIST_ACL = "SELECT * FROM RM_RECORD_PO_RETURN WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	//View chua DS ho so đã goi cho khach hang qua chi nhanh
	public static final String SELECT_RMT_RECORD_BRANCH_LIST_ACL = "SELECT * FROM RM_RECORD_BRANCH WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	//View chua DS ho so đã goi cho khach hang qua chi nhanh, da duoc chi nhanh nhan
	public static final String SELECT_RMT_RECORD_BRANCH_RECEIVE_LIST_ACL = "SELECT * FROM RM_RECORD_BRANCH_RECEIVE WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";

	//View chua DS ho so đã goi cho khach hang qua chi nhanh, da bi chi tra ve
	public static final String SELECT_RMT_RECORD_BRANCH_RETURN_LIST_ACL = "SELECT * FROM RM_RECORD_BRANCH_RETURN WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chua DS ho so khach hang đã nhận qua chi nhanh
	public static final String SELECT_RMT_RECORD_BRANCH_DELIVER_LIST_ACL = "SELECT * FROM RM_RECORD_BRANCH_DELIVER WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
		
	
	//View chua DS ho so da xac nhan, chuan bi scan len CM
	public static final String SELECT_RMT_RECORD_SCAN_CM_LIST_ACL = "SELECT * FROM RM_RECORD_SCAN_CM WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chua DS ho so da xac nhan, chuan bi scan len CM
	public static final String SELECT_RMT_RECORD_SCAN_CM_CHECK_LIST_ACL = "SELECT * FROM RM_RECORD_SCAN_CM_CHECK WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chua DS ho so scan và kiểm tra lần 1, được xếp vào hộp
	public static final String SELECT_RMT_RECORD_IN_BOX_LIST_ACL = "SELECT * FROM RM_RECORD_IN_BOX WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	public static final String SELECT_RMT_BY_ID = "SELECT * FROM RM_RECORD_FL1 WHERE 1= 1 AND ID = :id";
	
	public static final String SELECT_RMT_RETURN_BY_ID = "SELECT * FROM RM_RECORD_FL3 WHERE 1= 1 AND ID = :id";
	
	public static final String SELECT_RECORD_BY_AGREEMENTNO = "SELECT * FROM RM_RECORD WHERE 1= 1 AND AgreementNo in (:list)";
	
	
	//View chứa hồ sơ suspense
	public static final String SELECT_SUSPENSE_LIST_ACL = "SELECT * FROM RM_RECORD_SUSPENSE WHERE 1= 1 " 
	+ " AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	
	//View cua DS các Loan mà  CS waiting
	public static final String SELECT_RMT_RECORD_CS_WAITING_LIST_ACL = "SELECT * FROM RM_RECORD_CS_WAITING WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	//View cua DS các Loan mà CS completed.
	public static final String SELECT_RMT_RECORD_CS_COMPLETED_LIST_ACL = "SELECT * FROM RM_RECORD_CS_COMPLETED WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	
	
	public static final String STORED_INVOLVE = "{ CALL involve(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static final String SYNCHRONIZE_DATA = "{ CALL synchronizeData(?)}";
	public static final String CLEANUP_PASSWORD_HISTORY = "{ CALL cleanupPasswordHistory(?,?)}";
	
	public static final String STORED_ACTION = "{ CALL action(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	
	
	
	
	
	public static final String SELECT_CS_RECORD_FL_LIST_ACL = "SELECT * FROM CS_RECORD_FL WHERE 1= 1 " 
			+ "AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//View chứa hồ sơ đã gởi từ Branch đến RMT
	public static final String SELECT_CS_RECORD_RMT_LIST_ACL = "SELECT * FROM CS_RECORD_RMT WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//view chua DS da nhan tu RMT
	public static final String SELECT_CS_RECORD_RMT_REVEIVE_LIST_ACL = "SELECT * FROM CS_RECORD_RMT_RECEIVE WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//view chua ho so dang wait Insurance Certificate 
	public static final String SELECT_CS_RECORD_WAITING_LIST_ACL = "SELECT * FROM CS_RECORD_WAITING WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	
	
	//view chua ho so dang pending Insurance Certificate 
	public static final String SELECT_RMT_PENDING_LIST_ACL = "SELECT * FROM CS_RECORD_PENDING WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
		
	//view chua ho so dang wait Insurance Certificate 
	public static final String SELECT_RMT_FAILED_LIST_ACL = "SELECT * FROM CS_RECORD_FAILED WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	//view chua ho so dang wait Insurance Certificate 
	public static final String SELECT_RMT_COMPLETED_LIST_ACL = "SELECT * FROM CS_RECORD_COMPLETED WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	
	//view chua ho so da sent Insurance Certificate 
	public static final String SELECT_RMT_SENT_LIST_ACL = "SELECT * FROM CS_RECORD_SENT WHERE 1= 1 AND check_privs(:username,:stage,id,branchdesc,productflag) = 1";
	
	
	//Search Data
	//View chứa ho so giai ngan ban dau
	public static final String SELECT_RECORD_LIST_ACL = "SELECT * FROM RM_RECORD WHERE 1= 1 ";
	public static final String SELECT_RECORD_CREDIT_SHIELD = "SELECT * FROM RM_RECORD WHERE 1= 1 AND CREDIT_SHIELD > 0 ";
	
	public static final String SELECT_MRC_LIST_ACL = "SELECT * FROM RM_MRC WHERE 1= 1 ";
	
	public static final String SELECT_TB6_LIST_ACL = "SELECT * FROM RM_TB6 WHERE 1= 1 ";
	
	public static final String SELECT_FORECLOSURE_LIST_ACL = "SELECT * FROM RM_FORECLOSURE WHERE 1= 1 ";
	
	public static final String SELECT_STOREDLOAN_LIST_ACL = "SELECT * FROM RM_STOREDLOAN WHERE 1= 1 ";
	
	public static final String SELECT_CANCEL_INSURANCE_LIST_ACL = "SELECT * FROM RM_CANCEL_INSURANCE WHERE 1= 1 ";
}
