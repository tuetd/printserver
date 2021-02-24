package com.pruvn.rms.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.pruvn.rms.dao.ParamMDAO;
import com.pruvn.rms.domain.ParamM;

/**
 * <p>Hibernate DAO layer for ParamMs</p>
 * <p>Generated at Fri Oct 26 09:59:41 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class ParamMHibernateDAO extends
		AbstractHibernateDAO<ParamM, Integer> implements
		ParamMDAO {

	/**
	 * Find ParamM by paramCode
	 */
	public ParamM findByParamCode(String paramCode) {
		List<ParamM> lst = findByCriteria(Restrictions.eq("paramCode", paramCode));
		if (lst != null && lst.size() > 0)
			return lst.get(0);
		
		return null;
	}
	
	/**
	 * Find ParamM by paramValue
	 */
	public List<ParamM> findByParamValue(String paramValue) {
		return findByCriteria(Restrictions.eq("paramValue", paramValue));
	}
	
	/**
	 * Find ParamM by description
	 */
	public List<ParamM> findByDescription(String description) {
		return findByCriteria(Restrictions.eq("description", description));
	}
	

}
