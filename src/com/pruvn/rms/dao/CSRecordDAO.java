package com.pruvn.rms.dao;

import com.pruvn.rms.domain.CSRecord;
/**
 * <p>Generic DAO layer for Records</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface CSRecordDAO extends GenericDAO<CSRecord,Integer> {

	//public List<RecordFL> findAll_ACL(String username,String stage);
	
	//public List<Record> findAllToRMT_ACL(String username, String stage);
	
	//public List<Record> findAllReceivedRMT_ACL(String username, String stage);
	
	//public List<Record> findAllReturnRMT_ACL(String username, String stage);
	
	String callInvolve(String action,String username, String stage, Integer recordId, String... values);
	
	//Record getRecordRMTById(Integer recordId);
	
	//Record getRecordRMTReturnById(Integer recordId);
	
	String  synchronizeData();

	CSRecord searchByAgreementNo(String agreementNo);
}