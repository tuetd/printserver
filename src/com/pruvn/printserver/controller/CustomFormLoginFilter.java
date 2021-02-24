package com.pruvn.printserver.controller;

import java.util.ArrayList;
import java.util.List;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pruvn.printserver.form.GlobalConstant;

public class CustomFormLoginFilter implements AuthenticationProvider {
	private static final Logger logger = Logger.getLogger(CustomFormLoginFilter.class);

	 public static final List<GrantedAuthority> AUTHORITIES
	       = new ArrayList<GrantedAuthority>();
	     static {
	         AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	         AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
	    }
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		try {
			if(!auth.getName().isEmpty()&&!auth.getCredentials().toString().isEmpty()){
				java.net.InetAddress i = java.net.InetAddress.getLocalHost();		
				UniAddress dc = UniAddress.getByName(i.getHostAddress());        
				NtlmPasswordAuthentication auths = new NtlmPasswordAuthentication(GlobalConstant.DOMAIN_NAME_PRUASIA, auth.getName(),auth.getCredentials().toString()); 
				SmbSession.logon( dc, auths );	
				logger.info("login success!");
				 return new UsernamePasswordAuthenticationToken(auth.getName(),
			                auth.getCredentials(), AUTHORITIES);
			}
			throw new BadCredentialsException("Invalid username and password!");
		} catch (Exception e) {
				try {
					if(!auth.getName().isEmpty()&&!auth.getCredentials().toString().isEmpty()){
						java.net.InetAddress i	 = java.net.InetAddress.getLocalHost();
						UniAddress dc = UniAddress.getByName(i.getHostAddress());        
						NtlmPasswordAuthentication auths_pruvn = new NtlmPasswordAuthentication(GlobalConstant.DOMAIN_NAME_PRUVN, auth.getName(),auth.getCredentials().toString()); 
						SmbSession.logon( dc, auths_pruvn );
						logger.info("login success!");
						 return new UsernamePasswordAuthenticationToken(auth.getName(),
					                auth.getCredentials(), AUTHORITIES);
				     }
				   }catch (Exception e1) {
					  throw new BadCredentialsException("Invalid username and password!");
				   }		
				
			}
			throw new BadCredentialsException("Invalid username and password!");
	}
	@Override
	public boolean supports(Class<?> authentication) {
		if ( authentication == null ) return false;
	      return Authentication.class.isAssignableFrom(authentication);
	}
	
}
