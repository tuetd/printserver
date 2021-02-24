package com.pruvn.rms.dto;

import java.util.Date;

public class UserMDTO {


	private Integer id;

	private String username;

	private String fullname;

	private String loggedin;

	private String sessionId;

	private String finnoneSecurityCode;

	private String emailCode;

	private String password;

	private Date lastChangedPw;

	private String userPlace;

	private Date last_login_date;

	private Integer isActived;

	private Date datelogintemp;

	private Integer countlogintemp;

	private String reasonlock;
	
	private Date createdDate;
	
	private String createdBy;
	
	private Date lastModifiedDate;
	
	private String lastModifiedBy;

	private String roles;
	
	private String department;
	
	private String status;
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getLoggedin() {
		return loggedin;
	}



	public void setLoggedin(String loggedin) {
		this.loggedin = loggedin;
	}



	public String getSessionId() {
		return sessionId;
	}



	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}



	public String getFinnoneSecurityCode() {
		return finnoneSecurityCode;
	}



	public void setFinnoneSecurityCode(String finnoneSecurityCode) {
		this.finnoneSecurityCode = finnoneSecurityCode;
	}



	public String getEmailCode() {
		return emailCode;
	}



	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Date getLastChangedPw() {
		return lastChangedPw;
	}



	public void setLastChangedPw(Date lastChangedPw) {
		this.lastChangedPw = lastChangedPw;
	}



	public String getUserPlace() {
		return userPlace;
	}



	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
	}



	public Date getLast_login_date() {
		return last_login_date;
	}



	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}



	public Integer getIsActived() {
		return isActived;
	}



	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}



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

	public String getRoles() {
		return roles;
	}



	public void setRoles(String roles) {
		this.roles = roles;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}