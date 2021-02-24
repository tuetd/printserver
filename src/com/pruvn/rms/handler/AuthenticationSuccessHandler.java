package com.pruvn.rms.handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.pruvn.rms.common.Constants;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private UserMService userMService;
	private Integer expirationDays;
	private UserLogService userLogService;
	
	public UserLogService getUserLogService() {
		return userLogService;
	}

	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}

	/**
	 * @return the expirationDays
	 */
	public Integer getExpirationDays() {
		return expirationDays;
	}

	/**
	 * @param expirationDays the expirationDays to set
	 */
	public void setExpirationDays(Integer expirationDays) {
		this.expirationDays = expirationDays;
	}

	/**
	 * @return the userMService
	 */
	public UserMService getUserMService() {
		return userMService;
	}

	/**
	 * @param userMService the userMService to set
	 */
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
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
		
		// update last login date
		try {
			UserM user = userMService.getUserByUserName(username);
			user.setLast_login_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			user.setCountlogintemp(null);
			user.setDatelogintemp(null);
			user.setReasonlock(null);
			//Save user log 
			UserLog userlog= new UserLog();
        	userlog.setLogDate(new Date());
        	userlog.setUsername(authentication.getName());
        	//userlog.setNoidung(Constants.LOGIN);
        	//userlog.setNoidung1(Constants.SUCCESS_LOGIN);
        	userlog.setRemoteIP(request.getRemoteAddr());
        	userlog.setSession(request.getSession().getId());
        	
        	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
        	userlog.setActivity(ACTIONS.LOGIN.toString());
        	userlog.setInput("Username is: " + username);
        	userlog.setOutput(Constants.SUCCESS_LOGIN);
        	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
        	//userlog.setStatusNote(statusNote);
        	
        	
        	userLogService.save(userlog);
			userMService.saveOrUpdate(user);
			
			if (user.getLastChangedPw() == null || 
					(user.getLastChangedPw() != null && DateUtils.countDaysBetween(user.getLastChangedPw(), Calendar.getInstance().getTime()) > expirationDays)) {
				response.sendRedirect("auth/changepwd.html");
			} else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		} catch (UserException e) {
			logger.error(ExceptionUtils.composeExceptionMessage(e));
		}
	}

}
