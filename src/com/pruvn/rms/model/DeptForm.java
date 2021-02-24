package com.pruvn.rms.model;

import javax.validation.constraints.NotNull;

/**
 */
public class DeptForm
{
	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute emailCode.
	 */
	@NotNull
	private String deptCode;
	
	/**
	 * Attribute userPlace.
	 */
	private String deptName;

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
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
