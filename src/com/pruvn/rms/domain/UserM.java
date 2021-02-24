package com.pruvn.rms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.pruvn.rms.interceptor.IAuditLog;

/**
 * <p>
 * Pojo mapping TABLE user_m
 * </p>
 * <p>
 * </p>
 * 
 * <p>
 * Generated at Mon Jul 11 14:56:25 ICT 2011
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "USER_M")
public class UserM implements Serializable, IAuditLog {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_USER_M"))
	@GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;

	/**
	 * Attribute username.
	 */
	@Column(name = "USERNAME", nullable = true)
	private String username;

	/**
	 * Attribute fullname.
	 */
	@Column(name = "FULL_NAME", nullable = true)
	private String fullname;

	/**
	 * Attribute loggedin.
	 */
	@Column(name = "LOGGEDIN", nullable = true)
	private String loggedin;

	/**
	 * Attribute sessionId.
	 */
	@Column(name = "SESSION_ID", nullable = true)
	private String sessionId;

	/**
	 * Attribute finnoneSecurityCode.
	 */
	@Column(name = "FINNONE_SECURITY_CODE", nullable = true)
	private String finnoneSecurityCode;

	/**
	 * Attribute emailCode.
	 */
	@Column(name = "EMAIL_CODE", nullable = true)
	private String emailCode;

	/**
	 * Attribute password.
	 */
	@Column(name = "PASSWORD", nullable = true)
	private String password;

	/**
	 * Attribute lastChangedPw.
	 */
	@Column(name = "LAST_CHANGED_PW", nullable = true)
	private Date lastChangedPw;

	/**
	 * Attribute userPlace.
	 */
	@Column(name = "USER_PLACE", nullable = true)
	private String userPlace;

	/**
	 * Attribute lastChangedPw.
	 */
	@Column(name = "LAST_LOGIN_DATE", nullable = true)
	private Date last_login_date;

	/**
	 * Attribute userPlace.
	 */
	@Column(name = "IS_ACTIVED", nullable = true, length = 1)
	private Integer isActived;

	@Column(name = "DATE_LOGIN_TEMP", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datelogintemp;

	@Column(name = "COUNT_LOGIN_TEMP", nullable = true)
	private Integer countlogintemp;

	@Column(name = "REASON_LOCK", nullable = true)
	private String reasonlock;
	
	@Column(name = "BRANCH_ID", nullable = true)
	private Integer branchId;
	
	@Column(name = "PRODUCT_ID", nullable = true)
	private Integer productId;
	
	@Column(name = "CREATED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "CREATED_BY", nullable = true)
	private String createdBy;
	
	@Column(name = "LAST_MODIFIED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@Column(name = "LAST_MODIFIED_BY", nullable = true)
	private String lastModifiedBy;

	public Date getDatelogintemp() {
		return datelogintemp;
	}

	public void setDatelogintemp(Date datelogintemp) {
		this.datelogintemp = datelogintemp;
	}

	public Integer getCountlogintemp() {
		return countlogintemp;
	}

	public void setCountlogintemp(Integer countlogintemp) {
		this.countlogintemp = countlogintemp;
	}

	public String getReasonlock() {
		return reasonlock;
	}

	public void setReasonlock(String reasonlock) {
		this.reasonlock = reasonlock;
	}

	/**
	 * @return the isActived
	 */
	public Integer getIsActived() {
		return isActived;
	}

	/**
	 * @param isActived
	 *            the isActived to set
	 */
	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}

	/**
	 * @return the last_login_date
	 */
	public Date getLast_login_date() {
		return last_login_date;
	}

	/**
	 * @param last_login_date
	 *            the last_login_date to set
	 */
	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            new value for username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            new value for id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return loggedin
	 */
	public String getLoggedin() {
		return loggedin;
	}

	/**
	 * @param loggedin
	 *            new value for loggedin
	 */
	public void setLoggedin(String loggedin) {
		this.loggedin = loggedin;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            new value for sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return finnoneSecurityCode
	 */
	public String getFinnoneSecurityCode() {
		return finnoneSecurityCode;
	}

	/**
	 * @param finnoneSecurityCode
	 *            new value for finnoneSecurityCode
	 */
	public void setFinnoneSecurityCode(String finnoneSecurityCode) {
		this.finnoneSecurityCode = finnoneSecurityCode;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return emailCode
	 */
	public String getEmailCode() {
		return emailCode;
	}

	/**
	 * @param emailCode
	 *            new value for emailCode
	 */
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            new value for password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return lastChangedPw
	 */
	public Date getLastChangedPw() {
		return lastChangedPw;
	}

	/**
	 * @param lastChangedPw
	 *            new value for lastChangedPw
	 */
	public void setLastChangedPw(Date lastChangedPw) {
		this.lastChangedPw = lastChangedPw;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return userPlace
	 */
	public String getUserPlace() {
		return userPlace;
	}

	/**
	 * @param userPlace
	 *            new value for userPlace
	 */
	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
	}

	@Override
	public Integer getIdAuditLog() {
		return this.id;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String getLogDeatil() {
		StringBuilder str_log = new StringBuilder();
		str_log.append("UserM id: " + id);
		str_log.append(" - userName:" + username);
		str_log.append(" - createDate:" + new Date());
		return str_log.toString();
	}

}