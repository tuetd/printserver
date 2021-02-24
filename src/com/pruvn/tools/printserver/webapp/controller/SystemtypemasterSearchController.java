package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.pruvn.tools.printserver.SystemtypemasterDAO;
import com.pruvn.tools.printserver.pojo.Systemtypemaster;

/**
 * <p>
 * Spring controller to search for Systemtypemasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SystemtypemasterSearchController extends SimpleFormController {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException bindException) throws Exception {

		ModelAndView modelAndView = super.onSubmit(request, response, command,
				bindException);

		modelAndView.addObject("Systemtypemasterlist", systemtypemasterDAO
				.findByExample((Systemtypemaster) command, new String[0]));

		modelAndView.addAllObjects(this.referenceData(request));

		return modelAndView;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

}