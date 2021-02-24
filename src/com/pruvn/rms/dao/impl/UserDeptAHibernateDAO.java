package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.UserDeptADAO;
import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.domain.RecordWait;
import com.pruvn.rms.domain.UserDeptA;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for UserDeptAs</p>
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserDeptAHibernateDAO extends
		AbstractHibernateDAO<UserDeptA, Integer> implements
		UserDeptADAO {

	/**
	 * Find UserDeptA by userId
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserDeptA> findByUser(final UserM user) {
		//return findByCriteria(Restrictions.eq("user", user));
		return (List<UserDeptA>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("from UserDeptA u join fetch u.dept Where u.user = :user");
        		query.setParameter("user", user);
        		return query.list();
            }
        });
	}
	
	/**
	 * Find UserDeptA by deptId
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserDeptA> findByDept(final DeptM dept) {
		//return findByCriteria(Restrictions.eq("dept", dept));
		return (List<UserDeptA>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		Query query  = session.createQuery("from UserDeptA u join fetch u.user Where u.dept = :dept");
        		query.setParameter("dept", dept);
        		return query.list();
            }
        });
	}

	@Override
	public UserDeptA getUserDeptByUsername(String username) {
		String query = "from UserDeptA where user.username = :username";
		String[] namedParams = new String[]{"username"};
		Object[] params = new Object[]{username};
		List<UserDeptA> list = executeQuery(query, namedParams, params);
		if (list != null && list.size() > 0 ) {
			return list.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void deleteByUserId(final Integer userId) {
		getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	SQLQuery query =session.createSQLQuery("DELETE FROM USER_DEPT_A WHERE USER_ID = " + userId);
        		return query.executeUpdate();
            }
        });
	}
}