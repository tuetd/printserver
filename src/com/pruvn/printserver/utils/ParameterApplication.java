package com.pruvn.printserver.utils;

 public enum ParameterApplication{
	 ACTIVE((long)1,"ACTIVE"),NOACTIVE((long)0,"NO ACTIVE"),DELETE((long)3,"DELETE"),BLOCK((long)2,"LOCKED");
	 private Long status;
	 private String name;
	 private ParameterApplication(Long i,String name){
		 this.status = i;
		 this.name = name;
	 }
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
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
