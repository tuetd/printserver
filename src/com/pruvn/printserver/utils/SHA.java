package com.pruvn.printserver.utils;

import java.security.NoSuchAlgorithmException;

public class SHA {
	public static String byteArrayToHexString(byte[] b){
	     StringBuffer sb = new StringBuffer(b.length * 2);
	     for (int i = 0; i < b.length; i++){
	       int v = b[i] & 0xff;
	       if (v < 16) {
	         sb.append('0');
	       }
	       sb.append(Integer.toHexString(v));
	     }
	     return sb.toString().toUpperCase();
	}

	public static byte[] computeHash(String x)   
	{
	     try {
			 java.security.MessageDigest d = null;
			 d = java.security.MessageDigest.getInstance("SHA-1");
			 d.reset();
			 d.update(x.getBytes());
			 return  d.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encode(String rawString) {
		return String.valueOf(SHA.byteArrayToHexString(SHA.computeHash(rawString)));
	}
}
