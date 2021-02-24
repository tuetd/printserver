package com.pruvn.rms.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pruvn.rms.domain.UserM;
/**
 * <p>Generic DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public interface UserMDAO extends GenericDAO<UserM,Integer> {

	/*
	 * TODO : Add specific businesses daos here.
	 * These methods will be overwrited if you re-generate this interface.
	 * You might want to extend this interface and to change the dao factory to return 
	 * an instance of the new implemenation in buildUserMDAO()
	 */
	  	 
	/**
	 * Find UserM by username
	 */
	public List<UserM> findByUsername(String username);

	/**
	 * Find UserM by loggedin
	 */
	public List<UserM> findByLoggedin(String loggedin);

	/**
	 * Find UserM by sessionId
	 */
	public List<UserM> findBySessionId(String sessionId);

	/**
	 * Find UserM by finnoneSecurityCode
	 */
	public List<UserM> findByFinnoneSecurityCode(String finnoneSecurityCode);

	/**
	 * Find UserM by emailCode
	 * @param username 
	 */
	public List<UserM> findByEmailCode(String emailCode);
	
	public List<UserM> findUser(String emailCode, String username);

	/**
	 * Find UserM by password
	 */
	public List<UserM> findByPassword(String password);

	/**
	 * Find UserM by lastChangedPw
	 */
	public List<UserM> findByLastChangedPw(Timestamp lastChangedPw);

	/**
	 * Find UserM by userPlace
	 */
	public List<UserM> findByUserPlace(String userPlace);

	public List<UserM> searchUserByUserName(String username);

	public List<UserM> findUserByDeptCodeAndLevelCode(String deptCode,
			String levelCode);

	public List<String> findByFullName(String user,String grouprole);

	public List<UserM> findByRole(String type);
	
	public void deactive(String userId);

}