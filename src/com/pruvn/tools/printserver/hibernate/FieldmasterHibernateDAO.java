package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fieldmaster;
import com.pruvn.tools.printserver.FieldmasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Fieldmasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FieldmasterHibernateDAO extends
		AbstractHibernateDAO<Fieldmaster, Integer> implements
		FieldmasterDAO {

	/**
	 * Find Fieldmaster by name
	 */
	public List<Fieldmaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Fieldmaster by typeid
	 */
	public List<Fieldmaster> findByTypeid(Integer typeid) {
		return findByCriteria(Restrictions.eq("typeid", typeid));
	}
	
	/**
	 * Find Fieldmaster by datasourceid
	 */
	public List<Fieldmaster> findByDatasourceid(Integer datasourceid) {
		return findByCriteria(Restrictions.eq("datasourceid", datasourceid));
	}
	
	/**
	 * Find Fieldmaster by sqlqueryid
	 */
	public List<Fieldmaster> findBySqlqueryid(Integer sqlqueryid) {
		return findByCriteria(Restrictions.eq("sqlqueryid", sqlqueryid));
	}
	

}
