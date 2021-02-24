package com.pruvn.tools.utils;

public class ConstantApp {
	 public enum REASON_LOCK{
		 TIME_EXPIRED("Exceed the limit of the times logged in"),LOGIN_EXPIRED("Exceed in number of times logged in");
		 private String name;
		 private REASON_LOCK(String name){
			 this.name = name;
		 }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		 
	 }
	 public static String LOGIN = "LOGIN";
	 public static String LOGOUT = "LOGOUT";
}
