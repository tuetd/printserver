package com.pruvn.rms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletResponse response = (HttpServletResponse) res;

		if (SecurityContextHolder.getContext().getAuthentication() != null) {         
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	        if (user != null && !user.isCredentialsNonExpired()) {
	        	response.sendRedirect("auth/changepwd.html");
	        } else {
	        	chain.doFilter(req, res);
	        }
        
		}else {
        	chain.doFilter(req, res);
        }
	}

}
