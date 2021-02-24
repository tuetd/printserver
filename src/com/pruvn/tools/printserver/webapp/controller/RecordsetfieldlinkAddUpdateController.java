package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.RecordsetfieldlinkDAO;
import com.pruvn.tools.printserver.pojo.Recordsetfieldlink;

/**
 * <p>
 * Spring controller to add or update a Recordsetfieldlink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class RecordsetfieldlinkAddUpdateController extends
		CancellableFormController {

	private RecordsetfieldlinkDAO recordsetfieldlinkDAO;

	public void setRecordsetfieldlinkDAO(
			RecordsetfieldlinkDAO recordsetfieldlinkDAO) {
		this.recordsetfieldlinkDAO = recordsetfieldlinkDAO;
	}

	public RecordsetfieldlinkDAO getRecordsetfieldlinkDAO() {
		return this.recordsetfieldlinkDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("recordsetfieldlinkId"));
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

		Recordsetfieldlink recordsetfieldlink = null;
		if (request.getParameter("recordsetfieldlinkId") != null) {
			recordsetfieldlink = recordsetfieldlinkDAO
					.getById(getPkFromRequest(request));
		} else {
			recordsetfieldlink = new Recordsetfieldlink();
		}

		return recordsetfieldlink;
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
		recordsetfieldlinkDAO.saveOrUpdate((Recordsetfieldlink) command);
	}

}
