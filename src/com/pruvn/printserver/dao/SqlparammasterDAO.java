package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.Docmaster;
import com.pruvn.printserver.entity.Sqlparammaster;
/**
 * <p>Generic DAO layer for Sqlparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface SqlparammasterDAO extends GenericDAO<Sqlparammaster,Long> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildSqlparammasterDAO()
	 */
	  	 
	public List<Sqlparammaster> findByNameSqlPara(String name);
	public List<Sqlparammaster> findByFriendlyname(String friendlyname);
	public List<Sqlparammaster> findByTypeid(Long typeid);
	public List<Sqlparammaster> findByDocid(Long datasourceid);
	public List<Sqlparammaster> findByFieldtype(String fieldtype);

}