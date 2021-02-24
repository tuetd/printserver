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
 * <p>Pojo mapping TABLE datasourcemaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "datasourcemaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Datasourcemaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute fileid.
	 */
	private Integer fileid;
	
	/**
	 * Attribute serverconfigid.
	 */
	private Integer serverconfigid;
	
	
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
	 * @return name
	 */
	@Basic
	@Column(name = "NAME", length = 45)
		public String getName() {
		return name;
	}

	/**
	 * @param name new value for name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * <p>For File DataSource 
	 * </p>
	 * @return fileid
	 */
	@Basic
	@Column(name = "FILEID")
		public Integer getFileid() {
		return fileid;
	}

	/**
	 * @param fileid new value for fileid 
	 */
	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return serverconfigid
	 */
	@Basic
	@Column(name = "SERVERCONFIGID")
		public Integer getServerconfigid() {
		return serverconfigid;
	}

	/**
	 * @param serverconfigid new value for serverconfigid 
	 */
	public void setServerconfigid(Integer serverconfigid) {
		this.serverconfigid = serverconfigid;
	}
	


}