package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.SqlparammasterDAO;
import com.pruvn.tools.printserver.pojo.Sqlparammaster;

/**
 * <p>
 * Spring controller to add or update a Sqlparammaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SqlparammasterAddUpdateController extends
		CancellableFormController {

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
		sqlparammasterDAO.saveOrUpdate((Sqlparammaster) command);
	}

}
