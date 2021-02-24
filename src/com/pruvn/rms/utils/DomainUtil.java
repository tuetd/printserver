package com.pruvn.rms.utils;

import java.net.UnknownHostException;

import com.pruvn.rms.common.Constants;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbSession;

public class DomainUtil {
	public static String getDomainCanonicalName() throws UnknownHostException {
		java.net.InetAddress i = java.net.InetAddress.getLocalHost();
		String canonicalHostName = i.getCanonicalHostName();
		return canonicalHostName.substring(canonicalHostName.indexOf(".") + 1);
	}
	
	public static void logIn(String username, String password) throws Exception {	
		java.net.InetAddress i = java.net.InetAddress.getLocalHost();		
		UniAddress dc = UniAddress.getByName(i.getHostAddress());        
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(Constants.DOMAIN_NAME, username, password);        
		SmbSession.logon( dc, auth );		
	}
}
