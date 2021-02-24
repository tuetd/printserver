package com.pruvn.tools.printserver.webapp.controller;

import com.pruvn.tools.printserver.pojo.Fileparamfilelink.FileparamfilelinkPK;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.FileparamfilelinkDAO;
import com.pruvn.tools.printserver.pojo.Fileparamfilelink;

/**
 * <p>
 * Spring controller to add or update a Fileparamfilelink
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class FileparamfilelinkAddUpdateController extends
		CancellableFormController {

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
		fileparamfilelinkDAO.saveOrUpdate((Fileparamfilelink) command);
	}

}
