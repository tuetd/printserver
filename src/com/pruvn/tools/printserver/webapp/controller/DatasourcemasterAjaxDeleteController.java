package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.DatasourcemasterDAO;

/**
 * <p>
 * Spring controller to delete a Datasourcemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class DatasourcemasterAjaxDeleteController extends AbstractController {

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

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("datasourcemasterId") != null) {
			datasourcemasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listDatasourcemaster.html");
	}

}
