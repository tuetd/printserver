package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.EmailSysMonitorMDAO;

/**
 * <p>
 * Spring controller to diplay list of EmailSysMonitorMs
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class EmailSysMonitorMListController extends
		ParameterizableViewController {

	private EmailSysMonitorMDAO emailSysMonitorMDAO;

	public void setEmailSysMonitorMDAO(EmailSysMonitorMDAO emailSysMonitorMDAO) {
		this.emailSysMonitorMDAO = emailSysMonitorMDAO;
	}

	public EmailSysMonitorMDAO getEmailSysMonitorMDAO() {
		return this.emailSysMonitorMDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("EmailSysMonitorMlist",
				emailSysMonitorMDAO.findAll());

		return modelAndView;
	}

}