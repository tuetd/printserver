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
 * <p>Pojo mapping TABLE parammaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "parammaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Parammaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute value.
	 */
	private String value;
	
	/**
	 * Attribute description.
	 */
	private String description;
	
	
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
	 * <p> 
	 * </p>
	 * @return value
	 */
	@Basic
	@Column(name = "VALUE", length = 255)
		public String getValue() {
		return value;
	}

	/**
	 * @param value new value for value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return description
	 */
	@Basic
	@Column(name = "DESCRIPTION", length = 100)
		public String getDescription() {
		return description;
	}

	/**
	 * @param description new value for description 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	


}