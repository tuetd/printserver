package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Bookmarkmaster;
import com.pruvn.tools.printserver.BookmarkmasterDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Bookmarkmasters</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class BookmarkmasterHibernateDAO extends
		AbstractHibernateDAO<Bookmarkmaster, Integer> implements
		BookmarkmasterDAO {

	/**
	 * Find Bookmarkmaster by name
	 */
	public List<Bookmarkmaster> findByName(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	/**
	 * Find Bookmarkmaster by docid
	 */
	public List<Bookmarkmaster> findByDocid(Integer docid) {
		return findByCriteria(Restrictions.eq("docid", docid));
	}
	
	/**
	 * Find Bookmarkmaster by fieldid
	 */
	public List<Bookmarkmaster> findByFieldid(Integer fieldid) {
		return findByCriteria(Restrictions.eq("fieldid", fieldid));
	}
	
	/**
	 * Find Bookmarkmaster by function
	 */
	public List<Bookmarkmaster> findByFunction(String function) {
		return findByCriteria(Restrictions.eq("function", function));
	}
	
	/**
	 * Find Bookmarkmaster by format
	 */
	public List<Bookmarkmaster> findByFormat(String format) {
		return findByCriteria(Restrictions.eq("format", format));
	}
	

}
