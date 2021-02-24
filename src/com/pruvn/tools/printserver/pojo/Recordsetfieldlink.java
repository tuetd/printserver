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
 * <p>Pojo mapping TABLE recordsetfieldlink</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "recordsetfieldlink", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Recordsetfieldlink implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute fieldid.
	 */
	private Integer fieldid;
	
	/**
	 * Attribute recordsetid.
	 */
	private Integer recordsetid;
	
	/**
	 * Attribute data.
	 */
	private String data;
	
	
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
	 * @return recordsetid
	 */
	@Basic
	@Column(name = "RECORDSETID")
		public Integer getRecordsetid() {
		return recordsetid;
	}

	/**
	 * @param recordsetid new value for recordsetid 
	 */
	public void setRecordsetid(Integer recordsetid) {
		this.recordsetid = recordsetid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return data
	 */
	@Basic
	@Column(name = "DATA", length = 2000)
		public String getData() {
		return data;
	}

	/**
	 * @param data new value for data 
	 */
	public void setData(String data) {
		this.data = data;
	}
	


}