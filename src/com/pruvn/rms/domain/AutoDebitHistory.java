package com.pruvn.rms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "AD_HISTORY")
public class AutoDebitHistory implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "AD_HISTORY_SEQ"))
	@GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Long id;

	/**
	 * Attribute username.
	 */
	@Column(name = "USERACTION", nullable = true)
	private String userAction;
		
	@Column(name = "DATEACTION", nullable = true)
	private Date dateAction;
	
	@Column(name = "ACTION", nullable = true)
	private String action;
	
	@Column(name = "IDRECORDACTION", nullable = true)
	private Long idRecordAction;
	
	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getIdRecordAction() {
		return idRecordAction;
	}

	public void setIdRecordAction(Long idRecordAction) {
		this.idRecordAction = idRecordAction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	

}