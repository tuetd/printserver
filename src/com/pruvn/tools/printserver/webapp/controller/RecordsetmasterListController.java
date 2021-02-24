package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.RecordsetmasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Recordsetmasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class RecordsetmasterListController extends
		ParameterizableViewController {

	private RecordsetmasterDAO recordsetmasterDAO;

	public void setRecordsetmasterDAO(RecordsetmasterDAO recordsetmasterDAO) {
		this.recordsetmasterDAO = recordsetmasterDAO;
	}

	public RecordsetmasterDAO getRecordsetmasterDAO() {
		return this.recordsetmasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Recordsetmasterlist",
				recordsetmasterDAO.findAll());

		return modelAndView;
	}

}