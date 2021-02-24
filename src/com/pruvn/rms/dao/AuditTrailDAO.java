package com.pruvn.rms.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pruvn.rms.domain.AuditTrail;
/**
 * <p>Generic DAO layer for AuditTrails</p>
 * <p>Generated at Thu Sep 29 10:44:42 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface AuditTrailDAO extends GenericDAO<AuditTrail,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildAuditTrailDAO()
	 */
	  	 
	/**
	 * Find AuditTrail by username
	 */
	public List<AuditTrail> findByUsername(String username);

	/**
	 * Find AuditTrail by message
	 */
	public List<AuditTrail> findByMessage(String message);

	/**
	 * Find AuditTrail by sendtime
	 */
	public List<AuditTrail> findBySendtime(Timestamp sendtime);

	/**
	 * Find AuditTrail by sendtonumber
	 */
	public List<AuditTrail> findBySendtonumber(String sendtonumber);

}