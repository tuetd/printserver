package com.pruvn.tools.printserver.webapp.controller;

import com.pruvn.tools.printserver.pojo.Fileparamfilelink.FileparamfilelinkPK;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.pruvn.tools.printserver.FileparamfilelinkDAO;
import com.pruvn.tools.printserver.pojo.Fileparamfilelink;

/**
 * <p>
 * Spring controller to search for Fileparamfilelinks
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FileparamfilelinkSearchController extends SimpleFormController {

	private FileparamfilelinkDAO fileparamfilelinkDAO;

	public void setFileparamfilelinkDAO(
			FileparamfilelinkDAO fileparamfilelinkDAO) {
		this.fileparamfilelinkDAO = fileparamfilelinkDAO;
	}

	public FileparamfilelinkDAO getFileparamfilelinkDAO() {
		return this.fileparamfilelinkDAO;
	}

	private FileparamfilelinkPK getPkFromRequest(HttpServletRequest request) {
		// TODO : get composite of the pk in request and return an instance of
		// FileparamfilelinkPK
		return null;
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

		Fileparamfilelink fileparamfilelink = null;
		if (request.getParameter("fileparamfilelinkId") != null) {
			fileparamfilelink = fileparamfilelinkDAO
					.getById(getPkFromRequest(request));
		} else {
			fileparamfilelink = new Fileparamfilelink();
		}

		return fileparamfilelink;
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

		modelAndView.addObject("Fileparamfilelinklist", fileparamfilelinkDAO
				.findByExample((Fileparamfilelink) command, new String[0]));

		modelAndView.addAllObjects(this.referenceData(request));

		return modelAndView;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

}