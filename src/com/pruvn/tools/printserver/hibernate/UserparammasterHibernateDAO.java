package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Userparammaster;
import com.pruvn.tools.printserver.UserparammasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Userparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserparammasterHibernateDAO extends
		AbstractHibernateDAO<Userparammaster, Integer> implements
		UserparammasterDAO {

	/**
	 * Find Userparammaster by name
	 */
	public List<Userparammaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Userparammaster by description
	 */
	public List<Userparammaster> findByDescription(String description) {
		return findByCriteria(Restrictions.eq("description", description));
	}
	

}
