package com.pruvn.rms.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * <p>Pojo mapping TABLE user_dept_a</p>
 * <p></p>
 *
 * <p>Generated at Thu Aug 04 18:02:44 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name = "USER_DEPT_A")
public class UserDeptA implements Serializable {

	/**
	 * Attribute id.
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_USER_DEPT_A"))
    @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private Integer id;
	
	/**
	 * Attribute userId.
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserM user;
	
	/**
	 * Attribute deptId.
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = false)
	private DeptM dept;
	
	
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
	 * @return the user
	 */
	public UserM getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserM user) {
		this.user = user;
	}

	/**
	 * @return the dept
	 */
	public DeptM getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(DeptM dept) {
		this.dept = dept;
	}
	
}