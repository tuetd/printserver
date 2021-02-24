package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.BookmarkmasterDAO;

/**
 * <p>
 * Spring controller to delete a Bookmarkmaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class BookmarkmasterAjaxDeleteController extends AbstractController {

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

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("bookmarkmasterId") != null) {
			bookmarkmasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listBookmarkmaster.html");
	}

}
