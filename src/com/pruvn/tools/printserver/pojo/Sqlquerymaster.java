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
 * <p>Pojo mapping TABLE sqlquerymaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "sqlquerymaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Sqlquerymaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute value.
	 */
	private String value;
	
	/**
	 * Attribute datasourceid.
	 */
	private Integer datasourceid;
	
	/**
	 * Attribute type.
	 */
	private Integer type;
	
	/**
	 * Attribute indexnum.
	 */
	private Integer indexnum;
	
	/**
	 * Attribute queryname.
	 */
	private String queryname;
	
	
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
	 * @return value
	 */
	@Basic
	@Column(name = "VALUE", length = 5000)
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
	 * @return datasourceid
	 */
	@Basic
	@Column(name = "DATASOURCEID")
		public Integer getDatasourceid() {
		return datasourceid;
	}

	/**
	 * @param datasourceid new value for datasourceid 
	 */
	public void setDatasourceid(Integer datasourceid) {
		this.datasourceid = datasourceid;
	}
	
	/**
	 * <p>0-DML 1-DDL 
	 * </p>
	 * @return type
	 */
	@Basic
	@Column(name = "TYPE")
		public Integer getType() {
		return type;
	}

	/**
	 * @param type new value for type 
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return indexnum
	 */
	@Basic
	@Column(name = "INDEXNUM")
		public Integer getIndexnum() {
		return indexnum;
	}

	/**
	 * @param indexnum new value for indexnum 
	 */
	public void setIndexnum(Integer indexnum) {
		this.indexnum = indexnum;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return queryname
	 */
	@Basic
	@Column(name = "QUERYNAME", length = 100)
		public String getQueryname() {
		return queryname;
	}

	/**
	 * @param queryname new value for queryname 
	 */
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	


}