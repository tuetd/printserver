package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.FilemasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Filemasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FilemasterListController extends ParameterizableViewController {

	private FilemasterDAO filemasterDAO;

	public void setFilemasterDAO(FilemasterDAO filemasterDAO) {
		this.filemasterDAO = filemasterDAO;
	}

	public FilemasterDAO getFilemasterDAO() {
		return this.filemasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Filemasterlist", filemasterDAO.findAll());

		return modelAndView;
	}

}