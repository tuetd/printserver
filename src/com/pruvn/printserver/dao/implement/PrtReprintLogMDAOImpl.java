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

import com.pruvn.printserver.dao.PrtReprintLogMDAO;
import com.pruvn.printserver.entity.PrtReprintLogM;


/**
 * <p>Hibernate DAO layer for PrtReprintLogMs</p>
 * <p>Generated at Tue Dec 04 16:09:38 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PrtReprintLogMDAOImpl extends HibernateGenericDAO<PrtReprintLogM, Integer> implements PrtReprintLogMDAO {

	/**
	 * Find PrtReprintLogM by applid
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	@SuppressWarnings("unchecked")
	public List<PrtReprintLogM> findByFromDate(Timestamp fromDate) {
		Criteria criteria = getSession().createCriteria(PrtReprintLogM.class);
		criteria.add(Restrictions.eq("fromDate", fromDate));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintLogM by stopInMinutes
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintLogM> findByStopInMinutes(Long stopInMinutes) {
		Criteria criteria = getSession().createCriteria(PrtReprintLogM.class);
		criteria.add(Restrictions.eq("stopInMinutes", stopInMinutes));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintLogM by createdBy
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintLogM> findByCreatedBy(String createdBy) {
		Criteria criteria = getSession().createCriteria(PrtReprintLogM.class);
		criteria.add(Restrictions.eq("createdBy", createdBy));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintLogM by createdDate
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintLogM> findByCreatedDate(Timestamp createdDate) {
			Criteria criteria = getSession().createCriteria(PrtReprintLogM.class);
			criteria.add(Restrictions.eq("createdDate", createdDate));
			return criteria.list();
	}
	
	/**
	 * Find PrtReprintLogM by logDate
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintLogM> findByLogDate(Timestamp logDate) {
		Criteria criteria = getSession().createCriteria(PrtReprintLogM.class);
		criteria.add(Restrictions.eq("logDate", logDate));
		return criteria.list();
	}
	
	/**
	 * Find PrtReprintLogM by notes
	 */
	@SuppressWarnings("unchecked")
	public List<PrtReprintLogM> findByNotes(String notes) {
		Criteria criteria = getSession().createCriteria(PrtReprintLogM.class);
		criteria.add(Restrictions.eq("notes", notes));
		return criteria.list();
	}
	

}
