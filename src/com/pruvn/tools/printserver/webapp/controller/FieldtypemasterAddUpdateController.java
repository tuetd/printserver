package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.FieldtypemasterDAO;
import com.pruvn.tools.printserver.pojo.Fieldtypemaster;

/**
 * <p>
 * Spring controller to add or update a Fieldtypemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FieldtypemasterAddUpdateController extends
		CancellableFormController {

	private FieldtypemasterDAO fieldtypemasterDAO;

	public void setFieldtypemasterDAO(FieldtypemasterDAO fieldtypemasterDAO) {
		this.fieldtypemasterDAO = fieldtypemasterDAO;
	}

	public FieldtypemasterDAO getFieldtypemasterDAO() {
		return this.fieldtypemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("fieldtypemasterId"));
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

		Fieldtypemaster fieldtypemaster = null;
		if (request.getParameter("fieldtypemasterId") != null) {
			fieldtypemaster = fieldtypemasterDAO
					.getById(getPkFromRequest(request));
		} else {
			fieldtypemaster = new Fieldtypemaster();
		}

		return fieldtypemaster;
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
		fieldtypemasterDAO.saveOrUpdate((Fieldtypemaster) command);
	}

}
