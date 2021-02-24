package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.Types;

import com.pruvn.tools.printserver.pojo.Docmaster;
import com.pruvn.tools.printserver.DocmasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Docmasters</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class DocmasterHibernateDAO extends
		AbstractHibernateDAO<Docmaster, Integer> implements
		DocmasterDAO {

	/**
	 * Find Docmaster by name
	 */
	public List<Docmaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Docmaster by startpage
	 */
	public List<Docmaster> findByStartpage(Byte startpage) {
		return findByCriteria(Restrictions.eq("startpage", startpage));
	}
	
	/**
	 * Find Docmaster by endpage
	 */
	public List<Docmaster> findByEndpage(Byte endpage) {
		return findByCriteria(Restrictions.eq("endpage", endpage));
	}
	
	/**
	 * Find Docmaster by templatefile
	 */
	public List<Docmaster> findByTemplatefile(Integer templatefile) {
		return findByCriteria(Restrictions.eq("templatefile", templatefile));
	}
	
	/**
	 * Find Docmaster by serverfile
	 */
	public List<Docmaster> findByServerfile(String serverfile) {
		return findByCriteria(Restrictions.eq("serverfile", serverfile));
	}
	
	/**
	 * Find Docmaster by localfile
	 */
	public List<Docmaster> findByLocalfile(String localfile) {
		return findByCriteria(Restrictions.eq("localfile", localfile));
	}
	
	/**
	 * Find Docmaster by datasourceId
	 */
	public List<Docmaster> findByDatasourceId(Integer datasourceId) {
		return findByCriteria(Restrictions.eq("datasourceId", datasourceId));
	}
}
