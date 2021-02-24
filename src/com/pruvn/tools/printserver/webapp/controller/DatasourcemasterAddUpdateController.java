package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.DatasourcemasterDAO;
import com.pruvn.tools.printserver.pojo.Datasourcemaster;

/**
 * <p>
 * Spring controller to add or update a Datasourcemaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class DatasourcemasterAddUpdateController extends
		CancellableFormController {

	private DatasourcemasterDAO datasourcemasterDAO;

	public void setDatasourcemasterDAO(DatasourcemasterDAO datasourcemasterDAO) {
		this.datasourcemasterDAO = datasourcemasterDAO;
	}

	public DatasourcemasterDAO getDatasourcemasterDAO() {
		return this.datasourcemasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("datasourcemasterId"));
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

		Datasourcemaster datasourcemaster = null;
		if (request.getParameter("datasourcemasterId") != null) {
			datasourcemaster = datasourcemasterDAO
					.getById(getPkFromRequest(request));
		} else {
			datasourcemaster = new Datasourcemaster();
		}

		return datasourcemaster;
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
		datasourcemasterDAO.saveOrUpdate((Datasourcemaster) command);
	}

}
