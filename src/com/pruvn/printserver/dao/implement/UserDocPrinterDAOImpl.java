package com.pruvn.printserver.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pruvn.printserver.dao.UserDocPrinterDAO;
import com.pruvn.printserver.entity.UserDocPrinter;


public class UserDocPrinterDAOImpl extends HibernateGenericDAO<UserDocPrinter, Long> implements UserDocPrinterDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDocPrinter> findByUserid(Long id) {
		Criteria criteria = getSession().createCriteria(UserDocPrinter.class);
		criteria.add(Restrictions.eq("id.userid", id));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDocPrinter> findByUseridAnDocid(Long userid, Long docid) {
		Criteria criteria = getSession().createCriteria(UserDocPrinter.class);
		criteria.add(Restrictions.eq("id.userid", userid));
		criteria.add(Restrictions.eq("id.docid", docid));
		return criteria.list();
	}


}
