package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.FileparammasterDAO;
import com.pruvn.tools.printserver.pojo.Fileparammaster;

/**
 * <p>
 * Spring controller to add or update a Fileparammaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FileparammasterAddUpdateController extends
		CancellableFormController {

	private FileparammasterDAO fileparammasterDAO;

	public void setFileparammasterDAO(FileparammasterDAO fileparammasterDAO) {
		this.fileparammasterDAO = fileparammasterDAO;
	}

	public FileparammasterDAO getFileparammasterDAO() {
		return this.fileparammasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("fileparammasterId"));
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

		Fileparammaster fileparammaster = null;
		if (request.getParameter("fileparammasterId") != null) {
			fileparammaster = fileparammasterDAO
					.getById(getPkFromRequest(request));
		} else {
			fileparammaster = new Fileparammaster();
		}

		return fileparammaster;
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
		fileparammasterDAO.saveOrUpdate((Fileparammaster) command);
	}

}
