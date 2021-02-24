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
 * <p>Pojo mapping TABLE recordsetmaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "recordsetmaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Recordsetmaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute datetime.
	 */
	private Timestamp datetime;
	
	/**
	 * Attribute statusid.
	 */
	private Integer statusid;
	
	/**
	 * Attribute userid.
	 */
	private Integer userid;
	
	
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
	 * @return datetime
	 */
	@Basic
	@Column(name = "DATETIME")
		public Timestamp getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime new value for datetime 
	 */
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return statusid
	 */
	@Basic
	@Column(name = "STATUSID")
		public Integer getStatusid() {
		return statusid;
	}

	/**
	 * @param statusid new value for statusid 
	 */
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return userid
	 */
	@Basic
	@Column(name = "USERID")
		public Integer getUserid() {
		return userid;
	}

	/**
	 * @param userid new value for userid 
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	


}