package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.DeptM;
/**
 * <p>Generic DAO layer for DeptMs</p>
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface DeptMDAO extends GenericDAO<DeptM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildDeptMDAO()
	 */
	  	 
	/**
	 * Find DeptM by deptcode
	 */
	public List<DeptM> findByDeptcode(String deptcode);

	/**
	 * Find DeptM by deptname
	 */
	public List<DeptM> findByDeptname(String deptname);

}