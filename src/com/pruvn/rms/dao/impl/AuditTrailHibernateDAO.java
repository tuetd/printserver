package com.pruvn.rms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.pruvn.rms.dao.AuditTrailDAO;
import com.pruvn.rms.domain.AuditTrail;

/**
 * <p>Hibernate DAO layer for AuditTrails</p>
 * <p>Generated at Thu Sep 29 10:44:42 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class AuditTrailHibernateDAO extends
		AbstractHibernateDAO<AuditTrail, Integer> implements
		AuditTrailDAO {

	/**
	 * Find AuditTrail by username
	 */
	public List<AuditTrail> findByUsername(String username) {
		return findByCriteria(Restrictions.eq("username", username));
	}
	
	/**
	 * Find AuditTrail by message
	 */
	public List<AuditTrail> findByMessage(String message) {
		return findByCriteria(Restrictions.eq("message", message));
	}
	
	/**
	 * Find AuditTrail by sendtime
	 */
	public List<AuditTrail> findBySendtime(Timestamp sendtime) {
		return findByCriteria(Restrictions.eq("sendtime", sendtime));
	}
	
	/**
	 * Find AuditTrail by sendtonumber
	 */
	public List<AuditTrail> findBySendtonumber(String sendtonumber) {
		return findByCriteria(Restrictions.eq("sendtonumber", sendtonumber));
	}
	

}
