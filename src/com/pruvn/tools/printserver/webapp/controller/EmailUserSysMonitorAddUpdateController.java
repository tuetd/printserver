package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.EmailUserSysMonitorDAO;
import com.pruvn.tools.printserver.pojo.EmailUserSysMonitor;

/**
 * <p>
 * Spring controller to add or update a EmailUserSysMonitor
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class EmailUserSysMonitorAddUpdateController extends
		CancellableFormController {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
	 * (javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		EmailUserSysMonitor emailUserSysMonitor = null;
		if (request.getParameter("emailUserSysMonitorId") != null) {
			emailUserSysMonitor = emailUserSysMonitorDAO
					.getById(getPkFromRequest(request));
		} else {
			emailUserSysMonitor = new EmailUserSysMonitor();
		}

		return emailUserSysMonitor;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.SimpleFormController#doSubmitAction
	 * (java.lang.Object)
	 */
	@Override
	protected void doSubmitAction(Object command) throws Exception {
		emailUserSysMonitorDAO.saveOrUpdate((EmailUserSysMonitor) command);
	}

}
