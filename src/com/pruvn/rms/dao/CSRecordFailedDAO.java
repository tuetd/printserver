package com.pruvn.rms.dao;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordFailed;
/**
 * <p>Generic DAO layer for Records</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface CSRecordFailedDAO extends GenericDAO<CSRecordFailed,Integer> {
	public List<CSRecordFailed> findAll_ACL(String username,String stage, Map<String, Object> filters);

	public CSRecord getCSRecordFailedByIdAndCusName(String loanID,
			String customerName);
}