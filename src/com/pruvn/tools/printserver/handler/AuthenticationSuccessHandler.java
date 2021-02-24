package com.pruvn.tools.printserver.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.pruvn.tools.common.util.Constant;
import com.pruvn.tools.printserver.ParammasterDAO;
import com.pruvn.tools.printserver.pojo.Parammaster;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private ParammasterDAO parammasterDAO;
	

	/**
	 * @return the parammasterDAO
	 */
	public ParammasterDAO getParammasterDAO() {
		return parammasterDAO;
	}


	/**
	 * @param parammasterDAO the parammasterDAO to set
	 */
	public void setParammasterDAO(ParammasterDAO parammasterDAO) {
		this.parammasterDAO = parammasterDAO;
	}


	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		String username = auth.getName();
		logger.info("logged user " + username);
		User usersec = (User) auth.getPrincipal();
		
		List<Parammaster> parammasters = parammasterDAO.findAll();
		if (Constant.docMapping == null) {
			Constant.docMapping = new HashMap<String, String>();
		}
		if (Constant.docMappingReverse == null) {
			Constant.docMappingReverse = new HashMap<String, String>();
		}
		for (Parammaster parammaster2 : parammasters) {
			Constant.docMapping.put(parammaster2.getName(), parammaster2.getValue());
			Constant.docMappingReverse.put(parammaster2.getValue(), parammaster2.getName());
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
