package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.StatusmasterDAO;
import com.pruvn.tools.printserver.pojo.Statusmaster;

/**
 * <p>
 * Spring controller to add or update a Statusmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class StatusmasterAddUpdateController extends CancellableFormController {

	private StatusmasterDAO statusmasterDAO;

	public void setStatusmasterDAO(StatusmasterDAO statusmasterDAO) {
		this.statusmasterDAO = statusmasterDAO;
	}

	public StatusmasterDAO getStatusmasterDAO() {
		return this.statusmasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("statusmasterId"));
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

		Statusmaster statusmaster = null;
		if (request.getParameter("statusmasterId") != null) {
			statusmaster = statusmasterDAO.getById(getPkFromRequest(request));
		} else {
			statusmaster = new Statusmaster();
		}

		return statusmaster;
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
		statusmasterDAO.saveOrUpdate((Statusmaster) command);
	}

}
