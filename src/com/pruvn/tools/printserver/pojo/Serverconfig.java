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
 * <p>Pojo mapping TABLE serverconfig</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:44 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "serverconfig", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Serverconfig implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute servername.
	 */
	private String servername;
	
	/**
	 * Attribute port.
	 */
	private String port;
	
	/**
	 * Attribute dbname.
	 */
	private String dbname;
	
	/**
	 * Attribute username.
	 */
	private String username;
	
	/**
	 * Attribute password.
	 */
	private String password;
	
	/**
	 * Attribute systemname.
	 */
	private String systemname;
	
	/**
	 * Attribute typeid.
	 */
	private Integer typeid;
	private String configcode;

	
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
	 * @return servername
	 */
	@Basic
	@Column(name = "SERVERNAME", length = 100)
		public String getServername() {
		return servername;
	}

	/**
	 * @param servername new value for servername 
	 */
	public void setServername(String servername) {
		this.servername = servername;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return port
	 */
	@Basic
	@Column(name = "PORT", length = 10)
		public String getPort() {
		return port;
	}

	/**
	 * @param port new value for port 
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	/**
	 * <p>For DB SERVER 
	 * </p>
	 * @return dbname
	 */
	@Basic
	@Column(name = "DBNAME", length = 100)
		public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname new value for dbname 
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return username
	 */
	@Basic
	@Column(name = "USERNAME", length = 45)
		public String getUsername() {
		return username;
	}

	/**
	 * @param username new value for username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return password
	 */
	@Basic
	@Column(name = "PASSWORD", length = 45)
		public String getPassword() {
		return password;
	}

	/**
	 * @param password new value for password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return systemname
	 */
	@Basic
	@Column(name = "SYSTEMNAME", length = 100)
		public String getSystemname() {
		return systemname;
	}

	/**
	 * @param systemname new value for systemname 
	 */
	public void setSystemname(String systemname) {
		this.systemname = systemname;
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
	@Basic
	@Column(name = "CONFIGCODE")
	public String getConfigcode() {
		return configcode;
	}

	public void setConfigcode(String configcode) {
		this.configcode = configcode;
	}
	


}