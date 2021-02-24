package com.pruvn.printserver.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
@NotBlank(message="Username không được để trống")
@NotEmpty(message="Username không được để trống")
 private String username;
@NotBlank(message="Password không được để trống")
@NotEmpty(message="Password không được để trống")
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
