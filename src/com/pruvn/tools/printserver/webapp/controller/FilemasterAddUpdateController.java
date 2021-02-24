package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.FilemasterDAO;
import com.pruvn.tools.printserver.pojo.Filemaster;

/**
 * <p>
 * Spring controller to add or update a Filemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FilemasterAddUpdateController extends CancellableFormController {

	private FilemasterDAO filemasterDAO;

	public void setFilemasterDAO(FilemasterDAO filemasterDAO) {
		this.filemasterDAO = filemasterDAO;
	}

	public FilemasterDAO getFilemasterDAO() {
		return this.filemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("filemasterId"));
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

		Filemaster filemaster = null;
		if (request.getParameter("filemasterId") != null) {
			filemaster = filemasterDAO.getById(getPkFromRequest(request));
		} else {
			filemaster = new Filemaster();
		}

		return filemaster;
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
		filemasterDAO.saveOrUpdate((Filemaster) command);
	}

}
