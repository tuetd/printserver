package com.pruvn.rms.dao;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordCompleted;
/**
 * <p>Generic DAO layer for Records</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface CSRecordCompletedDAO extends GenericDAO<CSRecordCompleted,Integer> {
	public List<CSRecordCompleted> findAll_ACL(String username,String stage, Map<String, Object> filters);

	public CSRecord getCSRecordCompletedByIdAndCusName(String loanID,
			String customerName);
}