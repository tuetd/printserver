package com.pruvn.rms.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.pruvn.rms.domain.Branch;
import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.domain.Product;
import com.pruvn.rms.domain.UserLevelM;

/**
 */
public class UserForm
{
	/**
	 * Attribute id.
	 */
	private Integer id;
	
	/**
	 * Attribute username.
	 */
	@NotNull
	private String username;
	
	/**
	 * Attribute emailCode.
	 */
	@NotNull
	private String emailCode;
	
	/**
	 * Attribute userPlace.
	 */
	private String userPlace;
	
	private Boolean isActived;
	
	private Integer branchId;
	
	private Integer productId;
	

	
	/**
	 * Attribute fullname.
	 */
	private String fullname;
	
	private List<DeptM> departments;
	
	private String department;
	
	private List<UserLevelM> userLevels;
	
	private String userLevel;
	
	private List<Branch> branchs;
	
	private List<Product> products;

	/**
	 * @return the userLevels
	 */
	public List<UserLevelM> getUserLevels() {
		return userLevels;
	}

	/**
	 * @param userLevels the userLevels to set
	 */
	public void setUserLevels(List<UserLevelM> userLevels) {
		this.userLevels = userLevels;
	}

	/**
	 * @return the userLevel
	 */
	public String getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * @return the isActived
	 */
	public Boolean getIsActived() {
		return isActived;
	}

	/**
	 * @param isActived the isActived to set
	 */
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}

	/**
	 * @return the departments
	 */
	public List<DeptM> getDepartments() {
		return departments;
	}

	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(List<DeptM> departments) {
		this.departments = departments;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
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
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the emailCode
	 */
	public String getEmailCode() {
		return emailCode;
	}

	/**
	 * @param emailCode the emailCode to set
	 */
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	/**
	 * @return the userPlace
	 */
	public String getUserPlace() {
		return userPlace;
	}

	/**
	 * @param userPlace the userPlace to set
	 */
	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public List<Branch> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
