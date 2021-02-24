package com.pruvn.rms.dao;

import com.pruvn.rms.domain.finnbank.Cm_doc_printed_trial;
/**
 * <p>Generic DAO layer for DeptMs</p>
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface Cm_doc_printed_trialDAO extends GenericDAO<Cm_doc_printed_trial,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildDeptMDAO()
	 */	  	
	Boolean findByLoanNo(String loanNo);
}