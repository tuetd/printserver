package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.pruvn.tools.printserver.BookmarkmasterDAO;

/**
 * <p>
 * Spring controller to diplay list of Bookmarkmasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:43 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class BookmarkmasterListController extends ParameterizableViewController {

	private BookmarkmasterDAO bookmarkmasterDAO;

	public void setBookmarkmasterDAO(BookmarkmasterDAO bookmarkmasterDAO) {
		this.bookmarkmasterDAO = bookmarkmasterDAO;
	}

	public BookmarkmasterDAO getBookmarkmasterDAO() {
		return this.bookmarkmasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = super.handleRequestInternal(request,
				response);

		modelAndView.addObject("Bookmarkmasterlist",
				bookmarkmasterDAO.findAll());

		return modelAndView;
	}

}