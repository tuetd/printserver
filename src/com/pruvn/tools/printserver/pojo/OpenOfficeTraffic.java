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
 * <p>Pojo mapping TABLE open_office_traffic</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "open_office_traffic", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class OpenOfficeTraffic implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute servername.
	 */
	private String servername;
	
	/**
	 * Attribute maxConn.
	 */
	private Integer maxConn;
	
	/**
	 * Attribute currConn.
	 */
	private Integer currConn;
	
	
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
	 * @return servername
	 */
	@Basic
	@Column(name = "SERVERNAME", length = 50)
		public String getServername() {
		return servername;
	}

	/**
	 * @param servername new value for servername 
	 */
	public void setServername(String servername) {
		this.servername = servername;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return maxConn
	 */
	@Basic
	@Column(name = "MAX_CONN")
		public Integer getMaxConn() {
		return maxConn;
	}

	/**
	 * @param maxConn new value for maxConn 
	 */
	public void setMaxConn(Integer maxConn) {
		this.maxConn = maxConn;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return currConn
	 */
	@Basic
	@Column(name = "CURR_CONN")
		public Integer getCurrConn() {
		return currConn;
	}

	/**
	 * @param currConn new value for currConn 
	 */
	public void setCurrConn(Integer currConn) {
		this.currConn = currConn;
	}
	


}