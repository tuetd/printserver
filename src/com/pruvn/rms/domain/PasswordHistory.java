package com.pruvn.rms.domain;

import java.io.Serializable;
import java.sql.Timestamp;
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

@SuppressWarnings("serial")
@Entity
@Table(name = "PASSWORD_HISTORY")
public class PasswordHistory implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_PASSWORD_HISTORY"))
    @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;

	/**
	 * Attribute name.
	 */
	@Column(name = "USERNAME", nullable = true)
	private String userName;
	
	@Column(name = "PASSWORD_CODE", nullable = true)
	private String passwordCode;
	
	@Column(name = "DATE_LOGIN", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLogin;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPasswordCode() {
		return passwordCode;
	}

	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}

	public Date getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
