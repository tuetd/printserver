package com.pruvn.rms.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Controller;

import com.pruvn.rms.common.Constants;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;

@Controller
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{
	private UserLogService userLogService;
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
        	UserLog userlog= new UserLog();
        	userlog.setLogDate(new Date());
        	userlog.setUsername(authentication.getName());
        	//userlog.setNoidung(Constants.LOGOUT.toString());
        	//userlog.setNoidung1(Constants.SUCCESS_LOGOUT.toString());
        	userlog.setRemoteIP(request.getRemoteAddr());
        	userlog.setSession(request.getSession().getId());
        	
        	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
        	userlog.setActivity(ACTIONS.LOGOUT.toString());
        	userlog.setInput("Username is: " + authentication.getName());
        	userlog.setOutput(Constants.SUCCESS_LOGOUT);
        	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
        	//userlog.setStatusNote(statusNote);
        	userLogService.save(userlog);
        }
     
        super.onLogoutSuccess(request, response, authentication);       
    }
	public UserLogService getUserLogService() {
		return userLogService;
	}
	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}
	
	

}
