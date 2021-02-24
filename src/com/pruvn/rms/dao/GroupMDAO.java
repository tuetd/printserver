package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.GroupM;
/**
 * <p>Generic DAO layer for GroupMs</p>
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface GroupMDAO extends GenericDAO<GroupM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildGroupMDAO()
	 */
	  	 
	/**
	 * Find GroupM by groupcode
	 */
	public List<GroupM> findByGroupcode(String groupcode);

	/**
	 * Find GroupM by groupname
	 */
	public List<GroupM> findByGroupname(String groupname);

	/**
	 * Find GroupM by groupdesc
	 */
	public List<GroupM> findByGroupdesc(String groupdesc);

	public List<GroupM> findByGroupcode(List<String> permissionlist);

}