package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Datasourcemaster;
import com.pruvn.tools.printserver.DatasourcemasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Datasourcemasters</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class DatasourcemasterHibernateDAO extends
		AbstractHibernateDAO<Datasourcemaster, Integer> implements
		DatasourcemasterDAO {

	/**
	 * Find Datasourcemaster by name
	 */
	public List<Datasourcemaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Datasourcemaster by fileid
	 */
	public List<Datasourcemaster> findByFileid(Integer fileid) {
		return findByCriteria(Restrictions.eq("fileid", fileid));
	}
	
	/**
	 * Find Datasourcemaster by serverconfigid
	 */
	public List<Datasourcemaster> findByServerconfigid(Integer serverconfigid) {
		return findByCriteria(Restrictions.eq("serverconfigid", serverconfigid));
	}
	

}
