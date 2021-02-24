package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.UserparammasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Userparammasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserparammasterListController extends
		ParameterizableViewController {

	private UserparammasterDAO userparammasterDAO;

	public void setUserparammasterDAO(UserparammasterDAO userparammasterDAO) {
		this.userparammasterDAO = userparammasterDAO;
	}

	public UserparammasterDAO getUserparammasterDAO() {
		return this.userparammasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Userparammasterlist",
				userparammasterDAO.findAll());

		return modelAndView;
	}

}