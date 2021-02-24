package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.BankAccMDAO;
import com.pruvn.printserver.entity.BankAccM;
import com.pruvn.printserver.entity.Docmaster;


public class BankAccMDAOImpl extends HibernateGenericDAO<BankAccM, Long> implements BankAccMDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccM> findByBankCode(String bank_CODE) {
		// TODO Auto-generated method stub
			Criteria criteria = getSession().createCriteria(BankAccM.class);
			criteria.add(Restrictions.eq("bank_code", bank_CODE));
			return criteria.list();
	}

	
	

}
