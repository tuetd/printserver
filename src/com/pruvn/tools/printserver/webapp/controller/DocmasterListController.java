package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.DocmasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Docmasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class DocmasterListController extends ParameterizableViewController {

	private DocmasterDAO docmasterDAO;

	public void setDocmasterDAO(DocmasterDAO docmasterDAO) {
		this.docmasterDAO = docmasterDAO;
	}

	public DocmasterDAO getDocmasterDAO() {
		return this.docmasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Docmasterlist", docmasterDAO.findAll());

		return modelAndView;
	}

}