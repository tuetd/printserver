package com.pruvn.rms.dao;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.RecordScanCM;
/**
 * <p>Generic DAO layer for RecordScanCM</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface RecordScanCMDAO extends GenericDAO<RecordScanCM,Integer> {
	public List<RecordScanCM> findAllRecordScanCM_ACL(String username,String stage, Map<String, Object> filters);
}