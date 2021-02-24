package com.pruvn.tools.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import javax.mail.internet.MimeUtility;

public class EncryptUtil {

	public static byte[] base64encode(byte[] b) throws Exception {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    OutputStream b64os = MimeUtility.encode(baos, "base64");
	    b64os.write(b);
	    b64os.close();
	    return baos.toByteArray();
	 }

	public static byte[] base64decode(byte[] b) throws Exception {
	    ByteArrayInputStream bais = new ByteArrayInputStream(b);
	    InputStream b64is = MimeUtility.decode(bais, "base64");
	    byte[] tmp = new byte[b.length];
	    int n = b64is.read(tmp);
	    byte[] res = new byte[n];
	    System.arraycopy(tmp, 0, res, 0, n);
	    return res;
	 }

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

}
