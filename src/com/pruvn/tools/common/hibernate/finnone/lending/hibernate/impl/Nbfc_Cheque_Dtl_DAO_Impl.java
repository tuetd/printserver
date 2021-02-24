package com.pruvn.tools.common.hibernate.finnone.lending.hibernate.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.lending.NBFC_CHEQUE_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Nbfc_Cheque_Dtl_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Nbfc_Cheque_Dtl_DAO_Impl extends AbstractHibernateDAO<NBFC_CHEQUE_DTL, Integer> implements Nbfc_Cheque_Dtl_DAO{

	@SuppressWarnings("unchecked")
	public List<NBFC_CHEQUE_DTL> getChequeListByCustIDLikeChequeNumOrderByChequeDate(
			Integer BPID, String CHEQUENUM) {
		Criteria criteria = getSession().createCriteria(NBFC_CHEQUE_DTL.class);		
		return (List<NBFC_CHEQUE_DTL>)criteria.add(Restrictions.eq("BPID", BPID))
											.add(Restrictions.like("CHEQUENUM", "%" + CHEQUENUM + "%"))
											.addOrder(Order.asc("CHEQUEDATE")).list();
	}

}
