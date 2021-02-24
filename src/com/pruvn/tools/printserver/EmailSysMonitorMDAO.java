package com.pruvn.tools.printserver;

import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.EmailSysMonitorM;
/**
 * <p>Generic DAO layer for EmailSysMonitorMs</p>
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface EmailSysMonitorMDAO extends GenericDAO<EmailSysMonitorM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildEmailSysMonitorMDAO()
	 */
	  	 
	/**
	 * Find EmailSysMonitorM by sysName
	 */
	public List<EmailSysMonitorM> findBySysName(String sysName);

	/**
	 * Find EmailSysMonitorM by sysCode
	 */
	public List<EmailSysMonitorM> findBySysCode(String sysCode);

	/**
	 * Find EmailSysMonitorM by emailContent
	 */
	public List<EmailSysMonitorM> findByEmailContent(String emailContent);

}