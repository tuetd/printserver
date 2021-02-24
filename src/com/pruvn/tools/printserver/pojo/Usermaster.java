package com.pruvn.tools.printserver.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * <p>Pojo mapping TABLE usermaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "usermaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Usermaster implements Serializable {

	/**
	 * Attribute username.
	 */
	private String username;
	
	/**
	 * Attribute id.
	 */
	protected Integer id;
	
	/**
	 * Attribute loggedin.
	 */
	private String loggedin;
	
	/**
	 * Attribute roleId.
	 */
	private Integer roleId;
	
	/**
	 * Attribute sessionId.
	 */
	private String sessionId;
	
	/**
	 * Attribute finnoneSecurityCode.
	 */
	private String finnoneSecurityCode;
	
	/**
	 * Attribute emailCode.
	 */
	private String emailCode;
	
	/**
	 * Attribute password.
	 */
	private String password;
	
	/**
	 * Attribute lastChangedPw.
	 */
	private Timestamp lastChangedPw;
	
	/**
	 * Attribute userPlace.
	 */
	private String userPlace;
	private String userChannel;
	
	/**
	 * Attribute userPlace.
	 */
	private String nameFull;
	private String department;
	private String userCodeId;
	private Integer status;
	private Date lastlogindate;
	private Date datelogintemp;
	private Integer countlogintemp;
	private String reasonlock;
	/**
	 * <p> 
	 * </p>
	 * @return username
	 */
	@Basic
	@Column(name = "USERNAME", length = 50, nullable=false)
		public String getUsername() {
		return username;
	}

	/**
	 * @param username new value for username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return id
	 */
	@Basic
	@Id
	@GeneratedValue
	@Column(name = "ID")
		public Integer getId() {
		return id;
	}

	/**
	 * @param id new value for id 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return loggedin
	 */
	@Basic
	@Column(name = "LOGGEDIN", length = 1)
		public String getLoggedin() {
		return loggedin;
	}

	/**
	 * @param loggedin new value for loggedin 
	 */
	public void setLoggedin(String loggedin) {
		this.loggedin = loggedin;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return roleId
	 */
	@Basic
	@Column(name = "role_id", nullable = false)
		public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId new value for roleId 
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return sessionId
	 */
	@Basic
	@Column(name = "session_id", length = 500)
		public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId new value for sessionId 
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return finnoneSecurityCode
	 */
	@Basic
	@Column(name = "finnone_security_code", length = 50)
		public String getFinnoneSecurityCode() {
		return finnoneSecurityCode;
	}

	/**
	 * @param finnoneSecurityCode new value for finnoneSecurityCode 
	 */
	public void setFinnoneSecurityCode(String finnoneSecurityCode) {
		this.finnoneSecurityCode = finnoneSecurityCode;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return emailCode
	 */
	@Basic
	@Column(name = "email_code", length = 100)
		public String getEmailCode() {
		return emailCode;
	}

	/**
	 * @param emailCode new value for emailCode 
	 */
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return password
	 */
	@Basic
	@Column(name = "password", length = 1000, nullable=false)
	
		public String getPassword() {
		return password;
	}

	/**
	 * @param password new value for password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return lastChangedPw
	 */
	@Basic
	@Column(name = "last_changed_pw")
		public Timestamp getLastChangedPw() {
		return lastChangedPw;
	}

	/**
	 * @param lastChangedPw new value for lastChangedPw 
	 */
	public void setLastChangedPw(Timestamp lastChangedPw) {
		this.lastChangedPw = lastChangedPw;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return userPlace
	 */
	@Basic
	@Column(name = "user_place", length = 200)
		public String getUserPlace() {
		return userPlace;
	}

	/**
	 * @param userPlace new value for userPlace 
	 */
	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
	}
	
	@Basic
	@Column(name = "channel_id")
	public String getUserChannel() {
		return userChannel;
	}

	public void setUserChannel(String userChannel) {
		this.userChannel = userChannel;
	}

	@Basic
	@Column(name = "fullname", length = 200)
	public String getNameFull() {
		return nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}
	@Basic
	@Column(name = "department", length = 200)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	@Basic
	@Column(name = "usercodeid", length = 200)
	public String getUserCodeId() {
		return userCodeId;
	}

	public void setUserCodeId(String userCodeId) {
		this.userCodeId = userCodeId;
	}
	@Basic
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Basic
	@Column(name = "lastlogindate")
	public Date getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	@Basic
	@Column(name = "date_login_temp")
	public Date getDatelogintemp() {
		return datelogintemp;
	}

	public void setDatelogintemp(Date datelogintemp) {
		this.datelogintemp = datelogintemp;
	}
	@Basic
	@Column(name = "count_login_temp")
	public Integer getCountlogintemp() {
		return countlogintemp;
	}

	public void setCountlogintemp(Integer countlogintemp) {
		this.countlogintemp = countlogintemp;
	}
	@Basic
	@Column(name = "reason_lock")
	public String getReasonlock() {
		return reasonlock;
	}

	public void setReasonlock(String reasonlock) {
		this.reasonlock = reasonlock;
	}
	


}