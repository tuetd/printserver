package com.pruvn.printserver.entity;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
 * <p>Pojo mapping TABLE docmaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "USER_LOG")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class UsermasterLog implements Serializable {

	/**
	 * Attribute id.
	 */
	protected Long id;
	
	/**
	 * Attribute name.
	 */
	private String username;
	private String iplogin;
	private String session_log;
	private Date date_create;
	private String noidung;
	private String noidung1;
	private String noidung2;
	private String noidung3;
	private String noidung4;
	
	
	
	/**
	 * <p> 
	 * </p>
	 * @return id
	 */
	@Basic
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_LOG_SEQ")
	@SequenceGenerator(name="USER_LOG_SEQ", sequenceName="USER_LOG_SEQ")
	@Column(name = "id")
		public Long getId() {
		return id;
	}

	/**
	 * @param id new value for id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	@Basic
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Basic
	@Column(name = "iplogin")
	public String getIplogin() {
		return iplogin;
	}

	public void setIplogin(String iplogin) {
		this.iplogin = iplogin;
	}
	@Basic
	@Column(name = "session_log")
	public String getSession_log() {
		return session_log;
	}

	public void setSession_log(String session_log) {
		this.session_log = session_log;
	}
	@Basic
	@Column(name = "date_create")
	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	@Basic
	@Column(name = "noidung")
	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	@Basic
	@Column(name = "noidung_1")
	public String getNoidung1() {
		return noidung1;
	}

	public void setNoidung1(String noidung1) {
		this.noidung1 = noidung1;
	}
	@Basic
	@Column(name = "noidung_2")
	public String getNoidung2() {
		return noidung2;
	}

	public void setNoidung2(String noidung2) {
		this.noidung2 = noidung2;
	}
	@Basic
	@Column(name = "noidung_3")
	public String getNoidung3() {
		return noidung3;
	}

	public void setNoidung3(String noidung3) {
		this.noidung3 = noidung3;
	}
	@Basic
	@Column(name = "noidung_4")
	public String getNoidung4() {
		return noidung4;
	}

	public void setNoidung4(String noidung4) {
		this.noidung4 = noidung4;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return name
	 */
	


}