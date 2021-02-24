package com.pruvn.rms.domain;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * <p>Pojo mapping TABLE dept_m</p>
 * <p></p>
 *
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name = "DEPT_M")
public class DeptM implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_DEPT_M"))
    @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;
	
	/**
	 * Attribute deptcode.
	 */
	@Column(name = "DEPTCODE", nullable = true)
	private String deptcode;
	
	/**
	 * Attribute deptname.
	 */
	@Column(name = "DEPTNAME", nullable = true)
	private String deptname;
	
	
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
	 * @return deptcode
	 */
	public String getDeptcode() {
		return deptcode;
	}

	/**
	 * @param deptcode new value for deptcode 
	 */
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return deptname
	 */
	public String getDeptname() {
		return deptname;
	}

	/**
	 * @param deptname new value for deptname 
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	


}