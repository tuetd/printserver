package com.pruvn.rms.domain.finnbank;

import java.io.Serializable;


/**
 * <p>Pojo mapping TABLE dept_m</p>
 * <p></p>
 *
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@SuppressWarnings("serial")
public class Cm_doc_printed_trial implements Serializable {

	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute deptcode.
	 */
	private String app_formno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApp_formno() {
		return app_formno;
	}

	public void setApp_formno(String app_formno) {
		this.app_formno = app_formno;
	}
	



}