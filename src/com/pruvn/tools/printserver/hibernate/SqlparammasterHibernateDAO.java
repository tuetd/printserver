package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Sqlparammaster;
import com.pruvn.tools.printserver.SqlparammasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Sqlparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class SqlparammasterHibernateDAO extends
		AbstractHibernateDAO<Sqlparammaster, Integer> implements
		SqlparammasterDAO {

	/**
	 * Find Sqlparammaster by name
	 */
	public List<Sqlparammaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Sqlparammaster by friendlyname
	 */
	public List<Sqlparammaster> findByFriendlyname(String friendlyname) {
		return findByCriteria(Restrictions.eq("friendlyname", friendlyname));
	}
	
	/**
	 * Find Sqlparammaster by typeid
	 */
	public List<Sqlparammaster> findByTypeid(Integer typeid) {
		return findByCriteria(Restrictions.eq("typeid", typeid));
	}
	
	/**
	 * Find Sqlparammaster by datasourceid
	 */
	public List<Sqlparammaster> findByDatasourceid(Integer datasourceid) {
		return findByCriteria(Restrictions.eq("datasourceid", datasourceid));
	}
	
	/**
	 * Find Sqlparammaster by fieldtype
	 */
	public List<Sqlparammaster> findByFieldtype(String fieldtype) {
		return findByCriteria(Restrictions.eq("fieldtype", fieldtype));
	}
	

}
