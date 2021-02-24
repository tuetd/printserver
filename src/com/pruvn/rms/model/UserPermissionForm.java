package com.pruvn.rms.model;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 */
public class UserPermissionForm
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
	
	private List<String> allPermission;
	
	private List<String> grantedPermission;

	/**
	 * @return the allPermission
	 */
	public List<String> getAllPermission() {
		return allPermission;
	}

	/**
	 * @param allPermission the allPermission to set
	 */
	public void setAllPermission(List<String> allPermission) {
		this.allPermission = allPermission;
	}

	/**
	 * @return the grantedPermission
	 */
	public List<String> getGrantedPermission() {
		return grantedPermission;
	}

	/**
	 * @param grantedPermission the grantedPermission to set
	 */
	public void setGrantedPermission(List<String> grantedPermission) {
		this.grantedPermission = grantedPermission;
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
}
