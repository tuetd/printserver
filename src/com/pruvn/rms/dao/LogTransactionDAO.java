package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.LogTransaction;
/**
 * <p>Generic DAO layer for Records</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface LogTransactionDAO extends GenericDAO<LogTransaction,Integer> {

	List<LogTransaction> getByRecordId(Integer recordId);

	List<LogTransaction> getLogTransactions(Integer recordId, String logType);
}