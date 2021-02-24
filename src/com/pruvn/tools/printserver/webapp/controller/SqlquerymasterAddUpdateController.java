package com.pruvn.tools.printserver.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.pruvn.tools.printserver.SqlquerymasterDAO;
import com.pruvn.tools.printserver.pojo.Sqlquerymaster;

/**
 * <p>
 * Spring controller to add or update a Sqlquerymaster
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:44 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
public class SqlquerymasterAddUpdateController extends
		CancellableFormController {

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

		Sqlquerymaster sqlquerymaster = null;
		if (request.getParameter("sqlquerymasterId") != null) {
			sqlquerymaster = sqlquerymasterDAO
					.getById(getPkFromRequest(request));
		} else {
			sqlquerymaster = new Sqlquerymaster();
		}

		return sqlquerymaster;
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
		sqlquerymasterDAO.saveOrUpdate((Sqlquerymaster) command);
	}

}
