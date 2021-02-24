package com.pruvn.tools.printserver;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Usermaster;
/**
 * <p>Generic DAO layer for Usermasters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface UsermasterDAO extends GenericDAO<Usermaster,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildUsermasterDAO()
	 */
	  	 
	/**
	 * Find Usermaster by username
	 */
	public Usermaster findByUsername(String username);

	/**
	 * Find Usermaster by loggedin
	 */
	public List<Usermaster> findByLoggedin(String loggedin);

	/**
	 * Find Usermaster by roleId
	 */
	public List<Usermaster> findByRoleId(Integer roleId);

	/**
	 * Find Usermaster by sessionId
	 */
	public List<Usermaster> findBySessionId(String sessionId);

	/**
	 * Find Usermaster by finnoneSecurityCode
	 */
	public List<Usermaster> findByFinnoneSecurityCode(String finnoneSecurityCode);

	/**
	 * Find Usermaster by emailCode
	 */
	public List<Usermaster> findByEmailCode(String emailCode);

	/**
	 * Find Usermaster by password
	 */
	public List<Usermaster> findByPassword(String password);

	/**
	 * Find Usermaster by lastChangedPw
	 */
	public List<Usermaster> findByLastChangedPw(Timestamp lastChangedPw);

	/**
	 * Find Usermaster by userPlace
	 */
	public List<Usermaster> findByUserPlace(String userPlace);

	public List<Usermaster> findByReport(Date todate, Date fromdate,
			Integer status);
	public List<Usermaster> findByReportPagination(int page, int pageSize,Date todate, Date fromdate,
			Integer status);
	 void excuteJobLockUser();

}