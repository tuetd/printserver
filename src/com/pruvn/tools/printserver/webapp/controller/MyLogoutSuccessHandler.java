package com.pruvn.tools.printserver.webapp.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Controller;

import com.pruvn.tools.printserver.UsermasterlogDAO;
import com.pruvn.tools.printserver.pojo.Usermasterlog;
import com.pruvn.tools.utils.ConstantApp;

@Controller
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{
	private UsermasterlogDAO usermasterlogDAO;
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
        	Usermasterlog userlog= new Usermasterlog();
        	userlog.setDate(new Date());
        	userlog.setUsername(authentication.getName());
        	userlog.setNoidung(ConstantApp.LOGOUT);
        	userlog.setIplogin(request.getRemoteAddr());
        	userlog.setSession(request.getSession().getId());
        	usermasterlogDAO.save(userlog);
        }
     
        super.onLogoutSuccess(request, response, authentication);       
    }
	public UsermasterlogDAO getUsermasterlogDAO() {
		return usermasterlogDAO;
	}
	public void setUsermasterlogDAO(UsermasterlogDAO usermasterlogDAO) {
		this.usermasterlogDAO = usermasterlogDAO;
	}
	
	

}
