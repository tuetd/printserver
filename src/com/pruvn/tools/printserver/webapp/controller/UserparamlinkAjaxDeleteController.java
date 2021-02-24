package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.UserparamlinkDAO;

/**
 * <p>
 * Spring controller to delete a Userparamlink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserparamlinkAjaxDeleteController extends AbstractController {

	private UserparamlinkDAO userparamlinkDAO;

	public void setUserparamlinkDAO(UserparamlinkDAO userparamlinkDAO) {
		this.userparamlinkDAO = userparamlinkDAO;
	}

	public UserparamlinkDAO getUserparamlinkDAO() {
		return this.userparamlinkDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("userparamlinkId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("userparamlinkId") != null) {
			userparamlinkDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listUserparamlink.html");
	}

}
