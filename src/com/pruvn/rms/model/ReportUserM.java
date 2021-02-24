package com.pruvn.rms.model;

import com.pruvn.rms.domain.UserM;

public class ReportUserM {
	private UserM userM;
	private String level;
	public UserM getUserM() {
		return userM;
	}
	public void setUserM(UserM userM) {
		this.userM = userM;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
