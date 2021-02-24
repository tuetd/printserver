package com.pruvn.tools.common.hibernate.dwh.service;

import java.sql.SQLException;
import java.util.List;

import com.pruvn.tools.printserver.GenericDAO;

public interface DWHouseService extends GenericDAO<Object, Integer>{
	void executeStoredProcedure(String sql, List<String> paramNames, List<Object> params) throws SQLException;
	void executeDDLQuery(String value) throws SQLException;
	String[][] executeQuery(String sql, List<String> fieldNames, List<String> fieldTypes, List<String> paramNames, List<Object> params) throws SQLException;
	void closeSession();
}
