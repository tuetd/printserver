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
 * <p>Pojo mapping TABLE userparamlink</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "userparamlink", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Userparamlink implements Serializable {

	/**
	 * Attribute userid.
	 */
	private Integer userid;
	
	/**
	 * Attribute paramid.
	 */
	private Integer paramid;
	
	/**
	 * Attribute value.
	 */
	private String value;
	
	
	/**
	 * <p> 
	 * </p>
	 * @return userid
	 */
	@Basic
	@Id
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
	
	/**
	 * <p> 
	 * </p>
	 * @return paramid
	 */
	@Basic
	@Column(name = "PARAMID")
		public Integer getParamid() {
		return paramid;
	}

	/**
	 * @param paramid new value for paramid 
	 */
	public void setParamid(Integer paramid) {
		this.paramid = paramid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return value
	 */
	@Basic
	@Column(name = "VALUE", length = 100)
		public String getValue() {
		return value;
	}

	/**
	 * @param value new value for value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	


}