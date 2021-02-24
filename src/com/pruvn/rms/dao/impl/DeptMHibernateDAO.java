package com.pruvn.rms.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.pruvn.rms.dao.DeptMDAO;
import com.pruvn.rms.domain.DeptM;

/**
 * <p>Hibernate DAO layer for DeptMs</p>
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class DeptMHibernateDAO extends
		AbstractHibernateDAO<DeptM, Integer> implements
		DeptMDAO {

	/**
	 * Find DeptM by deptcode
	 */
	public List<DeptM> findByDeptcode(String deptcode) {
		return findByCriteria(Restrictions.eq("deptcode", deptcode));
	}
	
	/**
	 * Find DeptM by deptname
	 */
	public List<DeptM> findByDeptname(String deptname) {
		return findByCriteria(Restrictions.eq("deptname", deptname));
	}
	

}
