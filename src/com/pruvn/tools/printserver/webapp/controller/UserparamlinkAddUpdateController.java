package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.UserparamlinkDAO;
import com.pruvn.tools.printserver.pojo.Userparamlink;

/**
 * <p>
 * Spring controller to add or update a Userparamlink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserparamlinkAddUpdateController extends CancellableFormController {

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

		Userparamlink userparamlink = null;
		if (request.getParameter("userparamlinkId") != null) {
			userparamlink = userparamlinkDAO.getById(getPkFromRequest(request));
		} else {
			userparamlink = new Userparamlink();
		}

		return userparamlink;
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
		userparamlinkDAO.saveOrUpdate((Userparamlink) command);
	}

}
