package com.pruvn.rms.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePwdForm
{
	private String username;

	@NotEmpty
	@NotNull 
	private String oldPwd;
	
	@NotEmpty
	@NotNull
	private String newPwd;

	@NotEmpty
	@NotNull
	private String reenterredNewPwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getReenterredNewPwd() {
		return reenterredNewPwd;
	}

	public void setReenterredNewPwd(String reenterredNewPwd) {
		this.reenterredNewPwd = reenterredNewPwd;
	}
}
