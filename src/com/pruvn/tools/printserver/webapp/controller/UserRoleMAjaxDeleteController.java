package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.UserRoleMDAO;

/**
 * <p>
 * Spring controller to delete a UserRoleM
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserRoleMAjaxDeleteController extends AbstractController {

	private UserRoleMDAO userRoleMDAO;

	public void setUserRoleMDAO(UserRoleMDAO userRoleMDAO) {
		this.userRoleMDAO = userRoleMDAO;
	}

	public UserRoleMDAO getUserRoleMDAO() {
		return this.userRoleMDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("userRoleMId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("userRoleMId") != null) {
			userRoleMDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listUserRoleM.html");
	}

}
