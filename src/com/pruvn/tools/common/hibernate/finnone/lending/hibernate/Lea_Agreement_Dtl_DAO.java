package com.pruvn.tools.common.hibernate.finnone.lending.hibernate;

import com.pruvn.tools.common.hibernate.finnone.lending.LEA_AGREEMENT_DTL;
import com.pruvn.tools.printserver.GenericDAO;

public interface Lea_Agreement_Dtl_DAO extends GenericDAO<LEA_AGREEMENT_DTL,Integer>{
	LEA_AGREEMENT_DTL getAgreementByNo(String agreementno);
}
