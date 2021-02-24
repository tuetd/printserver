package com.pruvn.rms.handler;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.pruvn.rms.common.Constants;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.Constant.ParameterApplication;

@Controller
public class MyLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler  {
	private static Logger logger = Logger
			.getLogger(MyLoginFailureHandler.class);
	private UserMService userMService;
	private UserLogService userLogService;
	private int minutesLimitLogin;
	private int maxCountLogin;
	private int expirationDays;
	private Map<String, String> failureUrlMap = new HashMap<String, String>();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		  String url = failureUrlMap.get(exception.getClass().getName());
		try {
			if (url != null) {
	            getRedirectStrategy().sendRedirect(request, response, url);
	        } else {
	        	String username = (String) request.getSession().getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY);
	        	//String username = (String) request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME");
	        	username = StringEscapeUtils.unescapeHtml(username);
				UserM usermaster  =  userMService.getUserByUserName(username);
				if (usermaster!=null){
						int maxcounttemp = 0;
						String message = exception.getMessage();
						if (usermaster != null&&!(usermaster.getIsActived() == null || usermaster.getIsActived() == ParameterApplication.NOACTIVE.getStatus())) {
							if (usermaster.getDatelogintemp() != null) {
						if (DateUtils.caculatorDateMinutes(new Date(),
								usermaster.getDatelogintemp()) <= minutesLimitLogin) {
							maxcounttemp = usermaster.getCountlogintemp();
						}
					}
					maxcounttemp++;
					if (maxcounttemp == maxCountLogin) {
						logger.info("usermaster is blocked");
						usermaster
								.setReasonlock(Constants.LOGIN_EXPIRED.toString());
						usermaster.setIsActived(ParameterApplication.NOACTIVE.getStatus());
						message = "The account is locked ("
								+ Constants.LOGIN_EXPIRED.toString() + ")";
					}
					usermaster.setCountlogintemp(maxcounttemp);
					usermaster.setDatelogintemp(new Date());
					userMService.saveOrUpdate(usermaster);
				}
				// log user:
				UserLog userlog = new UserLog();
				userlog.setLogDate(new Date());
				userlog.setUsername(username);
				//userlog.setNoidung(Constants.LOGIN.toString());
				//userlog.setNoidung1(Constants.INVALID_LOGIN.toString());
				//userlog.setNoidung2(message);
				userlog.setRemoteIP(request.getRemoteAddr());
				userlog.setSession(request.getSession().getId());
				
	        	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
	        	userlog.setActivity(ACTIONS.LOGIN.toString());
	        	userlog.setInput("Username is: " + username);
	        	userlog.setOutput(Constants.INVALID_LOGIN);
	        	userlog.setStatus(LOG_STATUS.FAILURE.toString());
	        	userlog.setStatusNote(message);
	        	
				userLogService.save(userlog);
				request.getSession().setAttribute("messages", message);
				super.onAuthenticationFailure(request, response, exception);
			
	        }else{
	        	request.getSession().setAttribute("messages", "Username not existed!");
				super.onAuthenticationFailure(request, response, exception);
	        }
				
	        }
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserMService getUserMService() {
		return userMService;
	}

	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}

	public UserLogService getUserLogService() {
		return userLogService;
	}

	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
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

	public int getExpirationDays() {
		return expirationDays;
	}

	public void setExpirationDays(int expirationDays) {
		this.expirationDays = expirationDays;
	}

	public Map<String, String> getFailureUrlMap() {
		return failureUrlMap;
	}

	public void setFailureUrlMap(Map<String, String> failureUrlMap) {
		this.failureUrlMap = failureUrlMap;
	}
	 public void setExceptionMappings(Map<?,?> failureUrlMap) {
	        this.failureUrlMap.clear();
	        for (Map.Entry<?,?> entry : failureUrlMap.entrySet()) {
	            Object exception = entry.getKey();
	            Object url = entry.getValue();
	            Assert.isInstanceOf(String.class, exception, "Exception key must be a String (the exception classname).");
	            Assert.isInstanceOf(String.class, url, "URL must be a String");
	            Assert.isTrue(UrlUtils.isValidRedirectUrl((String)url), "Not a valid redirect URL: " + url);
	            this.failureUrlMap.put((String)exception, (String)url);
	        }
	    }
}
