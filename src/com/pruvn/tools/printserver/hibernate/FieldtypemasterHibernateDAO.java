package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fieldtypemaster;
import com.pruvn.tools.printserver.FieldtypemasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Fieldtypemasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FieldtypemasterHibernateDAO extends
		AbstractHibernateDAO<Fieldtypemaster, Integer> implements
		FieldtypemasterDAO {

	/**
	 * Find Fieldtypemaster by name
	 */
	public List<Fieldtypemaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	

}
