package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.UserRoleMDAO;
import com.pruvn.printserver.entity.UserRoleM;


public class UserRoleMDAOImpl extends HibernateGenericDAO<UserRoleM, Long> implements UserRoleMDAO{

	@Override
	@SuppressWarnings("unchecked")
	public List<UserRoleM> getRole(Long code) {
		Criteria criteria = getSession().createCriteria(UserRoleM.class);
		criteria.add(Restrictions.eq("id", code));
		return criteria.list();
	}
	
	

}
