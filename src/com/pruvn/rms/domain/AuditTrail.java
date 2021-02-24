package com.pruvn.rms.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



/**
 * <p>Pojo mapping TABLE audit_trail</p>
 * <p></p>
 *
 * <p>Generated at Thu Sep 29 10:44:42 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name = "AUDIT_TRAIL")
public class AuditTrail implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_AUDIT_TRAIL"))
    @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;
	
	/**
	 * Attribute username.
	 */
	@Column(name = "USERNAME", nullable = true)
	private String username;
	
	/**
	 * Attribute message.
	 */
	@Column(name = "MESSAGE", nullable = true)
	private String message;
	
	/**
	 * Attribute sendtime.
	 */
	@Column(name = "SENDTIME", nullable = true)
	private Timestamp sendtime;
	
	/**
	 * Attribute sendtonumber.
	 */
	@Column(name = "SENDTONUMBER", nullable = true)
	private String sendtonumber;
	@Column(name = "DEPT", nullable = true)
	private String dept;
	
	
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * <p> 
	 * </p>
	 * @return id
	 */
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
	 * @return username
	 */
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
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message new value for message 
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return sendtime
	 */
	public Timestamp getSendtime() {
		return sendtime;
	}

	/**
	 * @param sendtime new value for sendtime 
	 */
	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return sendtonumber
	 */
	public String getSendtonumber() {
		return sendtonumber;
	}

	/**
	 * @param sendtonumber new value for sendtonumber 
	 */
	public void setSendtonumber(String sendtonumber) {
		this.sendtonumber = sendtonumber;
	}
	


}