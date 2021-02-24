package com.pruvn.rms.aop;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.exception.PasswordExpiredException;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.DateUtils;
import com.pruvn.rms.utils.ExceptionUtils;

@Aspect
public class AuthenticationAspect {
private static final Logger LOGGER = Logger.getLogger(AuthenticationAspect.class);
	
	private UserMService userMService;

	@Before("execution(* com.pruvn.rms.controller.*.*(..)) &&" +
			" !within(com.pruvn.rms.controller.ChangePwdController) &&" +
			" !within(com.pruvn.rms.controller.ResetPwdController) &&" +
			" !within(com.pruvn.rms.controller.LoginController)")
	public void logBefore(JoinPoint joinPoint ) throws IOException, PasswordExpiredException {

		
		LOGGER.info("AuthenticationAspect is running!");
		LOGGER.info("perform before aspect on point : " + joinPoint.getSignature().getName());
		LOGGER.info("******");
		
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		String username = auth.getName();
		LOGGER.info("logged user " + username);
		
		// update last login date
		try {
			UserM user = userMService.getUserByUserName(username);
			user.setLast_login_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			userMService.saveOrUpdate(user);
			
			if (user.getLastChangedPw() == null || 
					(user.getLastChangedPw() != null && DateUtils.countDaysBetween(user.getLastChangedPw(), Calendar.getInstance().getTime()) > 90)) {
				throw new PasswordExpiredException();
			}
		} catch (UserException e) {
			
			LOGGER.error(ExceptionUtils.composeExceptionMessage(e));
		}
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
	
	
}
