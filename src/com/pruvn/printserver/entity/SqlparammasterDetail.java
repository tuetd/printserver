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
@Table(name = "SQLPARAMMASTER_DETAIL")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class SqlparammasterDetail implements Serializable {

	/**
	 * Attribute id.
	 */
	private Long id;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	
	/**
	 * Attribute datasourceid.
	 */
	private Long docid;
	
	/**
	 * Attribute fieldtype.
	 */
	private String description;
	private String value;
	
	
	/**
	 * <p> 
	 * </p>
	 * @return id
	 */
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
	
	/**
	 * <p> 
	 * </p>
	 * @return name
	 */
	@Column(name = "NAME", length = 100)
		public String getName() {
		return name;
	}

	/**
	 * @param name new value for name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "DOCID")
	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}




}