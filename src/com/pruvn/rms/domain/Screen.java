/*
 * Java bean class for entity table RM_PRODUCT 
 * Created on 3 Oct 2013 ( Time 12:18:07 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.domain;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;

/**
 * Domain/Entity bean for table "RM_PRODUCT"
 * 
 * @author Telosys Tools Generator
 * 
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "RM_SCREEN")
public class Screen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "RM_SCREEN_SEQ"))
	@GeneratedValue(generator = "generator")
	private Integer id; // Primary Key

	@Column(name = "STAGE", nullable = false)
	private String stage;

	@Column(name = "VIEW_NAME")
	private String viewName;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PRIORITY1")
	private String priority1;

	@Column(name = "PRIORITY2")
	private String priority2;

	@Column(name = "IS_ACTIVED")
	private Integer isActived;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriority1() {
		return priority1;
	}

	public void setPriority1(String priority1) {
		this.priority1 = priority1;
	}

	public String getPriority2() {
		return priority2;
	}

	public void setPriority2(String priority2) {
		this.priority2 = priority2;
	}

	public Integer getIsActived() {
		return isActived;
	}

	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}
}