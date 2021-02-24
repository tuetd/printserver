package com.pruvn.printserver.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.entity.UsermasterLog;
import com.pruvn.printserver.services.UsermasterLogService;
import com.pruvn.printserver.services.UsermasterService;
import com.pruvn.printserver.utils.Constant;
import com.pruvn.printserver.utils.DateUtils;
import com.pruvn.printserver.utils.ParameterApplication;


@Controller
public class MyLoginFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	private static Logger logger = Logger
			.getLogger(MyLoginFailureHandler.class);
	@Autowired
	private UsermasterService usermasterService;
	@Autowired
	private UsermasterLogService usermasterLogService;
	private Long minutesLimitLogin;
	private Long maxCountLogin;
	
	
	
	public UsermasterLogService getUsermasterLogService() {
		return usermasterLogService;
	}
	public void setUsermasterLogService(UsermasterLogService usermasterLogService) {
		this.usermasterLogService = usermasterLogService;
	}
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		 String formUsernameKey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
		 String username = request.getParameter(formUsernameKey);
		try {
			Usermaster 	usermaster = usermasterService.getUserByUserName(username);
		Long maxcounttemp = (long)0;
		String message="Username or password incorrect";
		if (usermaster != null) {
			if ((long)usermaster.getStatus() == (long)ParameterApplication.BLOCK
					.getStatus()) {
				logger.info("usermaster is blocked");
				message="The account is locked ("+usermaster.getReason_lock()+")";
				//save log
				UsermasterLog userlog=new UsermasterLog();
				userlog.setUsername(usermaster.getUsername());
				userlog.setDate_create(new Date());
				userlog.setNoidung(Constant.LOGIN);
				userlog.setNoidung1(usermaster.getReason_lock());
				userlog.setNoidung2(Constant.LOGIN_FAIL);
				userlog.setSession_log(request.getSession().getId());
			    userlog.setIplogin(request.getRemoteAddr());
			    usermasterLogService.save(userlog);
			} else if ((long)usermaster.getStatus() == (long)ParameterApplication.DELETE
					.getStatus()) {
				logger.info("usermaster is deleted");
				message ="The account is deleted";
				//save log
				UsermasterLog userlog=new UsermasterLog();
				userlog.setUsername(usermaster.getUsername());
				userlog.setDate_create(new Date());
				userlog.setNoidung(Constant.LOGIN);
				userlog.setNoidung1(ParameterApplication.DELETE.getName());
				userlog.setNoidung2(Constant.LOGIN_FAIL);
				userlog.setSession_log(request.getSession().getId());
			    userlog.setIplogin(request.getRemoteAddr());
			    usermasterLogService.save(userlog);
			} else {
				if (usermaster.getDate_login_temp() != null) {
					if (DateUtils.caculatorDateMinutes(new Date(),
							usermaster.getDate_login_temp()) <= minutesLimitLogin) {
						maxcounttemp = usermaster.getCount_login_temp();
					}
				}
				maxcounttemp++;
				if (maxcounttemp == maxCountLogin) {
					logger.info("usermaster is blocked");
					usermaster.setReason_lock(Constant.REASON_LOCK.LOGIN_EXPIRED.getName());
					usermaster
							.setStatus(ParameterApplication.BLOCK.getStatus());
					message ="The account is locked ("+Constant.REASON_LOCK.LOGIN_EXPIRED.getName()+")";
				}
				usermaster.setCount_login_temp(maxcounttemp);
				usermaster.setDate_login_temp(new Date());
				usermasterService.update(usermaster);
				//save log
				UsermasterLog userlog=new UsermasterLog();
				userlog.setUsername(usermaster.getUsername());
				userlog.setDate_create(new Date());
				userlog.setNoidung(Constant.LOGIN);
				userlog.setNoidung1(message);
				userlog.setNoidung2(Constant.LOGIN_FAIL);
				userlog.setSession_log(request.getSession().getId());
			    userlog.setIplogin(request.getRemoteAddr());
			    usermasterLogService.save(userlog);
			}
		}
		request.getSession().setAttribute("messages",message);  
		super.onAuthenticationFailure(request, response, exception);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
public static Logger getLogger() {
	return logger;
}
public static void setLogger(Logger logger) {
	MyLoginFailureHandler.logger = logger;
}
public UsermasterService getUsermasterService() {
	return usermasterService;
}
public void setUsermasterService(UsermasterService usermasterService) {
	this.usermasterService = usermasterService;
}
public Long getMinutesLimitLogin() {
	return minutesLimitLogin;
}
public void setMinutesLimitLogin(Long minutesLimitLogin) {
	this.minutesLimitLogin = minutesLimitLogin;
}
public Long getMaxCountLogin() {
	return maxCountLogin;
}
public void setMaxCountLogin(Long maxCountLogin) {
	this.maxCountLogin = maxCountLogin;
}






//	public UsermasterlogDAO getUsermasterlogDAO() {
//		return usermasterlogDAO;
//	}
//
//	public void setUsermasterlogDAO(UsermasterlogDAO usermasterlogDAO) {
//		this.usermasterlogDAO = usermasterlogDAO;
//	}



}
