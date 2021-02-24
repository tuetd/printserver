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
 * <p>Pojo mapping TABLE filemaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "filemaster", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Filemaster implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute filepath.
	 */
	private String filepath;
	
	/**
	 * Attribute sheetnumber.
	 */
	private Byte sheetnumber;
	
	/**
	 * Attribute startindex.
	 */
	private Byte startindex;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute typeid.
	 */
	private Integer typeid;
	
	/**
	 * Attribute projectid.
	 */
	private Integer projectid;
	
	
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
	 * @return filepath
	 */
	@Basic
	@Column(name = "FILEPATH", length = 255)
		public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath new value for filepath 
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	/**
	 * <p>For Excel File 
	 * </p>
	 * @return sheetnumber
	 */
	@Basic
	@Column(name = "SHEETNUMBER")
		public Byte getSheetnumber() {
		return sheetnumber;
	}

	/**
	 * @param sheetnumber new value for sheetnumber 
	 */
	public void setSheetnumber(Byte sheetnumber) {
		this.sheetnumber = sheetnumber;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return startindex
	 */
	@Basic
	@Column(name = "STARTINDEX")
		public Byte getStartindex() {
		return startindex;
	}

	/**
	 * @param startindex new value for startindex 
	 */
	public void setStartindex(Byte startindex) {
		this.startindex = startindex;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return name
	 */
	@Basic
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
	 * @return typeid
	 */
	@Basic
	@Column(name = "TYPEID")
		public Integer getTypeid() {
		return typeid;
	}

	/**
	 * @param typeid new value for typeid 
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return projectid
	 */
	@Basic
	@Column(name = "PROJECTID")
		public Integer getProjectid() {
		return projectid;
	}

	/**
	 * @param projectid new value for projectid 
	 */
	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	


}