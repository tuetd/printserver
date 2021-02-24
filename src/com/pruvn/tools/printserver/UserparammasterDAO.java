package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Userparammaster;
/**
 * <p>Generic DAO layer for Userparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface UserparammasterDAO extends GenericDAO<Userparammaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildUserparammasterDAO()
	 */
	  	 
	/**
	 * Find Userparammaster by name
	 */
	public List<Userparammaster> findByName(String name);

	/**
	 * Find Userparammaster by description
	 */
	public List<Userparammaster> findByDescription(String description);

}