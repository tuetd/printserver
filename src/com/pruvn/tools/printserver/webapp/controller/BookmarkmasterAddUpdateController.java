package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.BookmarkmasterDAO;
import com.pruvn.tools.printserver.pojo.Bookmarkmaster;

/**
 * <p>
 * Spring controller to add or update a Bookmarkmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class BookmarkmasterAddUpdateController extends
		CancellableFormController {

	private BookmarkmasterDAO bookmarkmasterDAO;

	public void setBookmarkmasterDAO(BookmarkmasterDAO bookmarkmasterDAO) {
		this.bookmarkmasterDAO = bookmarkmasterDAO;
	}

	public BookmarkmasterDAO getBookmarkmasterDAO() {
		return this.bookmarkmasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("bookmarkmasterId"));
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

		Bookmarkmaster bookmarkmaster = null;
		if (request.getParameter("bookmarkmasterId") != null) {
			bookmarkmaster = bookmarkmasterDAO
					.getById(getPkFromRequest(request));
		} else {
			bookmarkmaster = new Bookmarkmaster();
		}

		return bookmarkmaster;
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
		bookmarkmasterDAO.saveOrUpdate((Bookmarkmaster) command);
	}

}
