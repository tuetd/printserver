package com.pruvn.tools.common.hibernate.dwh.service.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.tools.common.hibernate.dwh.service.DWHouseService;
import com.pruvn.tools.common.util.GlobalConstant;
import com.pruvn.tools.common.util.UtilConverter;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;

public class DWHouseServiceImpl extends AbstractHibernateDAO<Object, Integer> implements DWHouseService{

//	public void executeStoredProcedure(String sql, List<String> paramNames,
//			List<Object> params) throws SQLException {
//		List<Object> paramValueList = new ArrayList<Object>();
//		StringTokenizer st = new StringTokenizer(sql, "@");
//		while (st.hasMoreTokens()) {
//			String sqlToken = st.nextToken();
//			if (paramNames.contains(sqlToken)) {
//				if (null == params.get(paramNames.indexOf(sqlToken))) {
//					sql = sql.replaceAll("@" + sqlToken + "@", sqlToken);
//				} else {
//					sql = sql.replaceAll("@" + sqlToken + "@", "?");
//					paramValueList
//							.add(params.get(paramNames.indexOf(sqlToken)));
//				}
//			}
//		}
//
//		CallableStatement cs = getSession().connection().prepareCall(sql);
//		for (int i = 0; i < paramValueList.size(); i++) {
//			cs.setObject(i + 1, paramValueList.get(i));
//		}
//		cs.execute();
//		
//	}
	

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void executeStoredProcedure(final String sql,final List<String> paramNames,
			final List<Object> params) {
		 getHibernateTemplate().execute(new HibernateCallback() {
			   public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Object> paramValueList = new ArrayList<Object>();
				String query=sql;
				StringTokenizer st = new StringTokenizer(query, "@");
				while (st.hasMoreTokens()) {
					String sqlToken = st.nextToken();
					if (paramNames.contains(sqlToken)) {
						if (null == params.get(paramNames.indexOf(sqlToken))) {
							query = query.replaceAll("@" + sqlToken + "@", sqlToken);
						} else {
							query = query.replaceAll("@" + sqlToken + "@", "?");
							paramValueList
									.add(params.get(paramNames.indexOf(sqlToken)));
						}
					}
				}

				CallableStatement cs = getSession().connection().prepareCall(query);
				for (int i = 0; i < paramValueList.size(); i++) {
					cs.setObject(i + 1, paramValueList.get(i));
				}
				cs.execute();
				return null;
			   }});
	}
	
//
//	public void executeDDLQuery(String sql) throws SQLException {
//		System.out.println("sql" + sql);
//		PreparedStatement pstmt = getSession().connection().prepareStatement(
//				sql);
//		pstmt.executeUpdate();
//		pstmt.close();
//		
//	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void executeDDLQuery(final String sql){
		 getHibernateTemplate().execute(new HibernateCallback() {
            public Object  doInHibernate(Session session) throws HibernateException, SQLException {	
	System.out.println("sql" + sql);
	PreparedStatement pstmt = getSession().connection().prepareStatement(
			sql);
	pstmt.executeUpdate();
	pstmt.close();
	return null;
            }});
	}
	
