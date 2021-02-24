package com.pruvn.tools.printserver.webapp.controller;

import com.pruvn.tools.printserver.pojo.Userdocprinter.UserdocprinterPK;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.UserdocprinterDAO;
import com.pruvn.tools.printserver.pojo.Userdocprinter;

/**
 * <p>
 * Spring controller to add or update a Userdocprinter
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class UserdocprinterAddUpdateController extends
		CancellableFormController {

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
		userdocprinterDAO.saveOrUpdate((Userdocprinter) command);
	}

}
