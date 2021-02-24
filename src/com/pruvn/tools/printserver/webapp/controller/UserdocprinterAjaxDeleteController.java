package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.pojo.Userdocprinter.UserdocprinterPK;
import com.pruvn.tools.printserver.UserdocprinterDAO;

/**
 * <p>
 * Spring controller to delete a Userdocprinter
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserdocprinterAjaxDeleteController extends AbstractController {

	private UserdocprinterDAO userdocprinterDAO;

	public void setUserdocprinterDAO(UserdocprinterDAO userdocprinterDAO) {
		this.userdocprinterDAO = userdocprinterDAO;
	}

	public UserdocprinterDAO getUserdocprinterDAO() {
		return this.userdocprinterDAO;
	}

	private UserdocprinterPK getPkFromRequest(HttpServletRequest request) {
		// TODO : get composite of the pk in request and return an instance of
		// UserdocprinterPK
		return null;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("userdocprinterId") != null) {
			userdocprinterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listUserdocprinter.html");
	}

}
