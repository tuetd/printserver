package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.pojo.Fileparamfilelink.FileparamfilelinkPK;
import com.pruvn.tools.printserver.FileparamfilelinkDAO;

/**
 * <p>
 * Spring controller to delete a Fileparamfilelink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FileparamfilelinkAjaxDeleteController extends AbstractController {

	private FileparamfilelinkDAO fileparamfilelinkDAO;

	public void setFileparamfilelinkDAO(
			FileparamfilelinkDAO fileparamfilelinkDAO) {
		this.fileparamfilelinkDAO = fileparamfilelinkDAO;
	}

	public FileparamfilelinkDAO getFileparamfilelinkDAO() {
		return this.fileparamfilelinkDAO;
	}

	private FileparamfilelinkPK getPkFromRequest(HttpServletRequest request) {
		// TODO : get composite of the pk in request and return an instance of
		// FileparamfilelinkPK
		return null;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("fileparamfilelinkId") != null) {
			fileparamfilelinkDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listFileparamfilelink.html");
	}

}
