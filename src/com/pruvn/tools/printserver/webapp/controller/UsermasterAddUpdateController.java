package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Usermaster;

/**
 * <p>
 * Spring controller to add or update a Usermaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UsermasterAddUpdateController extends CancellableFormController {

	private UsermasterDAO usermasterDAO;

	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	public UsermasterDAO getUsermasterDAO() {
		return this.usermasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("usermasterId"));
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

		Usermaster usermaster = null;
		if (request.getParameter("usermasterId") != null) {
			usermaster = usermasterDAO.getById(getPkFromRequest(request));
		} else {
			usermaster = new Usermaster();
		}

		return usermaster;
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
		usermasterDAO.saveOrUpdate((Usermaster) command);
	}

}
