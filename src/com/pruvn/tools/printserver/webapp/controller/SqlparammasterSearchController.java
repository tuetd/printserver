package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.pruvn.tools.printserver.SqlparammasterDAO;
import com.pruvn.tools.printserver.pojo.Sqlparammaster;

/**
 * <p>
 * Spring controller to search for Sqlparammasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SqlparammasterSearchController extends SimpleFormController {

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

		Sqlparammaster sqlparammaster = null;
		if (request.getParameter("sqlparammasterId") != null) {
			sqlparammaster = sqlparammasterDAO
					.getById(getPkFromRequest(request));
		} else {
			sqlparammaster = new Sqlparammaster();
		}

		return sqlparammaster;
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

		modelAndView.addObject("Sqlparammasterlist", sqlparammasterDAO
				.findByExample((Sqlparammaster) command, new String[0]));

		modelAndView.addAllObjects(this.referenceData(request));

		return modelAndView;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(request, binder);

	}

}