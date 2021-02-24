package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.EmailUserSysMonitorDAO;

/**
 * <p>
 * Spring controller to diplay list of EmailUserSysMonitors
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class EmailUserSysMonitorListController extends
		ParameterizableViewController {

	private EmailUserSysMonitorDAO emailUserSysMonitorDAO;

	public void setEmailUserSysMonitorDAO(
			EmailUserSysMonitorDAO emailUserSysMonitorDAO) {
		this.emailUserSysMonitorDAO = emailUserSysMonitorDAO;
	}

	public EmailUserSysMonitorDAO getEmailUserSysMonitorDAO() {
		return this.emailUserSysMonitorDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("EmailUserSysMonitorlist",
				emailUserSysMonitorDAO.findAll());

		return modelAndView;
	}

}