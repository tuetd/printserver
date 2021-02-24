package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.EmailSysMonitorMDAO;

/**
 * <p>
 * Spring controller to delete a EmailSysMonitorM
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class EmailSysMonitorMAjaxDeleteController extends AbstractController {

	private EmailSysMonitorMDAO emailSysMonitorMDAO;

	public void setEmailSysMonitorMDAO(EmailSysMonitorMDAO emailSysMonitorMDAO) {
		this.emailSysMonitorMDAO = emailSysMonitorMDAO;
	}

	public EmailSysMonitorMDAO getEmailSysMonitorMDAO() {
		return this.emailSysMonitorMDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("emailSysMonitorMId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("emailSysMonitorMId") != null) {
			emailSysMonitorMDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listEmailSysMonitorM.html");
	}

}
