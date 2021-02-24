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

import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintM;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.PrtReprintMDAO;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;


/**
 * <p>Hibernate DAO layer for PrtReprintMs</p>
 * <p>Generated at Tue Dec 04 16:09:39 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PrtReprintMHibernateDAO extends AbstractHibernateDAO<PrtReprintM, Integer> implements PrtReprintMDAO {

	/**
	 * Find PrtReprintM by applid
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintM> findByApplid(final String applid) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Criteria crit = session.createCriteria(getPersistentClass());
            	
            	crit.add(Restrictions.ilike("applid", applid, MatchMode.ANYWHERE));
                return crit.list();
            }
        });
	}
	
	/**
	 * Find PrtReprintM by fromDate
	 */
	public List<PrtReprintM> findByFromDate(Timestamp fromDate) {
		return findByCriteria(Restrictions.eq("fromDate", fromDate));
	}
	
	/**
	 * Find PrtReprintM by stopInMinutes
	 */
	public List<PrtReprintM> findByStopInMinutes(Long stopInMinutes) {
		return findByCriteria(Restrictions.eq("stopInMinutes", stopInMinutes));
	}
	
	/**
	 * Find PrtReprintM by createdBy
	 */
	public List<PrtReprintM> findByCreatedBy(String createdBy) {
		return findByCriteria(Restrictions.eq("createdBy", createdBy));
	}
	
	/**
	 * Find PrtReprintM by createdDate
	 */
	public List<PrtReprintM> findByCreatedDate(Timestamp createdDate) {
		return findByCriteria(Restrictions.eq("createdDate", createdDate));
	}
	
	/**
	 * Find PrtReprintM by notes
	 */
	public List<PrtReprintM> findByNotes(String notes) {
		return findByCriteria(Restrictions.eq("notes", notes));
	}
	

}
