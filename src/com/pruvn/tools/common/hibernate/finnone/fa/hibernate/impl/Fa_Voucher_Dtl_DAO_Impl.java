package com.pruvn.tools.common.hibernate.finnone.fa.hibernate.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.pruvn.tools.common.hibernate.finnone.fa.FA_VOUCHER_DTL;
import com.pruvn.tools.common.hibernate.finnone.fa.hibernate.Fa_Voucher_Dtl_DAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class Fa_Voucher_Dtl_DAO_Impl extends AbstractHibernateDAO<FA_VOUCHER_DTL, Integer> implements Fa_Voucher_Dtl_DAO{

	@SuppressWarnings("unchecked")
	public List<FA_VOUCHER_DTL> getVoucherDtlList(Integer chequeid) {
		Criteria criteria = getSession().createCriteria(FA_VOUCHER_DTL.class);
		return (List<FA_VOUCHER_DTL>)criteria.add(Restrictions.eq("chequeid", chequeid))
										     .addOrder(Order.desc("voucherdate")).list();		
	}

}
