package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.OpenOfficeTrafficDAO;
import com.pruvn.tools.printserver.pojo.OpenOfficeTraffic;

/**
 * <p>
 * Spring controller to add or update a OpenOfficeTraffic
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class OpenOfficeTrafficAddUpdateController extends
		CancellableFormController {

	private OpenOfficeTrafficDAO openOfficeTrafficDAO;

	public void setOpenOfficeTrafficDAO(
			OpenOfficeTrafficDAO openOfficeTrafficDAO) {
		this.openOfficeTrafficDAO = openOfficeTrafficDAO;
	}

	public OpenOfficeTrafficDAO getOpenOfficeTrafficDAO() {
		return this.openOfficeTrafficDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("openOfficeTrafficId"));
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

		OpenOfficeTraffic openOfficeTraffic = null;
		if (request.getParameter("openOfficeTrafficId") != null) {
			openOfficeTraffic = openOfficeTrafficDAO
					.getById(getPkFromRequest(request));
		} else {
			openOfficeTraffic = new OpenOfficeTraffic();
		}

		return openOfficeTraffic;
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
		openOfficeTrafficDAO.saveOrUpdate((OpenOfficeTraffic) command);
	}

}
