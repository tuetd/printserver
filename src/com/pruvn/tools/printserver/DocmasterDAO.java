package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Docmaster;
/**
 * <p>Generic DAO layer for Docmasters</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface DocmasterDAO extends GenericDAO<Docmaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildDocmasterDAO()
	 */
	  	 
	/**
	 * Find Docmaster by name
	 */
	public List<Docmaster> findByName(String name);

	/**
	 * Find Docmaster by startpage
	 */
	public List<Docmaster> findByStartpage(Byte startpage);

	/**
	 * Find Docmaster by endpage
	 */
	public List<Docmaster> findByEndpage(Byte endpage);

	/**
	 * Find Docmaster by templatefile
	 */
	public List<Docmaster> findByTemplatefile(Integer templatefile);

	/**
	 * Find Docmaster by serverfile
	 */
	public List<Docmaster> findByServerfile(String serverfile);

	/**
	 * Find Docmaster by localfile
	 */
	public List<Docmaster> findByLocalfile(String localfile);

	/**
	 * Find Docmaster by datasourceId
	 */
	public List<Docmaster> findByDatasourceId(Integer datasourceId);
}