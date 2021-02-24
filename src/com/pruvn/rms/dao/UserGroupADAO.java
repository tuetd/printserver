package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.UserGroupA;
import com.pruvn.rms.domain.UserM;
/**
 * <p>Generic DAO layer for UserGroupAs</p>
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface UserGroupADAO extends GenericDAO<UserGroupA,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildUserGroupADAO()
	 */
	  	 
	/**
	 * Find UserGroupA by userid
	 */
	public List<UserGroupA> findByUser(UserM user);

	/**
	 * Find UserGroupA by groupid
	 */
	public List<UserGroupA> findByGroup(GroupM group);

}