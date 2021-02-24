package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.printsrv.V_WS_LOAN_STATUS;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.V_Ws_Loan_Status_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class V_Ws_Loan_Status_DAO_Impl extends AbstractHibernateDAO<V_WS_LOAN_STATUS, Integer> implements V_Ws_Loan_Status_DAO{

	public V_WS_LOAN_STATUS getWSLoanStatusByApplid(String app_id_c) {
		Criteria criteria = getSession().createCriteria(V_WS_LOAN_STATUS.class);		
		return (V_WS_LOAN_STATUS)criteria.add(Restrictions.eq("APPLID", app_id_c)).uniqueResult();		
	}

}
