package com.pruvn.tools.utils;

 public enum ParameterApplication{
	 ACTIVE(1,"ACTIVE"),NOACTIVE(0,"NO ACTIVE"),DELETE(3,"DELETE"),BLOCK(2,"LOCKED");
	 private int status;
	 private String name;
	 private ParameterApplication(int i,String name){
		 this.status = i;
		 this.name = name;
	 }
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String convertStatus(int statuspar){
		String result="";
		switch (statuspar) {
		case 1:
			result = "ACTIVE" ;
			break;
		case 0:
			result = "NO ACTIVE" ;
			break;
		case 3:
			result = "DELETE" ;
			break;
		case 2:
			result = "LOCKED" ;
			break;
	  }
		return result;
	}
}
