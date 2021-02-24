package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.FilemasterDAO;

/**
 * <p>
 * Spring controller to delete a Filemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FilemasterAjaxDeleteController extends AbstractController {

	private FilemasterDAO filemasterDAO;

	public void setFilemasterDAO(FilemasterDAO filemasterDAO) {
		this.filemasterDAO = filemasterDAO;
	}

	public FilemasterDAO getFilemasterDAO() {
		return this.filemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("filemasterId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("filemasterId") != null) {
			filemasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listFilemaster.html");
	}

}
