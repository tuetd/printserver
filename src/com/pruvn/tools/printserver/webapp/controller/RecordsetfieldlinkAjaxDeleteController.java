package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.RecordsetfieldlinkDAO;

/**
 * <p>
 * Spring controller to delete a Recordsetfieldlink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class RecordsetfieldlinkAjaxDeleteController extends AbstractController {

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

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("recordsetfieldlinkId") != null) {
			recordsetfieldlinkDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listRecordsetfieldlink.html");
	}

}
