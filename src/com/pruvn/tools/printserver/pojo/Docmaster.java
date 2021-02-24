package com.pruvn.tools.printserver.pojo;

import java.util.List;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * <p>Pojo mapping TABLE docmaster</p>
 * <p></p>
 *
 * <p>Generated at Thu Jul 19 10:40:43 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@Table(name = "docmaster_docx", catalog = "cfclongdev")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SuppressWarnings("serial")
public class Docmaster implements Serializable {

	/**
	 * Attribute id.
	 */
	protected Integer id;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute startpage.
	 */
	private Byte startpage;
	
	/**
	 * Attribute endpage.
	 */
	private Byte endpage;
	
	/**
	 * Attribute templatefile.
	 */
	private Integer templatefile;
	
	/**
	 * Attribute serverfile.
	 */
	private String serverfile;
	
	/**
	 * Attribute localfile.
	 */
	private String localfile;
	
	/**
	 * Attribute datasourceId.
	 */
	private Integer datasourceId;
	
	/**
	 * Attribute status.
	 */
	private String status;
	
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
	@Column(name = "NAME", length = 50)
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
	 * @return startpage
	 */
	@Basic
	@Column(name = "STARTPAGE")
		public Byte getStartpage() {
		return startpage;
	}

	/**
	 * @param startpage new value for startpage 
	 */
	public void setStartpage(Byte startpage) {
		this.startpage = startpage;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return endpage
	 */
	@Basic
	@Column(name = "ENDPAGE")
		public Byte getEndpage() {
		return endpage;
	}

	/**
	 * @param endpage new value for endpage 
	 */
	public void setEndpage(Byte endpage) {
		this.endpage = endpage;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return templatefile
	 */
	@Basic
	@Column(name = "TEMPLATEFILE")
		public Integer getTemplatefile() {
		return templatefile;
	}

	/**
	 * @param templatefile new value for templatefile 
	 */
	public void setTemplatefile(Integer templatefile) {
		this.templatefile = templatefile;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return serverfile
	 */
	@Basic
	@Column(name = "SERVERFILE", length = 100)
		public String getServerfile() {
		return serverfile;
	}

	/**
	 * @param serverfile new value for serverfile 
	 */
	public void setServerfile(String serverfile) {
		this.serverfile = serverfile;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return localfile
	 */
	@Basic
	@Column(name = "LOCALFILE", length = 100)
		public String getLocalfile() {
		return localfile;
	}

	/**
	 * @param localfile new value for localfile 
	 */
	public void setLocalfile(String localfile) {
		this.localfile = localfile;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return datasourceId
	 */
	@Basic
	@Column(name = "datasource_id")
		public Integer getDatasourceId() {
		return datasourceId;
	}

	/**
	 * @param datasourceId new value for datasourceId 
	 */
	public void setDatasourceId(Integer datasourceId) {
		this.datasourceId = datasourceId;
	}
	@Basic
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	


}