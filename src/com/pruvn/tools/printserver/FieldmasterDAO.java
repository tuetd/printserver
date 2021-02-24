package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fieldmaster;
/**
 * <p>Generic DAO layer for Fieldmasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface FieldmasterDAO extends GenericDAO<Fieldmaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildFieldmasterDAO()
	 */
	  	 
	/**
	 * Find Fieldmaster by name
	 */
	public List<Fieldmaster> findByName(String name);

	/**
	 * Find Fieldmaster by typeid
	 */
	public List<Fieldmaster> findByTypeid(Integer typeid);

	/**
	 * Find Fieldmaster by datasourceid
	 */
	public List<Fieldmaster> findByDatasourceid(Integer datasourceid);

	/**
	 * Find Fieldmaster by sqlqueryid
	 */
	public List<Fieldmaster> findBySqlqueryid(Integer sqlqueryid);

}