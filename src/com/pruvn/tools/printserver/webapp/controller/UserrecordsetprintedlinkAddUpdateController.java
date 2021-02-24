package com.pruvn.tools.printserver.webapp.controller;

import com.pruvn.tools.printserver.pojo.Userrecordsetprintedlink.UserrecordsetprintedlinkPK;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.UserrecordsetprintedlinkDAO;
import com.pruvn.tools.printserver.pojo.Userrecordsetprintedlink;

/**
 * <p>
 * Spring controller to add or update a Userrecordsetprintedlink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserrecordsetprintedlinkAddUpdateController extends
		CancellableFormController {

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

		Userrecordsetprintedlink userrecordsetprintedlink = null;
		if (request.getParameter("userrecordsetprintedlinkId") != null) {
			userrecordsetprintedlink = userrecordsetprintedlinkDAO
					.getById(getPkFromRequest(request));
		} else {
			userrecordsetprintedlink = new Userrecordsetprintedlink();
		}

		return userrecordsetprintedlink;
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
		userrecordsetprintedlinkDAO
				.saveOrUpdate((Userrecordsetprintedlink) command);
	}

}
