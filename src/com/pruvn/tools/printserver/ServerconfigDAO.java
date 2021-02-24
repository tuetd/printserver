package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Serverconfig;
/**
 * <p>Generic DAO layer for Serverconfigs</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface ServerconfigDAO extends GenericDAO<Serverconfig,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildServerconfigDAO()
	 */
	  	 
	/**
	 * Find Serverconfig by servername
	 */
	public List<Serverconfig> findByServername(String servername);

	/**
	 * Find Serverconfig by port
	 */
	public List<Serverconfig> findByPort(String port);

	/**
	 * Find Serverconfig by dbname
	 */
	public List<Serverconfig> findByDbname(String dbname);

	/**
	 * Find Serverconfig by username
	 */
	public List<Serverconfig> findByUsername(String username);

	/**
	 * Find Serverconfig by password
	 */
	public List<Serverconfig> findByPassword(String password);

	/**
	 * Find Serverconfig by systemname
	 */
	public List<Serverconfig> findBySystemname(String systemname);

	/**
	 * Find Serverconfig by typeid
	 */
	public List<Serverconfig> findByTypeid(Integer typeid);

}