package com.pruvn.tools.common.hibernate.finnone.cas.hibernate.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.cas.NBFC_PRODUCT_M;
import com.pruvn.tools.common.hibernate.finnone.cas.hibernate.Nbfc_Product_M_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Nbfc_Product_M_DAO_Impl extends AbstractHibernateDAO<NBFC_PRODUCT_M, Integer> implements Nbfc_Product_M_DAO{

	public NBFC_PRODUCT_M getProductByCode(String CODE) {
		Criteria criteria = getSession().createCriteria(NBFC_PRODUCT_M.class);		
		return (NBFC_PRODUCT_M)criteria.add(Restrictions.eq("CODE", CODE)).uniqueResult();
	}

}
