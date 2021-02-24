package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.SystemtypemasterDAO;

/**
 * <p>
 * Spring controller to delete a Systemtypemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SystemtypemasterAjaxDeleteController extends AbstractController {

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

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("systemtypemasterId") != null) {
			systemtypemasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listSystemtypemaster.html");
	}

}
