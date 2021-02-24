package com.pruvn.tools.utils;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintLogM;
import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintM;

/**
 */
public class PrtReprintForm
{
	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute id.
	 */
	@NotEmpty(message="Agreement Id must not empty")
	@Digits(message="Agreement Id must be number ex: 405218", integer=8, fraction=0)
	private String applid;
	
	/**
	 * Attribute emailCode.
	 */
	@NotNull(message="Will close in (minutes) must not empty")
	private Integer stopInMinutes;
	
	/**
	 * Attribute userPlace.
	 */
	private String notes;
	
	private List<PrtReprintM> prtReprints;
	
	private List<PrtReprintLogM> prtReprintLogs;

	
	/**
	 * @return the prtReprintLogs
	 */
	public List<PrtReprintLogM> getPrtReprintLogs() {
		return prtReprintLogs;
	}

	/**
	 * @param prtReprintLogs the prtReprintLogs to set
	 */
	public void setPrtReprintLogs(List<PrtReprintLogM> prtReprintLogs) {
		this.prtReprintLogs = prtReprintLogs;
	}

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
	 * @return the prtReprints
	 */
	public List<PrtReprintM> getPrtReprints() {
		return prtReprints;
	}

	/**
	 * @param prtReprints the prtReprints to set
	 */
	public void setPrtReprints(List<PrtReprintM> prtReprints) {
		this.prtReprints = prtReprints;
	}

	/**
	 * @return the applid
	 */
	public String getApplid() {
		return applid;
	}

	/**
	 * @param applid the applid to set
	 */
	public void setApplid(String applid) {
		this.applid = applid;
	}

	/**
	 * @return the stopInMinutes
	 */
	public Integer getStopInMinutes() {
		return stopInMinutes;
	}

	/**
	 * @param stopInMinutes the stopInMinutes to set
	 */
	public void setStopInMinutes(Integer stopInMinutes) {
		this.stopInMinutes = stopInMinutes;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
