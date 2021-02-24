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
@Table(name = "SQLPARAMMASTER")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Sqlparammaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Long id;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute friendlyname.
	 */
	private String friendlyname;
	
	/**
	 * Attribute typeid.
	 */
	private Long typeid;
	
	/**
	 * Attribute datasourceid.
	 */
	private Long docid;
	
	/**
	 * Attribute fieldtype.
	 */
	private String fieldtype;
	
	
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
	
	/**
	 * <p> 
	 * </p>
	 * @return friendlyname
	 */
	@Basic
	@Column(name = "FRIENDLYNAME", length = 100)
		public String getFriendlyname() {
		return friendlyname;
	}

	/**
	 * @param friendlyname new value for friendlyname 
	 */
	public void setFriendlyname(String friendlyname) {
		this.friendlyname = friendlyname;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return typeid
	 */
	@Column(name = "TYPEID")
		public Long getTypeid() {
		return typeid;
	}

	/**
	 * @param typeid new value for typeid 
	 */
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return datasourceid
	 */
	@Column(name = "DOCID")
	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}


	
	/**
	 * <p> 
	 * </p>
	 * @return fieldtype
	 */
	@Column(name = "FIELDTYPE", length = 45)
		public String getFieldtype() {
		return fieldtype;
	}

	/**
	 * @param fieldtype new value for fieldtype 
	 */
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	


}