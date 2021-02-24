package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Serverconfig;
import com.pruvn.tools.printserver.ServerconfigDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Serverconfigs</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class ServerconfigHibernateDAO extends
		AbstractHibernateDAO<Serverconfig, Integer> implements
		ServerconfigDAO {

	/**
	 * Find Serverconfig by servername
	 */
	public List<Serverconfig> findByServername(String servername) {
		return findByCriteria(Restrictions.eq("servername", servername));
	}
	
	/**
	 * Find Serverconfig by port
	 */
	public List<Serverconfig> findByPort(String port) {
		return findByCriteria(Restrictions.eq("port", port));
	}
	
	/**
	 * Find Serverconfig by dbname
	 */
	public List<Serverconfig> findByDbname(String dbname) {
		return findByCriteria(Restrictions.eq("dbname", dbname));
	}
	
	/**
	 * Find Serverconfig by username
	 */
	public List<Serverconfig> findByUsername(String username) {
		return findByCriteria(Restrictions.eq("username", username));
	}
	
	/**
	 * Find Serverconfig by password
	 */
	public List<Serverconfig> findByPassword(String password) {
		return findByCriteria(Restrictions.eq("password", password));
	}
	
	/**
	 * Find Serverconfig by systemname
	 */
	public List<Serverconfig> findBySystemname(String systemname) {
		return findByCriteria(Restrictions.eq("systemname", systemname));
	}
	
	/**
	 * Find Serverconfig by typeid
	 */
	public List<Serverconfig> findByTypeid(Integer typeid) {
		return findByCriteria(Restrictions.eq("typeid", typeid));
	}
	

}
