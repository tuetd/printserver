package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate;

import com.pruvn.tools.common.hibernate.finnone.printsrv.V_WS_LOAN_STATUS;
import com.pruvn.tools.printserver.GenericDAO;

public interface V_Ws_Loan_Status_DAO extends GenericDAO<V_WS_LOAN_STATUS,Integer>{
	V_WS_LOAN_STATUS getWSLoanStatusByApplid(String app_id_c);
}
