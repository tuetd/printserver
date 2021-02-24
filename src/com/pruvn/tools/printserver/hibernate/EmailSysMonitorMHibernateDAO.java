package com.pruvn.tools.printserver.hibernate;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.EmailSysMonitorM;
import com.pruvn.tools.printserver.EmailSysMonitorMDAO;

import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for EmailSysMonitorMs</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class EmailSysMonitorMHibernateDAO extends
		AbstractHibernateDAO<EmailSysMonitorM, Integer> implements
		EmailSysMonitorMDAO {

	/**
	 * Find EmailSysMonitorM by sysName
	 */
	public List<EmailSysMonitorM> findBySysName(String sysName) {
		return findByCriteria(Restrictions.eq("sysName", sysName));
	}
	
	/**
	 * Find EmailSysMonitorM by sysCode
	 */
	public List<EmailSysMonitorM> findBySysCode(String sysCode) {
		return findByCriteria(Restrictions.eq("sysCode", sysCode));
	}
	
	/**
	 * Find EmailSysMonitorM by emailContent
	 */
	public List<EmailSysMonitorM> findByEmailContent(String emailContent) {
		return findByCriteria(Restrictions.eq("emailContent", emailContent));
	}
	

}
