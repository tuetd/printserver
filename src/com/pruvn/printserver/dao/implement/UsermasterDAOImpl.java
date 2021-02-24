package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.UsermasterDAO;
import com.pruvn.printserver.entity.Usermaster;


public class UsermasterDAOImpl extends HibernateGenericDAO<Usermaster, Long> implements UsermasterDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Usermaster> findByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Usermaster.class);
		criteria.add(Restrictions.eq("username", username));
		return criteria.list();
	}
}
