package com.pruvn.tools.common.hibernate.finnone.lending.hibernate.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.lending.LEA_AGREEMENT_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Lea_Agreement_Dtl_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Lea_Agreement_Dtl_DAO_Impl extends AbstractHibernateDAO<LEA_AGREEMENT_DTL, Integer> implements Lea_Agreement_Dtl_DAO{

	public LEA_AGREEMENT_DTL getAgreementByNo(String agreementno) {
		Criteria criteria = getSession().createCriteria(LEA_AGREEMENT_DTL.class);		
		return (LEA_AGREEMENT_DTL)criteria.add(Restrictions.eq("AGREEMENTNO", agreementno)).uniqueResult();
	}

}
