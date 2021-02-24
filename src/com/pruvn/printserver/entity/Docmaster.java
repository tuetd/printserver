package com.pruvn.printserver.entity;

// Generated Jan 14, 2011 9:45:52 PM by Hibernate Tools 3.4.0.Beta1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * CmsSurvey generated by hbm2java
 */
@Entity
@Table(name = "DOCMASTER")
@Cache (usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Docmaster implements java.io.Serializable {

	private Long id;
	private String name_doc;
	private String templatefile;
	private String status;
	private String description;
	private Long signature;
	private Long signature_sip;
	public Docmaster() {
	}

	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CMS_CUS_SURVEY_SEQ")
//	@SequenceGenerator(name="CMS_CUS_SURVEY_SEQ", sequenceName="CMS_CUS_SURVEY_SEQ")
	@Column(name = "ID", unique = true, nullable = false)	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "NAME_DOC")	
	public String getName_doc() {
		return name_doc;
	}

	public void setName_doc(String name_doc) {
		this.name_doc = name_doc;
	}
	@Column(name = "TEMPLATEFILE")	
	public String getTemplatefile() {
		return templatefile;
	}

	public void setTemplatefile(String templatefile) {
		this.templatefile = templatefile;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "DESCIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "SIGNATURE")
	public Long getSignature() {
		return signature;
	}

	public void setSignature(Long signature) {
		this.signature = signature;
	}
	@Column(name = "SIGNATURE_SIP")
	public Long getSignature_sip() {
		return signature_sip;
	}

	public void setSignature_sip(Long signature_sip) {
		this.signature_sip = signature_sip;
	}


	

}