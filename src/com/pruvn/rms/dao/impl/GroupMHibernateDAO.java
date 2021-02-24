package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.GroupMDAO;
import com.pruvn.rms.domain.GroupM;

/**
 * <p>Hibernate DAO layer for GroupMs</p>
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class GroupMHibernateDAO extends
		AbstractHibernateDAO<GroupM, Integer> implements
		GroupMDAO {

	/**
	 * Find GroupM by groupcode
	 */
	public List<GroupM> findByGroupcode(String groupcode) {
		return findByCriteria(Restrictions.eq("groupcode", groupcode));
	}
	
	/**
	 * Find GroupM by groupname
	 */
	public List<GroupM> findByGroupname(String groupname) {
		return findByCriteria(Restrictions.eq("groupname", groupname));
	}
	
	/**
	 * Find GroupM by groupdesc
	 */
	public List<GroupM> findByGroupdesc(String groupdesc) {
		return findByCriteria(Restrictions.eq("groupdesc", groupdesc));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GroupM> findByGroupcode(final List<String> permissionlist) {
		return (List<GroupM>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	String sql = "from GroupM where groupcode in (:permissionList)";
            	Query query  = session.createQuery(sql);
            	query.setParameterList("permissionList", permissionlist);
                return query.list();
            }
        });
	}
	

}
