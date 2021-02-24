package com.pruvn.tools.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	public String readKey(String key) throws IOException {
		Properties properties = new Properties();
	    try {
	    	//URL url =  ClassLoader.getSystemResource("config.properties");
	        //properties.load(new FileInputStream(System.getProperty("user.dir") + "//config.properties"));
	    	properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
	        if (properties != null && properties.containsKey(key)) {
	        	return (String) properties.get(key);
	        }
	    } catch (IOException e) {
	    	throw new IOException("Can't read properties file config.properties in classpath " + e);
	    }
	    return "";
	}
}
