package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.ServerconfigDAO;
import com.pruvn.tools.printserver.pojo.Serverconfig;

/**
 * <p>
 * Spring controller to add or update a Serverconfig
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class ServerconfigAddUpdateController extends CancellableFormController {

	private ServerconfigDAO serverconfigDAO;

	public void setServerconfigDAO(ServerconfigDAO serverconfigDAO) {
		this.serverconfigDAO = serverconfigDAO;
	}

	public ServerconfigDAO getServerconfigDAO() {
		return this.serverconfigDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("serverconfigId"));
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

		Serverconfig serverconfig = null;
		if (request.getParameter("serverconfigId") != null) {
			serverconfig = serverconfigDAO.getById(getPkFromRequest(request));
		} else {
			serverconfig = new Serverconfig();
		}

		return serverconfig;
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
		serverconfigDAO.saveOrUpdate((Serverconfig) command);
	}

}
