package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;


import com.pruvn.tools.printserver.pojo.Parammaster;
/**
 * <p>Generic DAO layer for Parammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface ParammasterDAO extends GenericDAO<Parammaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildParammasterDAO()
	 */
	  	 
	/**
	 * Find Parammaster by name
	 */
	public List<Parammaster> findByName(String name);

	/**
	 * Find Parammaster by value
	 */
	public List<Parammaster> findByValue(String value);

	/**
	 * Find Parammaster by description
	 */
	public List<Parammaster> findByDescription(String description);
	public Parammaster getParamByName(String NAME);

}