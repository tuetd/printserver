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
import org.springframework.web.util.IntrospectorCleanupListener;

/**
 * <p>Pojo mapping TABLE bookmarkmaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "bookmarkmaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Bookmarkmaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute docid.
	 */
	private Integer docid;
	
	/**
	 * Attribute fieldid.
	 */
	private Integer fieldid;
	
	/**
	 * Attribute function.
	 */
	private String function;
	
	/**
	 * Attribute format.
	 */
	private String format;
	
	
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
	 * @return docid
	 */
	@Basic
	@Column(name = "DOCID")
		public Integer getDocid() {
		return docid;
	}

	/**
	 * @param docid new value for docid 
	 */
	public void setDocid(Integer docid) {
		this.docid = docid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return fieldid
	 */
	@Basic
	@Column(name = "FIELDID")
		public Integer getFieldid() {
		return fieldid;
	}

	/**
	 * @param fieldid new value for fieldid 
	 */
	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return function
	 */
	@Basic
	@Column(name = "FUNCTION", length = 100)
		public String getFunction() {
		return function;
	}

	/**
	 * @param function new value for function 
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return format
	 */
	@Basic
	@Column(name = "FORMAT", length = 100)
		public String getFormat() {
		return format;
	}

	/**
	 * @param format new value for format 
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	


}