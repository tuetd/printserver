package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.SqlquerymasterDAO;

/**
 * <p>
 * Spring controller to delete a Sqlquerymaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SqlquerymasterAjaxDeleteController extends AbstractController {

	private SqlquerymasterDAO sqlquerymasterDAO;

	public void setSqlquerymasterDAO(SqlquerymasterDAO sqlquerymasterDAO) {
		this.sqlquerymasterDAO = sqlquerymasterDAO;
	}

	public SqlquerymasterDAO getSqlquerymasterDAO() {
		return this.sqlquerymasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("sqlquerymasterId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("sqlquerymasterId") != null) {
			sqlquerymasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listSqlquerymaster.html");
	}

}
