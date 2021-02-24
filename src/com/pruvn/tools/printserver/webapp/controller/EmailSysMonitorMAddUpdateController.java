package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.EmailSysMonitorMDAO;
import com.pruvn.tools.printserver.pojo.EmailSysMonitorM;

/**
 * <p>
 * Spring controller to add or update a EmailSysMonitorM
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class EmailSysMonitorMAddUpdateController extends
		CancellableFormController {

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

		EmailSysMonitorM emailSysMonitorM = null;
		if (request.getParameter("emailSysMonitorMId") != null) {
			emailSysMonitorM = emailSysMonitorMDAO
					.getById(getPkFromRequest(request));
		} else {
			emailSysMonitorM = new EmailSysMonitorM();
		}

		return emailSysMonitorM;
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
		emailSysMonitorMDAO.saveOrUpdate((EmailSysMonitorM) command);
	}

}