//	public String[][] executeQuery(String sql, List<String> fieldNames,
//			List<String> fieldTypes, List<String> paramNames,
//			List<Object> params) throws SQLException {
//		String[][] result = null;
//
//		StringTokenizer st = new StringTokenizer(sql, "&&");
//		while (st.hasMoreTokens()) {
//			String sqlToken = st.nextToken();
//			if (paramNames.contains(sqlToken)) {
//				sql = sql.replaceAll("&&" + sqlToken + "&&",
//						params.get(paramNames.indexOf(sqlToken)).toString());
//			}
//		}
//
//		List<Object> paramValueList = new ArrayList<Object>();
//		st = new StringTokenizer(sql, "@");
//		while (st.hasMoreTokens()) {
//			String sqlToken = st.nextToken();
//			if (paramNames.contains(sqlToken)) {
//				if (null == params.get(paramNames.indexOf(sqlToken))) {
//					sql = sql.replaceAll("@" + sqlToken + "@", sqlToken);
//				} else {
//					sql = sql.replaceAll("@" + sqlToken + "@", "?");
//					paramValueList
//							.add(params.get(paramNames.indexOf(sqlToken)));
//				}
//			}
//		}
//		System.out.println(sql);
//
//		PreparedStatement pstmt = getSession().connection().prepareStatement(
//				sql);
//		for (int i = 0; i < paramValueList.size(); i++) {
//			pstmt.setObject(i + 1, paramValueList.get(i));
//		}
//		// if (ProjectVariable.docName.startsWith("REPAYMENTTERM")) {
//		// pstmt.setObject(1, ProjectVariable.username);
//		// pstmt.setObject(2, ProjectVariable.dateConnected);
//		// pstmt.setObject(3, ProjectVariable.timeConnected);
//		// }
//		// if (ProjectConstant.CERTIFICATEDOC.equals(ProjectVariable.docName)) {
//		// pstmt.setObject(2, ProjectVariable.username);
//		// pstmt.setObject(3, ProjectVariable.dateConnected);
//		// pstmt.setObject(4, ProjectVariable.timeConnected);
//		// }
//
//		ResultSet rs = pstmt.executeQuery();
//		List<String[]> dataTemp = new ArrayList<String[]>();
//		while (rs.next()) {
//			String[] record = new String[fieldNames.size()];
//			for (int i = 0; i < fieldNames.size(); i++) {
//				System.out.println(fieldNames.get(i));
//				if (GlobalConstant.DATATYPE_DATE.equals(fieldTypes.get(i))) {
//					record[i] = UtilConverter.getFormattedDate(
//							GlobalConstant.DATEPATTERN_1,
//							rs.getDate(fieldNames.get(i)));
//				} else {
//					record[i] = rs.getString(fieldNames.get(i));
//				}
//			}
//			dataTemp.add(record);
//		}
//
//		result = new String[dataTemp.size()][fieldNames.size()];
//		dataTemp.toArray(result);
//
//		rs.close();
//		pstmt.close();
//		
//		return result;
//	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[][] executeQuery(final String sql,final List<String> fieldNames,
			final List<String> fieldTypes,final List<String> paramNames,
			final	List<Object> params)	{
	return getHibernateTemplate().execute(new HibernateCallback() {
	            public String[][] doInHibernate(Session session) throws HibernateException, SQLException {
		String[][] result = null;
		String query=sql;
		StringTokenizer st = new StringTokenizer(query, "&&");
		while (st.hasMoreTokens()) {
			String sqlToken = st.nextToken();
			if (paramNames.contains(sqlToken)) {
				query = query.replaceAll("&&" + sqlToken + "&&",
						params.get(paramNames.indexOf(sqlToken)).toString());
			}
		}

		List<Object> paramValueList = new ArrayList<Object>();
		st = new StringTokenizer(query, "@");
		while (st.hasMoreTokens()) {
			String sqlToken = st.nextToken();
			if (paramNames.contains(sqlToken)) {
				if (null == params.get(paramNames.indexOf(sqlToken))) {
					query = query.replaceAll("@" + sqlToken + "@", sqlToken);
				} else {
					query = query.replaceAll("@" + sqlToken + "@", "?");
					paramValueList
							.add(params.get(paramNames.indexOf(sqlToken)));
				}
			}
		}
		System.out.println(query);

		PreparedStatement pstmt = getSession().connection().prepareStatement(
				query);
		for (int i = 0; i < paramValueList.size(); i++) {
			pstmt.setObject(i + 1, paramValueList.get(i));
		}
		ResultSet rs = pstmt.executeQuery();
		List<String[]> dataTemp = new ArrayList<String[]>();
		while (rs.next()) {
			String[] record = new String[fieldNames.size()];
			for (int i = 0; i < fieldNames.size(); i++) {
				System.out.println(fieldNames.get(i));
				if (GlobalConstant.DATATYPE_DATE.equals(fieldTypes.get(i))) {
					record[i] = UtilConverter.getFormattedDate(
							GlobalConstant.DATEPATTERN_1,
							rs.getDate(fieldNames.get(i)));
				} else {
					record[i] = rs.getString(fieldNames.get(i));
				}
			}
			dataTemp.add(record);
		}

		result = new String[dataTemp.size()][fieldNames.size()];
		dataTemp.toArray(result);

		rs.close();
		pstmt.close();

		return result;
	    }});		
	}

	public void closeSession() {
		getSession().flush();
		getSession().clear();
		getSession().disconnect();
		getSession().getSessionFactory().close();
		getSession().close();		
		logger.debug("HibernateUtil session closed");
		
	}

}
