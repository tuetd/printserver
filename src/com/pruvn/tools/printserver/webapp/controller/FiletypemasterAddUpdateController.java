package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.FiletypemasterDAO;
import com.pruvn.tools.printserver.pojo.Filetypemaster;

/**
 * <p>
 * Spring controller to add or update a Filetypemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FiletypemasterAddUpdateController extends
		CancellableFormController {

	private FiletypemasterDAO filetypemasterDAO;

	public void setFiletypemasterDAO(FiletypemasterDAO filetypemasterDAO) {
		this.filetypemasterDAO = filetypemasterDAO;
	}

	public FiletypemasterDAO getFiletypemasterDAO() {
		return this.filetypemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("filetypemasterId"));
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

		Filetypemaster filetypemaster = null;
		if (request.getParameter("filetypemasterId") != null) {
			filetypemaster = filetypemasterDAO
					.getById(getPkFromRequest(request));
		} else {
			filetypemaster = new Filetypemaster();
		}

		return filetypemaster;
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
		filetypemasterDAO.saveOrUpdate((Filetypemaster) command);
	}

}
