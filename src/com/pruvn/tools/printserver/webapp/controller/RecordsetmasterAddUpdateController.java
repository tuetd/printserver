package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.RecordsetmasterDAO;
import com.pruvn.tools.printserver.pojo.Recordsetmaster;

/**
 * <p>
 * Spring controller to add or update a Recordsetmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class RecordsetmasterAddUpdateController extends
		CancellableFormController {

	private RecordsetmasterDAO recordsetmasterDAO;

	public void setRecordsetmasterDAO(RecordsetmasterDAO recordsetmasterDAO) {
		this.recordsetmasterDAO = recordsetmasterDAO;
	}

	public RecordsetmasterDAO getRecordsetmasterDAO() {
		return this.recordsetmasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("recordsetmasterId"));
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

		Recordsetmaster recordsetmaster = null;
		if (request.getParameter("recordsetmasterId") != null) {
			recordsetmaster = recordsetmasterDAO
					.getById(getPkFromRequest(request));
		} else {
			recordsetmaster = new Recordsetmaster();
		}

		return recordsetmaster;
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
		recordsetmasterDAO.saveOrUpdate((Recordsetmaster) command);
	}

}
