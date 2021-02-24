package com.pruvn.tools.printserver.webapp.controller;

import com.pruvn.tools.printserver.pojo.Userdocprinter.UserdocprinterPK;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.pruvn.tools.printserver.UserdocprinterDAO;
import com.pruvn.tools.printserver.pojo.Userdocprinter;

/**
 * <p>
 * Spring controller to search for Userdocprinters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserdocprinterSearchController extends SimpleFormController {

	private UserdocprinterDAO userdocprinterDAO;

	public void setUserdocprinterDAO(UserdocprinterDAO userdocprinterDAO) {
		this.userdocprinterDAO = userdocprinterDAO;
	}

	public UserdocprinterDAO getUserdocprinterDAO() {
		return this.userdocprinterDAO;
	}

	private UserdocprinterPK getPkFromRequest(HttpServletRequest request) {
		// TODO : get composite of the pk in request and return an instance of
		// UserdocprinterPK
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

		Userdocprinter userdocprinter = null;
		if (request.getParameter("userdocprinterId") != null) {
			userdocprinter = userdocprinterDAO
					.getById(getPkFromRequest(request));
		} else {
			userdocprinter = new Userdocprinter();
		}

		return userdocprinter;
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

		modelAndView.addObject("Userdocprinterlist", userdocprinterDAO
				.findByExample((Userdocprinter) command, new String[0]));

		modelAndView.addAllObjects(this.referenceData(request));

		return modelAndView;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

}