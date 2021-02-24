package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Fileparammaster;
/**
 * <p>Generic DAO layer for Fileparammasters</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface FileparammasterDAO extends GenericDAO<Fileparammaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildFileparammasterDAO()
	 */
	  	 
	/**
	 * Find Fileparammaster by name
	 */
	public List<Fileparammaster> findByName(String name);

	/**
	 * Find Fileparammaster by filetypeid
	 */
	public List<Fileparammaster> findByFiletypeid(Integer filetypeid);

}