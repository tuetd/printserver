package com.pruvn.tools.common.hibernate.finnone.lending.hibernate;

import java.util.List;

import com.pruvn.tools.common.hibernate.finnone.lending.NBFC_CHEQUE_DTL;
import com.pruvn.tools.printserver.GenericDAO;

public interface Nbfc_Cheque_Dtl_DAO extends GenericDAO<NBFC_CHEQUE_DTL, Integer>{
	List<NBFC_CHEQUE_DTL> getChequeListByCustIDLikeChequeNumOrderByChequeDate(Integer BPID, String CHEQUENUM);
}
