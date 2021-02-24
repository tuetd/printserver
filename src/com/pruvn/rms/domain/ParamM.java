package com.pruvn.rms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * <p>Pojo mapping TABLE param_m</p>
 * <p></p>
 *
 * <p>Generated at Fri Oct 26 09:59:41 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name = "PARAM_M")
public class ParamM implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_PARAM_M"))
    @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;
	
	/**
	 * Attribute paramCode.
	 */
	@Column(name = "PARAMCODE", nullable = true)
	private String paramCode;
	
	/**
	 * Attribute paramValue.
	 */
	@Column(name = "PARAMVALUE", nullable = true)
	private String paramValue;
	
	/**
	 * Attribute description.
	 */
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	
	/**
	 * <p> 
	 * </p>
	 * @return id
	 */
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
	 * @return paramCode
	 */
	public String getParamCode() {
		return paramCode;
	}

	/**
	 * @param paramCode new value for paramCode 
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue new value for paramValue 
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return description
	 */
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