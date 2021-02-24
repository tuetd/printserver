package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.UserGroupADAO;
import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.UserGroupA;
import com.pruvn.rms.domain.UserM;

/**
 * <p>
 * Hibernate DAO layer for UserGroupAs
 * </p>
 * <p>
 * Generated at Mon Jul 11 15:00:10 ICT 2011
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserGroupAHibernateDAO extends
		AbstractHibernateDAO<UserGroupA, Integer> implements UserGroupADAO {

	/**
	 * Find UserGroupA by userid
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserGroupA> findByUser(final UserM user) {
		return (List<UserGroupA>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("from UserGroupA u join fetch u.group Where u.user = :user");
        		query.setParameter("user", user);
        		return query.list();
            }
        });
	}

	/**
	 * Find UserGroupA by groupid
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserGroupA> findByGroup(final GroupM group) {
		/*List<UserGroupA> list = findByCriteria(Restrictions.eq("group", group));
		for (UserGroupA userGroupA : list) {
			Hibernate.initialize(userGroupA.getGroup());
			Hibernate.initialize(userGroupA.getUser());
		}
		return list;*/
		return (List<UserGroupA>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("from UserGroupA u join fetch u.user Where u.group = :group");
        		query.setParameter("group", group);
        		return query.list();
            }
        });
	}

}
