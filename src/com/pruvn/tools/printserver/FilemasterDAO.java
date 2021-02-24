package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Filemaster;
/**
 * <p>Generic DAO layer for Filemasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface FilemasterDAO extends GenericDAO<Filemaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildFilemasterDAO()
	 */
	  	 
	/**
	 * Find Filemaster by filepath
	 */
	public List<Filemaster> findByFilepath(String filepath);

	/**
	 * Find Filemaster by sheetnumber
	 */
	public List<Filemaster> findBySheetnumber(Byte sheetnumber);

	/**
	 * Find Filemaster by startindex
	 */
	public List<Filemaster> findByStartindex(Byte startindex);

	/**
	 * Find Filemaster by name
	 */
	public List<Filemaster> findByName(String name);

	/**
	 * Find Filemaster by typeid
	 */
	public List<Filemaster> findByTypeid(Integer typeid);

	/**
	 * Find Filemaster by projectid
	 */
	public List<Filemaster> findByProjectid(Integer projectid);

}