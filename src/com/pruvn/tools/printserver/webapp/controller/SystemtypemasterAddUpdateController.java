package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.SystemtypemasterDAO;
import com.pruvn.tools.printserver.pojo.Systemtypemaster;

/**
 * <p>
 * Spring controller to add or update a Systemtypemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SystemtypemasterAddUpdateController extends
		CancellableFormController {

	private SystemtypemasterDAO systemtypemasterDAO;

	public void setSystemtypemasterDAO(SystemtypemasterDAO systemtypemasterDAO) {
		this.systemtypemasterDAO = systemtypemasterDAO;
	}

	public SystemtypemasterDAO getSystemtypemasterDAO() {
		return this.systemtypemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("systemtypemasterId"));
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

		Systemtypemaster systemtypemaster = null;
		if (request.getParameter("systemtypemasterId") != null) {
			systemtypemaster = systemtypemasterDAO
					.getById(getPkFromRequest(request));
		} else {
			systemtypemaster = new Systemtypemaster();
		}

		return systemtypemaster;
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
		systemtypemasterDAO.saveOrUpdate((Systemtypemaster) command);
	}

}
