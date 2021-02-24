package com.pruvn.printserver.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class SHAPasswordEncoder implements PasswordEncoder {

	public String encodePassword(String rawPassword, Object arg1)
			throws DataAccessException {
		return String.valueOf(SHA.byteArrayToHexString(SHA.computeHash(rawPassword)));
	}

	public boolean isPasswordValid(String encPass, String rawPassword, Object arg2)
			throws DataAccessException {
		return encPass.equals(String.valueOf(SHA.byteArrayToHexString(SHA.computeHash(rawPassword))));
	}
}
