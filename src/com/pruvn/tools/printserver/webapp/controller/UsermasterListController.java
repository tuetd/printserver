package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Usermaster;

/**
 * <p>
 * Spring controller to diplay list of Usermasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UsermasterListController extends ParameterizableViewController {

	private UsermasterDAO usermasterDAO;

	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	public UsermasterDAO getUsermasterDAO() {
		return this.usermasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		Usermaster usermaster = new Usermaster();

		modelAndView.addObject("Usermasterlist", usermasterDAO.findAll());
		modelAndView.addObject("usermaster", usermaster);

		return modelAndView;
	}

}