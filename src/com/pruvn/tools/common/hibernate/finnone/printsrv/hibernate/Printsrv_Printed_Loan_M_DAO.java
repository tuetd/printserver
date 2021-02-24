package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate;

import com.pruvn.tools.common.hibernate.finnone.printsrv.PRINTSRV_PRINTED_LOAN_M;
import com.pruvn.tools.printserver.GenericDAO;

public interface Printsrv_Printed_Loan_M_DAO extends GenericDAO<PRINTSRV_PRINTED_LOAN_M,Integer>{
 Boolean checkPrintedDoc(PRINTSRV_PRINTED_LOAN_M loan) throws Exception;
}
