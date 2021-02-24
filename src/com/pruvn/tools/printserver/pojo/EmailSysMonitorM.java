package com.pruvn.tools.printserver.pojo;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * <p>Pojo mapping TABLE email_sys_monitor_m</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "email_sys_monitor_m", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class EmailSysMonitorM implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute sysName.
	 */
	private String sysName;
	
	/**
	 * Attribute sysCode.
	 */
	private String sysCode;
	
	/**
	 * Attribute emailContent.
	 */
	private String emailContent;
	
	
	/**
	 * <p> 
	 * </p>
	 * @return id
	 */
	@Basic
	@Id
	@GeneratedValue
	@Column(name = "id")
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
	 * @return sysName
	 */
	@Basic
	@Column(name = "sys_name", length = 45)
		public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName new value for sysName 
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return sysCode
	 */
	@Basic
	@Column(name = "sys_code", length = 45)
		public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode new value for sysCode 
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return emailContent
	 */
	@Basic
	@Column(name = "email_content", length = 5000)
		public String getEmailContent() {
		return emailContent;
	}

	/**
	 * @param emailContent new value for emailContent 
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	


}