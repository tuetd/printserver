package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.pruvn.tools.printserver.DatasourcemasterDAO;
import com.pruvn.tools.printserver.pojo.Datasourcemaster;

/**
 * <p>
 * Spring controller to search for Datasourcemasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class DatasourcemasterSearchController extends SimpleFormController {

	private DatasourcemasterDAO datasourcemasterDAO;

	public void setDatasourcemasterDAO(DatasourcemasterDAO datasourcemasterDAO) {
		this.datasourcemasterDAO = datasourcemasterDAO;
	}

	public DatasourcemasterDAO getDatasourcemasterDAO() {
		return this.datasourcemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("datasourcemasterId"));
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

		Datasourcemaster datasourcemaster = null;
		if (request.getParameter("datasourcemasterId") != null) {
			datasourcemaster = datasourcemasterDAO
					.getById(getPkFromRequest(request));
		} else {
			datasourcemaster = new Datasourcemaster();
		}

		return datasourcemaster;
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

		modelAndView.addObject("Datasourcemasterlist", datasourcemasterDAO
				.findByExample((Datasourcemaster) command, new String[0]));

		modelAndView.addAllObjects(this.referenceData(request));

		return modelAndView;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

}