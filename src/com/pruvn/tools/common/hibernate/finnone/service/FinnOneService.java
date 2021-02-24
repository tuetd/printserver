package com.pruvn.tools.common.hibernate.finnone.service;
import java.sql.SQLException;
import java.util.List;

import com.pruvn.tools.printserver.GenericDAO;
public interface FinnOneService extends GenericDAO<Object, Integer>{
	void executeStoredProcedure(String sql, List<String> paramNames, List<Object> params) throws SQLException;
	void executeDDLQuery(String value) throws SQLException;
	String[][] executeQuery(String sql, List<String> fieldNames, List<String> fieldTypes, List<String> paramNames, List<Object> params) throws SQLException;
	String errorCodeCheck(String applid);
	Boolean checkNoSignDocument(String applid);
	Boolean checkEnableNoSignDocument(String doc_code);
	void closeSession();
	List<Double> getDataFCL(final String applid, final String runDate, final String prePayPenalty) ;
	public String checkDueDate(final String applid, final String runDate);
	int checkTB6(String string);
	int checkClosed(String string);
	String checkCreditShield(String applid, String runDate);
	String printFCLPercent(String applid);
}
