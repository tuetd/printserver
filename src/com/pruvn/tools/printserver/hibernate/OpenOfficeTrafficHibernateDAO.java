package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.OpenOfficeTraffic;
import com.pruvn.tools.printserver.OpenOfficeTrafficDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for OpenOfficeTraffics</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class OpenOfficeTrafficHibernateDAO extends
		AbstractHibernateDAO<OpenOfficeTraffic, Integer> implements
		OpenOfficeTrafficDAO {

	/**
	 * Find OpenOfficeTraffic by servername
	 */
	public List<OpenOfficeTraffic> findByServername(String servername) {
		return findByCriteria(Restrictions.eq("servername", servername));
	}
	
	/**
	 * Find OpenOfficeTraffic by maxConn
	 */
	public List<OpenOfficeTraffic> findByMaxConn(Integer maxConn) {
		return findByCriteria(Restrictions.eq("maxConn", maxConn));
	}
	
	/**
	 * Find OpenOfficeTraffic by currConn
	 */
	public List<OpenOfficeTraffic> findByCurrConn(Integer currConn) {
		return findByCriteria(Restrictions.eq("currConn", currConn));
	}
	

}
