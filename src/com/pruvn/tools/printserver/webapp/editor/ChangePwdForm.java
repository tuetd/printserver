package com.pruvn.tools.printserver.webapp.editor;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePwdForm
{
	private String username;

	@NotEmpty(message="Old password not empty")
	@NotNull (message="Old password not empty")
	private String oldPwd;
	
	@NotEmpty(message="New password not empty")
	@NotNull(message="New password not empty")
	private String newPwd;

	@NotEmpty(message="Re-enter password not empty")
	@NotNull(message="Re-enter password not empty")
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
