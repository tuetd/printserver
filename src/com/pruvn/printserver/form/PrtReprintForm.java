package com.pruvn.printserver.form;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.pruvn.printserver.entity.PrtReprintLogM;
import com.pruvn.printserver.entity.PrtReprintM;

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
	@NotEmpty(message="Agreement No must not empty")
	@Digits(message="Agreement No must be number ex: 100008635", integer=9, fraction=0)
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
