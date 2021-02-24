package com.pruvn.rms.utils;

import java.util.Date;


public class ExceptionUtils {
	public static final String NEWLINE = "\n";
	public static String composeExceptionMessage(Exception e) {
		String message = new Date().toString() + NEWLINE;
		message += e.toString() + NEWLINE;
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			message += ste[i].toString() + NEWLINE;
		}
		return message;
	}
}
