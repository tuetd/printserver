package com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintLogM;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.PrtReprintLogMDAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;


/**
 * <p>Hibernate DAO layer for PrtReprintLogMs</p>
 * <p>Generated at Tue Dec 04 16:09:38 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PrtReprintLogMHibernateDAO extends AbstractHibernateDAO<PrtReprintLogM, Integer> implements PrtReprintLogMDAO {

	/**
	 * Find PrtReprintLogM by applid
	 */
	public List<PrtReprintLogM> findByApplid(final String applid) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Criteria crit = session.createCriteria(getPersistentClass());
            	
            	crit.add(Restrictions.ilike("applid", applid, MatchMode.ANYWHERE));
                return crit.list();
            }
        });
	}
	
	/**
	 * Find PrtReprintLogM by fromDate
	 */
	public List<PrtReprintLogM> findByFromDate(Timestamp fromDate) {
		return findByCriteria(Restrictions.eq("fromDate", fromDate));
	}
	
	/**
	 * Find PrtReprintLogM by stopInMinutes
	 */
	public List<PrtReprintLogM> findByStopInMinutes(Long stopInMinutes) {
		return findByCriteria(Restrictions.eq("stopInMinutes", stopInMinutes));
	}
	
	/**
	 * Find PrtReprintLogM by createdBy
	 */
	public List<PrtReprintLogM> findByCreatedBy(String createdBy) {
		return findByCriteria(Restrictions.eq("createdBy", createdBy));
	}
	
	/**
	 * Find PrtReprintLogM by createdDate
	 */
	public List<PrtReprintLogM> findByCreatedDate(Timestamp createdDate) {
		return findByCriteria(Restrictions.eq("createdDate", createdDate));
	}
	
	/**
	 * Find PrtReprintLogM by logDate
	 */
	public List<PrtReprintLogM> findByLogDate(Timestamp logDate) {
		return findByCriteria(Restrictions.eq("logDate", logDate));
	}
	
	/**
	 * Find PrtReprintLogM by notes
	 */
	public List<PrtReprintLogM> findByNotes(String notes) {
		return findByCriteria(Restrictions.eq("notes", notes));
	}
	

}
