package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Bookmarkmaster;
/**
 * <p>Generic DAO layer for Bookmarkmasters</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface BookmarkmasterDAO extends GenericDAO<Bookmarkmaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildBookmarkmasterDAO()
	 */
	  	 
	/**
	 * Find Bookmarkmaster by name
	 */
	public List<Bookmarkmaster> findByName(String name);

	/**
	 * Find Bookmarkmaster by docid
	 */
	public List<Bookmarkmaster> findByDocid(Integer docid);

	/**
	 * Find Bookmarkmaster by fieldid
	 */
	public List<Bookmarkmaster> findByFieldid(Integer fieldid);

	/**
	 * Find Bookmarkmaster by function
	 */
	public List<Bookmarkmaster> findByFunction(String function);

	/**
	 * Find Bookmarkmaster by format
	 */
	public List<Bookmarkmaster> findByFormat(String format);

}