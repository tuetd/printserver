package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.LoanPurposeMDAO;
import com.pruvn.printserver.entity.LoanPurposeM;


public class LoanPurposeMDAOImpl extends HibernateGenericDAO<LoanPurposeM, String> implements LoanPurposeMDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanPurposeM> findByPurposeCode(String purpose) {
		Criteria criteria = getSession().createCriteria(LoanPurposeM.class);
		criteria.add(Restrictions.eq("system_core", purpose));
		return criteria.list();
	}

	
	

}
