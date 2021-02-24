package com.pruvn.tools.common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;

//import ndlong.common.hibernate.HibernateUtil;

public class DBProcessor {
	/**
	 * ver 1.0
	 * @param sysName
	 * @param configConn
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection ORACLEConnect(String sysName, Connection configConn) 
			throws ClassNotFoundException, SQLException {	
		Connection result = null;			
		PreparedStatement pstmt = configConn.prepareStatement("SELECT HOST, PORT, DB, USERNAME, PASSWORD FROM DATABASECONFIG WHERE SYSNAME = ?");
		pstmt.setObject(1, sysName);
		ResultSet rs = pstmt.executeQuery();		
		if (rs.next()) {
			String host = rs.getString("HOST");
			String port = rs.getString("PORT");
			String db = rs.getString("DB");
			String username = rs.getString("USERNAME");
			String password = rs.getString("PASSWORD");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + db;
			result = DriverManager.getConnection(url, username, password);
		}
		rs.close();
		pstmt.close();				
		
		return result;
	}
	
	public static Connection SQLSERVERConnect(String host, String port, String db, String username, String password) 
			throws ClassNotFoundException, SQLException {	
		Connection result = null;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + db + ";user=" + username + ";password=" + password + ";";
		result = DriverManager.getConnection(connectionUrl);	
		
		return result;
	}
	
	/**
	 * ver 1.1
	 * @param host
	 * @param port
	 * @param db
	 * @param username
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection ORACLEConnect(String host, String port, String db, String username, String password) 
			throws ClassNotFoundException, SQLException {		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + db;
		return DriverManager.getConnection(url, username, password);		
	}
	
	public static Connection ACCESSConnect(String ODBCName) throws SQLException, ClassNotFoundException {		
		String url = "jdbc:odbc:" + ODBCName;		
		// Load the jdbc-odbc bridge driver
		Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");		
		// Attempt to connect to a driver. Each one
		// of the registered drivers will be loaded until
		// one is found that can process this URL		
		return DriverManager.getConnection(url);		
	}	
	
	
	public static Connection MySQLConnect(String host, String port, String db, String username) 
			throws ClassNotFoundException, SQLException {		
		//Register the JDBC driver for MySQL.
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + username + "&password=&useUnicode=true&characterEncoding=utf8";	    
//	    return DriverManager.getConnection(url, username, "");		
	    return DriverManager.getConnection(url);
	}
	
	public static Connection MySQLConnect(String host, String port, String username) 
			throws ClassNotFoundException, SQLException {		
		//Register the JDBC driver for MySQL.
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + host + ":" + port + "/";
		return DriverManager.getConnection(url, username, "");		
	}
	
	public static Connection MySQLConnectWithPassword(String host, String port, String username, String password, String db) 
			throws ClassNotFoundException, SQLException {		
		//Register the JDBC driver for MySQL.
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + username + "&password=" + password + "&useUnicode=true&characterEncoding=utf8";
		return DriverManager.getConnection(url);		
}
	
	public static void closeConnection(Connection conn) {
		try {
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}
		
	/**
	 * 0-SERVERNAME 1-PORT 2-DBNAME 3-USERNAME 4-PASSWORD
	 * @param conn
	 * @param systemName
	 * @return
	 * @throws SQLException
	 */
	public static String[] getServerConfig(Connection conn, String systemName) throws SQLException {
		String[] result = new String[5];  
		PreparedStatement pstmt = conn.prepareStatement("SELECT SERVERNAME, PORT, DBNAME, USERNAME, PASSWORD FROM cfclongdev.SERVERCONFIG " +
															"WHERE SYSTEMNAME = ?");
		pstmt.setObject(1, systemName);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			result[0] = rs.getString("SERVERNAME");
			result[1] = rs.getString("PORT");			
			result[2] = rs.getString("DBNAME");
			result[3] = rs.getString("USERNAME");
			result[4] = rs.getString("PASSWORD");
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	public static void insertDataToDB(String[] columnNames, Object[] data, String tableName, Connection conn) throws SQLException {
		String sql = "INSERT INTO " + tableName + "(";
		for (int i = 0; i < columnNames.length; i++) {
			sql += columnNames[i] + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ") VALUES(";
		for (int i = 0; i < data.length; i++) {
			sql += "?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < data.length; i++) {
			pstmt.setObject(i + 1, data[i]);
		}		
		
		pstmt.executeUpdate();
		pstmt.close();	
	}	
	
	public static Integer insertDataToDBWithGeneratedID(String[] columnNames, Object[] data, String tableName, Connection conn) throws SQLException {
		Integer result = null;
		
		String sql = "INSERT INTO " + tableName + "(";
		for (int i = 0; i < columnNames.length; i++) {
			sql += columnNames[i] + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ") VALUES(";
		for (int i = 0; i < data.length; i++) {
			sql += "?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < data.length; i++) {
			pstmt.setObject(i + 1, data[i]);
		}		
		
		pstmt.executeUpdate();
		
		ResultSet uprs = pstmt.getGeneratedKeys();
		if (uprs.next()) {
			result = new Integer(uprs.getInt(1));
		}
		
		uprs.close();
		pstmt.close();
		
		return result;
	}
	
	public static List<String> getServerconfig(String systemName, Connection conn) throws SQLException {
		List<String> result = new ArrayList<String>();
		
		String sql = "SELECT * FROM serverconfig where systemname = ?";
		

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, systemName);		
		ResultSet rs = pstmt.executeQuery();	
		
		while (rs.next()) {			
			result.add(rs.getString("SERVERNAME"));
			result.add(rs.getString("PORT"));
			result.add(rs.getString("DBNAME"));
			result.add(rs.getString("USERNAME"));
			result.add(rs.getString("PASSWORD"));			
		}
		
		rs.close();
		pstmt.close();
		return result;
	}		
	
	public static Connection getODBCConn(String name) throws SQLException {
		String url = "jdbc:odbc:" + name;
		Connection conn = DriverManager.getConnection(url);		
		return conn;
	}
	
	public static Connection getConnectionFromContext(String contextName) throws NamingException, SQLException {
		Context context = new InitialContext();		
		Context envCont = (Context)context.lookup("java:comp/env");		
		DataSource dataSource = (DataSource)envCont.lookup(contextName);
		return dataSource.getConnection();
	}	
	
	public static void executeDDLQuery(String sql, Connection conn) throws SQLException {		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();	
		pstmt.close();		
	}
	
	/**
	 * added 24-11-2008
	 * @param sql
	 * @param conn
	 * @param paramNames
	 * @param params
	 * @throws SQLException
	 */
	public static void executeStoredProcedure(String sql, Connection conn, List<String> paramNames, List<Object> params) throws SQLException {
		List<Object> paramValueList = new ArrayList<Object>();
		StringTokenizer st = new StringTokenizer(sql, "@");		
		while (st.hasMoreTokens()) {
			String sqlToken = st.nextToken();
			if (paramNames.contains(sqlToken)) {
				if (null == params.get(paramNames.indexOf(sqlToken))) {
					sql = sql.replaceAll("@" + sqlToken + "@", sqlToken);
				}
				else {
					sql = sql.replaceAll("@" + sqlToken + "@", "?");
					paramValueList.add(params.get(paramNames.indexOf(sqlToken)));
				}
			}
		}
		
		CallableStatement cs = conn.prepareCall(sql);
		for (int i = 0; i < paramValueList.size(); i++) {							
			cs.setObject(i + 1, paramValueList.get(i));				
		}		
		cs.execute();		
	}
	/**
	 * 
	 * @param sql
	 * @param fieldNames
	 * @param paramNames
	 * @param params
	 * @param session
	 * @return 
	 * @throws HibernateException 
	 * @throws SQLException
	 */
	public static void executeStoredProcedure(String sql, Session session,
			List<String> paramNames, List<Object> params) throws HibernateException, SQLException {
		List<Object> paramValueList = new ArrayList<Object>();
		StringTokenizer st = new StringTokenizer(sql, "@");		
		while (st.hasMoreTokens()) {
			String sqlToken = st.nextToken();
			if (paramNames.contains(sqlToken)) {
				if (null == params.get(paramNames.indexOf(sqlToken))) {
					sql = sql.replaceAll("@" + sqlToken + "@", sqlToken);
				}
				else {
					sql = sql.replaceAll("@" + sqlToken + "@", "?");
					paramValueList.add(params.get(paramNames.indexOf(sqlToken)));
				}
			}
		}
		System.out.println("sql"+sql);
		CallableStatement cs = session.connection().prepareCall(sql);
		for (int i = 0; i < paramValueList.size(); i++) {							
			cs.setObject(i + 1, paramValueList.get(i));				
		}		
		cs.execute();	
		
	}
	
	
	public static void executeStoredProcedure(String sql, Connection conn, List<Object> params) throws SQLException {
		CallableStatement cs = conn.prepareCall(sql);
		for (int i = 0; i < params.size(); i++) {							
			cs.setObject(i + 1, params.get(i));				
		}		
		cs.execute();		
	}
	
	/**
	 * 
	 * @param sql
	 * @param fieldNames
	 * @param paramNames
	 * @param params
	 * @param conn
	 * @return 
	 * @throws SQLException
	 */
	public static String[][] executeQuery(String sql, List<String> fieldNames, List<String> fieldTypes, List<String> paramNames, List<Object> params, Connection conn) throws SQLException {
		String[][] result = null;
		
		StringTokenizer st = new StringTokenizer(sql, "&&");		
		while (st.hasMoreTokens()) {
			String sqlToken = st.nextToken();
			if (paramNames.contains(sqlToken)) {				
				sql = sql.replaceAll("&&" + sqlToken + "&&", params.get(paramNames.indexOf(sqlToken)).toString());				
			}
		}		
		
		List<Object> paramValueList = new ArrayList<Object>();
		st = new StringTokenizer(sql, "@");		
		while (st.hasMoreTokens()) {
			String sqlToken = st.nextToken();
			if (paramNames.contains(sqlToken)) {
				if (null == params.get(paramNames.indexOf(sqlToken))) {
					sql = sql.replaceAll("@" + sqlToken + "@", sqlToken);
				}
				else {
					sql = sql.replaceAll("@" + sqlToken + "@", "?");
					paramValueList.add(params.get(paramNames.indexOf(sqlToken)));
				}
			}
		}
		System.out.println(sql);
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < paramValueList.size(); i++) {							
			pstmt.setObject(i + 1, paramValueList.get(i));				
		}
//		if (ProjectVariable.docName.startsWith("REPAYMENTTERM")) {
//			pstmt.setObject(1, ProjectVariable.username);
//			pstmt.setObject(2, ProjectVariable.dateConnected);
//			pstmt.setObject(3, ProjectVariable.timeConnected);
//		}
//		if (ProjectConstant.CERTIFICATEDOC.equals(ProjectVariable.docName)) {
//			pstmt.setObject(2, ProjectVariable.username);
//			pstmt.setObject(3, ProjectVariable.dateConnected);
//			pstmt.setObject(4, ProjectVariable.timeConnected);
//		}
			
		
		ResultSet rs = pstmt.executeQuery();
		List<String[]> dataTemp = new ArrayList<String[]>();		
		while (rs.next()) {
			String[] record = new String[fieldNames.size()];
			for (int i = 0; i < fieldNames.size(); i++) {
				System.out.println(fieldNames.get(i));
				if (GlobalConstant.DATATYPE_DATE.equals(fieldTypes.get(i))) {
					record[i] = UtilConverter.getFormattedDate(GlobalConstant.DATEPATTERN_1, rs.getDate(fieldNames.get(i)));
				}
				else {
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
	}

	
	
//	public static void closeSessionHibernate(HibernateUtil hibernateUtil ) {
//		if (null != hibernateUtil) {
//			hibernateUtil.closeSession();
//		}
//	}
}
