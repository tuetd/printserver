package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.RecordsetmasterDAO;

/**
 * <p>
 * Spring controller to delete a Recordsetmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class RecordsetmasterAjaxDeleteController extends AbstractController {

	private RecordsetmasterDAO recordsetmasterDAO;

	public void setRecordsetmasterDAO(RecordsetmasterDAO recordsetmasterDAO) {
		this.recordsetmasterDAO = recordsetmasterDAO;
	}

	public RecordsetmasterDAO getRecordsetmasterDAO() {
		return this.recordsetmasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("recordsetmasterId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("recordsetmasterId") != null) {
			recordsetmasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listRecordsetmaster.html");
	}

}
