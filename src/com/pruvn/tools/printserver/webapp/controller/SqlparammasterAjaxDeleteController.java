package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pruvn.tools.printserver.SqlparammasterDAO;

/**
 * <p>
 * Spring controller to delete a Sqlparammaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SqlparammasterAjaxDeleteController extends AbstractController {

	private SqlparammasterDAO sqlparammasterDAO;

	public void setSqlparammasterDAO(SqlparammasterDAO sqlparammasterDAO) {
		this.sqlparammasterDAO = sqlparammasterDAO;
	}

	public SqlparammasterDAO getSqlparammasterDAO() {
		return this.sqlparammasterDAO;
	}

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("sqlparammasterId"));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("sqlparammasterId") != null) {
			sqlparammasterDAO.deleteById(getPkFromRequest(request));
		}

		// TODO: when it will be ajax : return null;
		return new ModelAndView("redirect:/listSqlparammaster.html");
	}

}
