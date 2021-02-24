package com.pruvn.printserver.dao.implement;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.printserver.dao.PrtReprintMDAO;
import com.pruvn.printserver.entity.PrtReprintM;


/**
 * <p>Hibernate DAO layer for PrtReprintMs</p>
 * <p>Generated at Tue Dec 04 16:09:39 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PrtReprintMDAOImpl extends  HibernateGenericDAO<PrtReprintM, Integer> implements PrtReprintMDAO {

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
	@SuppressWarnings("unchecked")
	public List<PrtReprintM> findByFromDate(Timestamp fromDate) {
		Criteria criteria = getSession().createCriteria(PrtReprintM.class);
		criteria.add(Restrictions.eq("fromDate", fromDate));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintM by stopInMinutes
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintM> findByStopInMinutes(Long stopInMinutes) {
		Criteria criteria = getSession().createCriteria(PrtReprintM.class);
		criteria.add(Restrictions.eq("stopInMinutes", stopInMinutes));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintM by createdBy
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintM> findByCreatedBy(String createdBy) {
		Criteria criteria = getSession().createCriteria(PrtReprintM.class);
		criteria.add(Restrictions.eq("createdBy", createdBy));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintM by createdDate
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintM> findByCreatedDate(Timestamp createdDate) {
		Criteria criteria = getSession().createCriteria(PrtReprintM.class);
		criteria.add(Restrictions.eq("createdDate", createdDate));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintM by notes
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintM> findByNotes(String notes) {
		Criteria criteria = getSession().createCriteria(PrtReprintM.class);
		criteria.add(Restrictions.eq("notes", notes));
		return criteria.list();
	}
	

}
