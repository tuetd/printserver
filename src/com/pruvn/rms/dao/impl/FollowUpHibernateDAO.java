package com.pruvn.rms.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import com.pruvn.rms.dao.FollowUpDAO;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.FollowUp;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for FollowUp</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class FollowUpHibernateDAO extends
		AbstractHibernateDAO<FollowUp, Integer> implements
		FollowUpDAO {

	
	public List<FollowUp> findAllByRecordId(Integer recordId) {
		return findByCriteria(Restrictions.eq("recordId", recordId));
	}

}
