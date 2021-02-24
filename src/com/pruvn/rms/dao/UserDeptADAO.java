package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.domain.UserDeptA;
import com.pruvn.rms.domain.UserM;
/**
 * <p>Generic DAO layer for UserDeptAs</p>
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface UserDeptADAO extends GenericDAO<UserDeptA,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildUserDeptADAO()
	 */
	  	 
	/**
	 * Find UserDeptA by userId
	 */
	public List<UserDeptA> findByUser(UserM user);

	/**
	 * Find UserDeptA by deptId
	 */
	public List<UserDeptA> findByDept(DeptM dept);
	
	public UserDeptA getUserDeptByUsername(String username);
	
	public void deleteByUserId(Integer userId);

}