package com.pruvn.tools.common.hibernate.finnone.lending.hibernate.impl;

import java.math.BigDecimal;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.lending.NBFC_TXN_CHARGE_DTL;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Nbfc_Txn_Charge_Dtl_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Nbfc_Txn_Charge_Dtl_DAO_Impl extends AbstractHibernateDAO<NBFC_TXN_CHARGE_DTL, Integer> implements Nbfc_Txn_Charge_Dtl_DAO{

	public BigDecimal getSumChargeByTnxIDAndDescLike(String CHARGEDESC,
			String TXNID) {
		Criteria criteria = getSession().createCriteria(NBFC_TXN_CHARGE_DTL.class);		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.sum("CHARGEAMT"));		
		BigDecimal sum = (BigDecimal)criteria.add(Restrictions.eq("TXNID", TXNID))
										.add(Restrictions.like("CHARGEDESC", "%" + CHARGEDESC + "%"))
										.setProjection(projectionList).uniqueResult();
		if (null == sum) {
			sum = BigDecimal.ZERO;
		}
		return sum;
	}

}
