package com.pruvn.rms.dao;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.AutoDebit;
import com.pruvn.rms.domain.DocumentMaintenance;
/**
 * <p>Generic DAO layer for GroupMs</p>
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface AutoDebitDAO extends GenericDAO<AutoDebit,Integer> {

	List<AutoDebit> findAll_AD(final String username, final String stage, final Map<String, Object> filters) ;
	
	List<DocumentMaintenance> getAllDocument(final String username, final String stage, final Map<String, Object> filters) ;
	
	int countAll_AD(final String username, final String stage, final Map<String, Object> filters) ;
	AutoDebit findByID(final Long id);
	List<AutoDebit> getlistBank() ;
	List<AutoDebit> getlistBranch() ;
}