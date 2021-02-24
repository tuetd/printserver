package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Filemaster;
import com.pruvn.tools.printserver.FilemasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Filemasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FilemasterHibernateDAO extends
		AbstractHibernateDAO<Filemaster, Integer> implements
		FilemasterDAO {

	/**
	 * Find Filemaster by filepath
	 */
	public List<Filemaster> findByFilepath(String filepath) {
		return findByCriteria(Restrictions.eq("filepath", filepath));
	}
	
	/**
	 * Find Filemaster by sheetnumber
	 */
	public List<Filemaster> findBySheetnumber(Byte sheetnumber) {
		return findByCriteria(Restrictions.eq("sheetnumber", sheetnumber));
	}
	
	/**
	 * Find Filemaster by startindex
	 */
	public List<Filemaster> findByStartindex(Byte startindex) {
		return findByCriteria(Restrictions.eq("startindex", startindex));
	}
	
	/**
	 * Find Filemaster by name
	 */
	public List<Filemaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Filemaster by typeid
	 */
	public List<Filemaster> findByTypeid(Integer typeid) {
		return findByCriteria(Restrictions.eq("typeid", typeid));
	}
	
	/**
	 * Find Filemaster by projectid
	 */
	public List<Filemaster> findByProjectid(Integer projectid) {
		return findByCriteria(Restrictions.eq("projectid", projectid));
	}
	

}
