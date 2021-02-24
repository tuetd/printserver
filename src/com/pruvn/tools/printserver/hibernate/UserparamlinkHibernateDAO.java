package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Userparamlink;
import com.pruvn.tools.printserver.UserparamlinkDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Userparamlinks</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserparamlinkHibernateDAO extends
		AbstractHibernateDAO<Userparamlink, Integer> implements
		UserparamlinkDAO {

	/**
	 * Find Userparamlink by paramid
	 */
	public List<Userparamlink> findByParamid(Integer paramid) {
		return findByCriteria(Restrictions.eq("paramid", paramid));
	}
	
	/**
	 * Find Userparamlink by value
	 */
	public List<Userparamlink> findByValue(String value) {
		return findByCriteria(Restrictions.eq("value", value));
	}
	

}
