package com.pruvn.rms.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm
{
	@NotEmpty (message = "common.smsimport.auth.login.inputusername")
	@NotNull (message = "common.smsimport.auth.login.inputpassword")
	private String username;
	
	@NotEmpty (message = "common.smsimport.auth.login.inputpassword")
	@NotNull (message = "common.smsimport.auth.login.inputpassword")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
