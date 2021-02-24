package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.FollowUp;
/**
 * <p>Generic DAO layer for FollowUp</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface FollowUpDAO extends GenericDAO<FollowUp,Integer> {
	List<FollowUp> findAllByRecordId(Integer recordId);
}