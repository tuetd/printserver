package com.pruvn.rms.dao;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.RecordScanCMCheck;
/**
 * <p>Generic DAO layer for RecordScanCMCheck</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface RecordScanCMCheckDAO extends GenericDAO<RecordScanCMCheck,Integer> {
	public List<RecordScanCMCheck> findAllRecordScanCMCheck_ACL(String username,String stage, Map<String, Object> filters);
}