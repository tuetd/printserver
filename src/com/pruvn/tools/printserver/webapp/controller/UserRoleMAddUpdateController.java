package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.UserRoleMDAO;
import com.pruvn.tools.printserver.pojo.UserRoleM;

/**
 * <p>
 * Spring controller to add or update a UserRoleM
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserRoleMAddUpdateController extends CancellableFormController {

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

		UserRoleM userRoleM = null;
		if (request.getParameter("userRoleMId") != null) {
			userRoleM = userRoleMDAO.getById(getPkFromRequest(request));
		} else {
			userRoleM = new UserRoleM();
		}

		return userRoleM;
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
		userRoleMDAO.saveOrUpdate((UserRoleM) command);
	}

}
