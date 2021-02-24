package com.pruvn.tools.printserver.webapp.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.UsermasterlogDAO;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.pojo.Usermasterlog;
import com.pruvn.tools.utils.ConstantApp;
import com.pruvn.tools.utils.DateUtils;
import com.pruvn.tools.utils.ParameterApplication;

@Controller
public class MyLoginFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	private static Logger logger = Logger
			.getLogger(MyLoginFailureHandler.class);
	private UsermasterDAO usermasterDAO;
	private UsermasterlogDAO usermasterlogDAO;
	private int minutesLimitLogin;
	private int maxCountLogin;
@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String username = (String) request.getSession().getAttribute(
				"SPRING_SECURITY_LAST_USERNAME");
		Usermaster usermaster = usermasterDAO.findByUsername(username);
		int maxcounttemp = 0;
		String message="Username or password incorrect";
		if (usermaster != null) {
			if (usermaster.getStatus() == ParameterApplication.BLOCK
					.getStatus()) {
				logger.info("usermaster is blocked");
				message="The account is locked ("+usermaster.getReasonlock()+")";
				//save log
				Usermasterlog userlog=new Usermasterlog();
				userlog.setUsername(usermaster.getUsername());
				userlog.setDate(new Date());
				userlog.setNoidung(ConstantApp.LOGIN);
				userlog.setNoidung1(usermaster.getReasonlock());
				userlog.setSession(request.getSession().getId());
			    userlog.setIplogin(request.getRemoteAddr());
			    usermasterlogDAO.save(userlog);
				
				
				
			} else if (usermaster.getStatus() == ParameterApplication.DELETE
					.getStatus()) {
				logger.info("usermaster is deleted");
				message ="The account is deleted";
				//save log
				Usermasterlog userlog=new Usermasterlog();
				userlog.setUsername(usermaster.getUsername());
				userlog.setDate(new Date());
				userlog.setNoidung(ConstantApp.LOGIN);
				userlog.setNoidung1(ParameterApplication.DELETE.getName());
				userlog.setSession(request.getSession().getId());
			    userlog.setIplogin(request.getRemoteAddr());
				usermasterlogDAO.save(userlog);
				
				
			} else {
				if (usermaster.getDatelogintemp() != null) {
					if (DateUtils.caculatorDateMinutes(new Date(),
							usermaster.getDatelogintemp()) <= minutesLimitLogin) {
						maxcounttemp = usermaster.getCountlogintemp();
					}
				}
				maxcounttemp++;
				if (maxcounttemp == maxCountLogin) {
					logger.info("usermaster is blocked");
					usermaster.setReasonlock(ConstantApp.REASON_LOCK.LOGIN_EXPIRED.getName());
					usermaster
							.setStatus(ParameterApplication.BLOCK.getStatus());
					message ="The account is locked ("+ConstantApp.REASON_LOCK.LOGIN_EXPIRED.getName()+")";
				}
				usermaster.setCountlogintemp(maxcounttemp);
				usermaster.setDatelogintemp(new Date());
				usermasterDAO.update(usermaster);
			}
		}
		request.getSession().setAttribute("messages",message);  
		super.onAuthenticationFailure(request, response, exception);
	}

	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}

	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	public int getMinutesLimitLogin() {
		return minutesLimitLogin;
	}

	public void setMinutesLimitLogin(int minutesLimitLogin) {
		this.minutesLimitLogin = minutesLimitLogin;
	}

	public int getMaxCountLogin() {
		return maxCountLogin;
	}

	public void setMaxCountLogin(int maxCountLogin) {
		this.maxCountLogin = maxCountLogin;
	}

	public UsermasterlogDAO getUsermasterlogDAO() {
		return usermasterlogDAO;
	}

	public void setUsermasterlogDAO(UsermasterlogDAO usermasterlogDAO) {
		this.usermasterlogDAO = usermasterlogDAO;
	}



}
