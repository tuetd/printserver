package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.UserparammasterDAO;
import com.pruvn.tools.printserver.pojo.Userparammaster;

/**
 * <p>
 * Spring controller to add or update a Userparammaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserparammasterAddUpdateController extends
		CancellableFormController {

	private UserparammasterDAO userparammasterDAO;

	public void setUserparammasterDAO(UserparammasterDAO userparammasterDAO) {
		this.userparammasterDAO = userparammasterDAO;
	}

	public UserparammasterDAO getUserparammasterDAO() {
		return this.userparammasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("userparammasterId"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
	 * (javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Userparammaster userparammaster = null;
		if (request.getParameter("userparammasterId") != null) {
			userparammaster = userparammasterDAO
					.getById(getPkFromRequest(request));
		} else {
			userparammaster = new Userparammaster();
		}

		return userparammaster;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.SimpleFormController#doSubmitAction
	 * (java.lang.Object)
	 */
	@Override
	protected void doSubmitAction(Object command) throws Exception {
		userparammasterDAO.saveOrUpdate((Userparammaster) command);
	}

}
