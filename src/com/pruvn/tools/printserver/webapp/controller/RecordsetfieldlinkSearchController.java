package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.pruvn.tools.printserver.RecordsetfieldlinkDAO;
import com.pruvn.tools.printserver.pojo.Recordsetfieldlink;

/**
 * <p>
 * Spring controller to search for Recordsetfieldlinks
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class RecordsetfieldlinkSearchController extends SimpleFormController {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException bindException) throws Exception {

		ModelAndView modelAndView = super.onSubmit(request, response, command,
				bindException);

		modelAndView.addObject("Recordsetfieldlinklist", recordsetfieldlinkDAO
				.findByExample((Recordsetfieldlink) command, new String[0]));

		modelAndView.addAllObjects(this.referenceData(request));

		return modelAndView;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

}