package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.FieldmasterDAO;
import com.pruvn.tools.printserver.pojo.Fieldmaster;

/**
 * <p>
 * Spring controller to add or update a Fieldmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FieldmasterAddUpdateController extends CancellableFormController {

	private FieldmasterDAO fieldmasterDAO;

	public void setFieldmasterDAO(FieldmasterDAO fieldmasterDAO) {
		this.fieldmasterDAO = fieldmasterDAO;
	}

	public FieldmasterDAO getFieldmasterDAO() {
		return this.fieldmasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("fieldmasterId"));
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

		Fieldmaster fieldmaster = null;
		if (request.getParameter("fieldmasterId") != null) {
			fieldmaster = fieldmasterDAO.getById(getPkFromRequest(request));
		} else {
			fieldmaster = new Fieldmaster();
		}

		return fieldmaster;
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
		fieldmasterDAO.saveOrUpdate((Fieldmaster) command);
	}

}
