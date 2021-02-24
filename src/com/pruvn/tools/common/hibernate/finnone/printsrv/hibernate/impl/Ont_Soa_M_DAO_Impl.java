package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.printsrv.ONT_SOA_M;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.Ont_Soa_M_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Ont_Soa_M_DAO_Impl extends AbstractHibernateDAO<ONT_SOA_M, Integer> implements Ont_Soa_M_DAO{

	public ONT_SOA_M getOnTSOAMByAgreementNo(String agreementno) {
		Criteria criteria = getSession().createCriteria(ONT_SOA_M.class);		
		return (ONT_SOA_M)criteria.add(Restrictions.eq("AGREEMENTNO", agreementno)).uniqueResult();			
	}

}
