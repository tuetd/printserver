package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fileparamfilelink;
import com.pruvn.tools.printserver.pojo.Fileparamfilelink.FileparamfilelinkPK;
/**
 * <p>Generic DAO layer for Fileparamfilelinks</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface FileparamfilelinkDAO extends GenericDAO<Fileparamfilelink,FileparamfilelinkPK> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildFileparamfilelinkDAO()
	 */
	  	 
	/**
	 * Find Fileparamfilelink by fileid
	 */
	public List<Fileparamfilelink> findByFileid(Integer fileid);

	/**
	 * Find Fileparamfilelink by fileparamid
	 */
	public List<Fileparamfilelink> findByFileparamid(Integer fileparamid);

	/**
	 * Find Fileparamfilelink by value
	 */
	public List<Fileparamfilelink> findByValue(String value);

}