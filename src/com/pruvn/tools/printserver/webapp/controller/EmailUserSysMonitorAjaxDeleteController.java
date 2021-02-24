package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.EmailUserSysMonitorDAO;

/**
 * <p>
 * Spring controller to delete a EmailUserSysMonitor
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class EmailUserSysMonitorAjaxDeleteController extends AbstractController {

	private EmailUserSysMonitorDAO emailUserSysMonitorDAO;

	public void setEmailUserSysMonitorDAO(
			EmailUserSysMonitorDAO emailUserSysMonitorDAO) {
		this.emailUserSysMonitorDAO = emailUserSysMonitorDAO;
	}

	public EmailUserSysMonitorDAO getEmailUserSysMonitorDAO() {
		return this.emailUserSysMonitorDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("emailUserSysMonitorId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("emailUserSysMonitorId") != null) {
			emailUserSysMonitorDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listEmailUserSysMonitor.html");
	}

}
