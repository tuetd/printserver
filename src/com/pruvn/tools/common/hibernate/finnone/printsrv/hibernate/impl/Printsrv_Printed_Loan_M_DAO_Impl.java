package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.printsrv.PRINTSRV_PRINTED_LOAN_M;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.Printsrv_Printed_Loan_M_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;
public class Printsrv_Printed_Loan_M_DAO_Impl extends AbstractHibernateDAO<PRINTSRV_PRINTED_LOAN_M, Integer> implements Printsrv_Printed_Loan_M_DAO{
	@SuppressWarnings("unchecked")
	public Boolean checkPrintedDoc(PRINTSRV_PRINTED_LOAN_M loan) throws Exception {
		Criteria criteria = getSession().createCriteria(PRINTSRV_PRINTED_LOAN_M.class);		
		List<Object> printed = (List<Object>)criteria.add(Restrictions.eq("APP_ID_C", loan.getAPP_ID_C()))
														.add(Restrictions.eq("DOC_NAME", loan.getDOC_NAME())).list();	
		if (printed != null && printed.size() > 0) {
			throw new Exception("Application form no# : " + loan.getAPP_ID_C() + " is not allowed to re-print !");
		}else {
			return true;
		}
	}

}
