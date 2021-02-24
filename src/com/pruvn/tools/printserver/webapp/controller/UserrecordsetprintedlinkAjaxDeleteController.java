package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.pojo.Userrecordsetprintedlink.UserrecordsetprintedlinkPK;
import com.pruvn.tools.printserver.UserrecordsetprintedlinkDAO;

/**
 * <p>
 * Spring controller to delete a Userrecordsetprintedlink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserrecordsetprintedlinkAjaxDeleteController extends
		AbstractController {

	private UserrecordsetprintedlinkDAO userrecordsetprintedlinkDAO;

	public void setUserrecordsetprintedlinkDAO(
			UserrecordsetprintedlinkDAO userrecordsetprintedlinkDAO) {
		this.userrecordsetprintedlinkDAO = userrecordsetprintedlinkDAO;
	}

	public UserrecordsetprintedlinkDAO getUserrecordsetprintedlinkDAO() {
		return this.userrecordsetprintedlinkDAO;
	}

	private UserrecordsetprintedlinkPK getPkFromRequest(
			HttpServletRequest request) {
		// TODO : get composite of the pk in request and return an instance of
		// UserrecordsetprintedlinkPK
		return null;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("userrecordsetprintedlinkId") != null) {
			userrecordsetprintedlinkDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listUserrecordsetprintedlink.html");
	}

}
