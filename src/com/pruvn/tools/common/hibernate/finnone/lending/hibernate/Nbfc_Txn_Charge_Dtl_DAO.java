package com.pruvn.tools.common.hibernate.finnone.lending.hibernate;

import java.math.BigDecimal;

import com.pruvn.tools.common.hibernate.finnone.lending.NBFC_TXN_CHARGE_DTL;
import com.pruvn.tools.printserver.GenericDAO;

public interface Nbfc_Txn_Charge_Dtl_DAO extends GenericDAO<NBFC_TXN_CHARGE_DTL,Integer>{
	BigDecimal getSumChargeByTnxIDAndDescLike(String CHARGEDESC, String TXNID);

}
