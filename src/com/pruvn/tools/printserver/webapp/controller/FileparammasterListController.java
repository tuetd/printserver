package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.FileparammasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Fileparammasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FileparammasterListController extends
		ParameterizableViewController {

	private FileparammasterDAO fileparammasterDAO;

	public void setFileparammasterDAO(FileparammasterDAO fileparammasterDAO) {
		this.fileparammasterDAO = fileparammasterDAO;
	}

	public FileparammasterDAO getFileparammasterDAO() {
		return this.fileparammasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Fileparammasterlist",
				fileparammasterDAO.findAll());

		return modelAndView;
	}

}