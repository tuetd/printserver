package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Sqlquerymaster;
import com.pruvn.tools.printserver.SqlquerymasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Sqlquerymasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class SqlquerymasterHibernateDAO extends
		AbstractHibernateDAO<Sqlquerymaster, Integer> implements
		SqlquerymasterDAO {

	/**
	 * Find Sqlquerymaster by value
	 */
	public List<Sqlquerymaster> findByValue(String value) {
		return findByCriteria(Restrictions.eq("value", value));
	}
	
	/**
	 * Find Sqlquerymaster by datasourceid
	 */
	public List<Sqlquerymaster> findByDatasourceid(Integer datasourceid) {
		return findByCriteria(Restrictions.eq("datasourceid", datasourceid));
	}
	
	/**
	 * Find Sqlquerymaster by type
	 */
	public List<Sqlquerymaster> findByType(Integer type) {
		return findByCriteria(Restrictions.eq("type", type));
	}
	
	/**
	 * Find Sqlquerymaster by indexnum
	 */
	public List<Sqlquerymaster> findByIndexnum(Integer indexnum) {
		return findByCriteria(Restrictions.eq("indexnum", indexnum));
	}
	
	/**
	 * Find Sqlquerymaster by queryname
	 */
	public List<Sqlquerymaster> findByQueryname(String queryname) {
		return findByCriteria(Restrictions.eq("queryname", queryname));
	}
	

}
