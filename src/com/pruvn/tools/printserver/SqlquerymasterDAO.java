package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Sqlquerymaster;
/**
 * <p>Generic DAO layer for Sqlquerymasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface SqlquerymasterDAO extends GenericDAO<Sqlquerymaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildSqlquerymasterDAO()
	 */
	  	 
	/**
	 * Find Sqlquerymaster by value
	 */
	public List<Sqlquerymaster> findByValue(String value);

	/**
	 * Find Sqlquerymaster by datasourceid
	 */
	public List<Sqlquerymaster> findByDatasourceid(Integer datasourceid);

	/**
	 * Find Sqlquerymaster by type
	 */
	public List<Sqlquerymaster> findByType(Integer type);

	/**
	 * Find Sqlquerymaster by indexnum
	 */
	public List<Sqlquerymaster> findByIndexnum(Integer indexnum);

	/**
	 * Find Sqlquerymaster by queryname
	 */
	public List<Sqlquerymaster> findByQueryname(String queryname);

}