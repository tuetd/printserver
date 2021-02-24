package com.pruvn.rms.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This snippet shows how we can generate the digest MD5 
 * for a given string. The digest is a one way hash and 
 * is represented into hex.
 * 
 * @author mvohra
 */
public class MD5 {
	public static void main(String args[]) throws Exception {
		// string to be encoded
		String text = "abc123";

		// get the instances for a given digest scheme MD5 or SHA
		MessageDigest m = MessageDigest.getInstance("MD5");

		// generate the digest ; pass in the text as bytes,
		// length to the bytes(offset) to be hashed; for full string 
		// pass 0 to text.length()
		m.update(text.getBytes(), 0, text.length());

		// get the String representation of hash bytes, 
		// create a big integer out of bytes
		// then convert it into hex value (16 as input to toString method)
		String digest = new BigInteger(1, m.digest()).toString(16); 
		System.out.println("MD5: " + digest);
		System.out.println("MD5 encode: " + encode(text));
		System.out.println("MD5 test: " + MD5(text));
	}
	
	public static String MD5(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        byte[] md5hash = md.digest();
        return byteToHex(md5hash);
    }
	private static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
	public static String encode(String text) throws NoSuchAlgorithmException {
		// get the instances for a given digest scheme MD5 or SHA
		MessageDigest m = MessageDigest.getInstance("MD5");

		// generate the digest ; pass in the text as bytes,
		// length to the bytes(offset) to be hashed; for full string 
		// pass 0 to text.length()
		m.update(text.getBytes(), 0, text.length());

		// get the String representation of hash bytes, 
		// create a big integer out of bytes
		// then convert it into hex value (16 as input to toString method)
		String digest = new BigInteger(1, m.digest()).toString(16); 
		
		return digest;
	}
}
