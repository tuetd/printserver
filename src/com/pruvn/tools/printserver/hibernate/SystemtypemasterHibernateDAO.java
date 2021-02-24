package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Systemtypemaster;
import com.pruvn.tools.printserver.SystemtypemasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Systemtypemasters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class SystemtypemasterHibernateDAO extends
		AbstractHibernateDAO<Systemtypemaster, Integer> implements
		SystemtypemasterDAO {

	/**
	 * Find Systemtypemaster by name
	 */
	public List<Systemtypemaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	

}
