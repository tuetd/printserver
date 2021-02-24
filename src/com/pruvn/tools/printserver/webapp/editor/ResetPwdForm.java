package com.pruvn.tools.printserver.webapp.editor;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ResetPwdForm
{
	@NotEmpty(message="Username not empty")
	@NotNull(message="Username not empty")
	private String username;
	@NotEmpty(message="Email not empty")
	@NotNull(message="Email not empty")
	private String email;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
