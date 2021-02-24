package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.SystemtypemasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Systemtypemasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SystemtypemasterListController extends
		ParameterizableViewController {

	private SystemtypemasterDAO systemtypemasterDAO;

	public void setSystemtypemasterDAO(SystemtypemasterDAO systemtypemasterDAO) {
		this.systemtypemasterDAO = systemtypemasterDAO;
	}

	public SystemtypemasterDAO getSystemtypemasterDAO() {
		return this.systemtypemasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Systemtypemasterlist",
				systemtypemasterDAO.findAll());

		return modelAndView;
	}

}