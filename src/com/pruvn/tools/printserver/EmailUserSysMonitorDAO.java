package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.EmailUserSysMonitor;
/**
 * <p>Generic DAO layer for EmailUserSysMonitors</p>
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface EmailUserSysMonitorDAO extends GenericDAO<EmailUserSysMonitor,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildEmailUserSysMonitorDAO()
	 */
	  	 
	/**
	 * Find EmailUserSysMonitor by userId
	 */
	public List<EmailUserSysMonitor> findByUserId(Integer userId);

	/**
	 * Find EmailUserSysMonitor by sysId
	 */
	public List<EmailUserSysMonitor> findBySysId(Integer sysId);

}