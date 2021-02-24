package com.pruvn.rms.dto;

import java.util.List;

public class DataTablesDto<T> implements java.io.Serializable {
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private List<T> aaData;
	private int sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	/**
	 * @return the aaData
	 */
	public List<T> getAaData() {
		return aaData;
	}
	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	/**
	 * @return the sEcho
	 */
	public int getsEcho() {
		return sEcho;
	}
	/**
	 * @param sEcho the sEcho to set
	 */
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	/**
	 * @return the iTotalRecords
	 */
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}
	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	/**
	 * @return the iTotalDisplayRecords
	 */
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
	
}
