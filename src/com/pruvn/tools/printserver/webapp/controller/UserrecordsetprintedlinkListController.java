package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.UserrecordsetprintedlinkDAO;

/**
 * <p>
 * Spring controller to diplay list of Userrecordsetprintedlinks
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserrecordsetprintedlinkListController extends
		ParameterizableViewController {

	private UserrecordsetprintedlinkDAO userrecordsetprintedlinkDAO;

	public void setUserrecordsetprintedlinkDAO(
			UserrecordsetprintedlinkDAO userrecordsetprintedlinkDAO) {
		this.userrecordsetprintedlinkDAO = userrecordsetprintedlinkDAO;
	}

	public UserrecordsetprintedlinkDAO getUserrecordsetprintedlinkDAO() {
		return this.userrecordsetprintedlinkDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Userrecordsetprintedlinklist",
				userrecordsetprintedlinkDAO.findAll());

		return modelAndView;
	}

}