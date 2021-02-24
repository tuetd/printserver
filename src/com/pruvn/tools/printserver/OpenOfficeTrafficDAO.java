package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.OpenOfficeTraffic;
/**
 * <p>Generic DAO layer for OpenOfficeTraffics</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface OpenOfficeTrafficDAO extends GenericDAO<OpenOfficeTraffic,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildOpenOfficeTrafficDAO()
	 */
	  	 
	/**
	 * Find OpenOfficeTraffic by servername
	 */
	public List<OpenOfficeTraffic> findByServername(String servername);

	/**
	 * Find OpenOfficeTraffic by maxConn
	 */
	public List<OpenOfficeTraffic> findByMaxConn(Integer maxConn);

	/**
	 * Find OpenOfficeTraffic by currConn
	 */
	public List<OpenOfficeTraffic> findByCurrConn(Integer currConn);

}