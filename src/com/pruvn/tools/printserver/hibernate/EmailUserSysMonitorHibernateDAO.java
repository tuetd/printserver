package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.EmailUserSysMonitor;
import com.pruvn.tools.printserver.EmailUserSysMonitorDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for EmailUserSysMonitors</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class EmailUserSysMonitorHibernateDAO extends
		AbstractHibernateDAO<EmailUserSysMonitor, Integer> implements
		EmailUserSysMonitorDAO {

	/**
	 * Find EmailUserSysMonitor by userId
	 */
	public List<EmailUserSysMonitor> findByUserId(Integer userId) {
		return findByCriteria(Restrictions.eq("userId", userId));
	}
	
	/**
	 * Find EmailUserSysMonitor by sysId
	 */
	public List<EmailUserSysMonitor> findBySysId(Integer sysId) {
		return findByCriteria(Restrictions.eq("sysId", sysId));
	}
	

}
