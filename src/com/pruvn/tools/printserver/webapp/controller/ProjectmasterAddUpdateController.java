package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.ProjectmasterDAO;
import com.pruvn.tools.printserver.pojo.Projectmaster;

/**
 * <p>
 * Spring controller to add or update a Projectmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class ProjectmasterAddUpdateController extends CancellableFormController {

	private ProjectmasterDAO projectmasterDAO;

	public void setProjectmasterDAO(ProjectmasterDAO projectmasterDAO) {
		this.projectmasterDAO = projectmasterDAO;
	}

	public ProjectmasterDAO getProjectmasterDAO() {
		return this.projectmasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("projectmasterId"));
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

		Projectmaster projectmaster = null;
		if (request.getParameter("projectmasterId") != null) {
			projectmaster = projectmasterDAO.getById(getPkFromRequest(request));
		} else {
			projectmaster = new Projectmaster();
		}

		return projectmaster;
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
		projectmasterDAO.saveOrUpdate((Projectmaster) command);
	}

}
