package com.pruvn.printserver.entity;

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
 * <p>Pojo mapping TABLE sqlparammaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "SIGNATURE")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Signature implements Serializable {

	/**
	 * Attribute id.
	 */
	private Long id;
	
	/**
	 * Attribute name.
	 */
	private String fullname;
	
	/**
	 * Attribute friendlyname.
	 */
	private String namesignature;
	
	/**
	 * Attribute typeid.
	 */
	private String status;
	
	private String title;
	
	
	
	@Id
	@Column(name = "ID")
		public Long getId() {
		return id;
	}

	/**
	 * @param id new value for id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "FULLNAME")
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Column(name = "NAME_SIGNATURE")
	public String getNamesignature() {
		return namesignature;
	}

	public void setNamesignature(String namesignature) {
		this.namesignature = namesignature;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	


}