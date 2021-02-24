package com.pruvn.tools.common.hibernate.finnone.printsrv;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * <p>Pojo mapping TABLE PRINTSRV.PRT_REPRINT_LOG_M</p>
 *
 * <p>Generated at Tue Dec 04 16:09:38 ICT 2012</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
public class PrtReprintLogM implements Serializable {


	/**
	 * Attribute applid.
	 */
	private Integer id;

	/**
	 * Attribute applid.
	 */
	private String applid;
	
	/**
	 * Attribute fromDate.
	 */
	private Timestamp fromDate;
	
	/**
	 * Attribute stopInMinutes.
	 */
	private Long stopInMinutes;
	
	/**
	 * Attribute createdBy.
	 */
	private String createdBy;
	
	/**
	 * Attribute createdDate.
	 */
	private Timestamp createdDate;
	
	/**
	 * Attribute logDate.
	 */
	private Timestamp logDate;
	
	/**
	 * Attribute notes.
	 */
	private String notes;
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return applid
	 */
	public String getApplid() {
		return applid;
	}

	/**
	 * @param applid new value for applid 
	 */
	public void setApplid(String applid) {
		this.applid = applid;
	}
	
	/**
	 * @return fromDate
	 */
	public Timestamp getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate new value for fromDate 
	 */
	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	 * @return stopInMinutes
	 */
	public Long getStopInMinutes() {
		return stopInMinutes;
	}

	/**
	 * @param stopInMinutes new value for stopInMinutes 
	 */
	public void setStopInMinutes(Long stopInMinutes) {
		this.stopInMinutes = stopInMinutes;
	}
	
	/**
	 * @return createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy new value for createdBy 
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * @return createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate new value for createdDate 
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * @return logDate
	 */
	public Timestamp getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate new value for logDate 
	 */
	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}
	
	/**
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes new value for notes 
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	


}