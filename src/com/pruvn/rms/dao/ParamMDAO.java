package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.ParamM;
/**
 * <p>Generic DAO layer for ParamMs</p>
 * <p>Generated at Fri Oct 26 09:59:41 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface ParamMDAO extends GenericDAO<ParamM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildParamMDAO()
	 */
	  	 
	/**
	 * Find ParamM by paramCode
	 */
	public ParamM findByParamCode(String paramCode);

	/**
	 * Find ParamM by paramValue
	 */
	public List<ParamM> findByParamValue(String paramValue);

	/**
	 * Find ParamM by description
	 */
	public List<ParamM> findByDescription(String description);

}