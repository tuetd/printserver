package com.pruvn.tools.common.hibernate.finnone.service.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.tools.common.hibernate.finnone.service.FinnOneService;
import com.pruvn.tools.common.util.GlobalConstant;
import com.pruvn.tools.common.util.UtilConverter;
import com.pruvn.tools.printserver.hibernate.AbstractHibernateDAO;
import com.pruvn.tools.utils.DateUtils;

public class FinnOneServiceImpl extends AbstractHibernateDAO<Object, Integer>
		implements FinnOneService {

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
//	}

//	public void executeDDLQuery(String sql) throws SQLException {
//		System.out.println("sql" + sql);
//		PreparedStatement pstmt = getSession().connection().prepareStatement(
//				sql);
//		pstmt.executeUpdate();
//		pstmt.close();
//	}

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

//	public String errorCodeCheck(String applid) {
//		String errorCode="0";
//		try {
//			String sql = "{? = call printsrv.F_LOAN_DOC_PRINT_CHECK(?)}";
//			CallableStatement cs; cs = getSession().connection().prepareCall(sql);
//			cs.registerOutParameter(1, Types.NUMERIC);
//			cs.setObject(2, new Integer(applid));
//			cs.execute();
//			errorCode = cs.getBigDecimal(1).toPlainString();
//			System.out.println("errorCode"+errorCode);
//			cs.close();
//		} catch (DataAccessResourceFailureException e) {
//			e.printStackTrace();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return errorCode;
//	}
//
//	public Boolean checkNoSignDocument(String APPLID) {
//		Session s = null;
//		  try {
//		        s = this.getSession();
//		    	StringBuffer str = new StringBuffer("	select 1	");	
//				str.append("	from cas.los_app_applications A	");
//				str.append("	where 	");
//				str.append("	A.vc_acctount_off_cd in (select CAMPAIGN_CODE from printsrv.prt_nosign_campaign_m)	");
//				str.append("	and app_id_c='" + APPLID + "'");
//				SQLQuery query = s.createSQLQuery(str.toString());
//				List<Object> ret = query.list();
//				if (ret.size() > 0) {
//					return true;
//				}
//				return false;		
//		    } finally {
//		        if (s != null) s.close();
//		    }  
////		StringBuffer str = new StringBuffer("	select 1	");	
////		str.append("	from cas.los_app_applications A	");
////		str.append("	where 	");
////		str.append("	A.vc_acctount_off_cd in (select CAMPAIGN_CODE from printsrv.prt_nosign_campaign_m)	");
////		str.append("	and app_id_c='" + APPLID + "'");
////		Session session=getSession();
////		SQLQuery query = session.createSQLQuery(str.toString());
////		List<Object> ret = query.list();
////		session.close();
////		if (ret.size() > 0) {
////			return true;
////		}
////		
////		return false;		
//	}
//
//	public Boolean checkEnableNoSignDocument(String doc_code) {
//		Session s = null;
//		  try {
//		        s = this.getSession();
//		StringBuffer str = new StringBuffer("	select 1	");	
//		str.append("	from prt_nosign_document_m	");
//		str.append("	where doc_code = '" + doc_code + "'	");
//		SQLQuery query = s.createSQLQuery(str.toString());
//		List<Object> ret = query.list();
//		if (ret.size() > 0) {
//			return true;
//		}	
//		return false;	
//		 } finally {
//			   if (s != null) s.close();
//	}  
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String errorCodeCheck(final String applid) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
            	String errorCode="0";
            	String sql = "{? = call printsrv.F_LOAN_DOC_PRINT_CHECK(?)}";
            	CallableStatement cs; cs = getSession().connection().prepareCall(sql);
            	cs.registerOutParameter(1, Types.NUMERIC);
            	cs.setObject(2, new Integer(applid));
            	cs.execute();
            	errorCode = cs.getBigDecimal(1).toPlainString();
            	System.out.println("errorCode"+errorCode);
            	cs.close();
            	return errorCode;
            }});
		}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Boolean checkNoSignDocument(final String APPLID) {				
				return getHibernateTemplate().execute(new HibernateCallback() {
		            public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
				    	StringBuffer str = new StringBuffer("	select 1	");	
						str.append("	from cas.los_app_applications A	");
						str.append("	where 	");
						str.append("	A.vc_acctount_off_cd in (select CAMPAIGN_CODE from printsrv.prt_nosign_campaign_m)	");
						str.append("	and app_id_c='" + APPLID + "'");
						Query q = session.createSQLQuery(str.toString());
						List<Object> ret = q.list();
						if (ret.size() > 0) {
							return true;
						}	
						return false;	
		               }
		           
		        });
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Boolean checkEnableNoSignDocument(final String doc_code) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
            	StringBuffer str = new StringBuffer("	select 1	");	
        		str.append("	from prt_nosign_document_m	");
        		str.append("	where doc_code = '" + doc_code + "'	");
				Query q = session.createSQLQuery(str.toString());
				List<Object> ret = q.list();
				if (ret.size() > 0) {
					return true;
				}	
				return false;	
               }
        });		
}
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
	
	//SR452
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Double> getDataFCL(final String applid, final String runDate, final String prePayPenalty) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public List<Double> doInHibernate(Session session) throws HibernateException, SQLException {
            	BigDecimal installments = new BigDecimal(0);
            	BigDecimal penalty = new BigDecimal(0);
            	BigDecimal overdueCharges = new BigDecimal(0); 
            	BigDecimal currlpi = new BigDecimal(0);
            	BigDecimal currMnthFInt = new BigDecimal(0);
            	BigDecimal intRefund = new BigDecimal(0);
            	BigDecimal balPrincipal = new BigDecimal(0);
            	BigDecimal excessAmount = new BigDecimal(0);
            	
            	
            	String err_Msg = "";
            	List<Double> list = new ArrayList<Double>(); 
            	
            	GregorianCalendar cal = new GregorianCalendar();
            	cal.setTime(DateUtils.toDate(runDate, DateUtils.ddMMyyyy));
            	
            	String sql = "{call LENDING.GET_LOAN_BALANCE_AS_ON_DT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            	CallableStatement cs; cs = getSession().connection().prepareCall(sql);
            	cs.setObject(1, new Integer(applid));
            	cs.setObject(2, new Date(cal.getTimeInMillis()));
            	cs.setObject(3, new Integer(prePayPenalty));
            	
            	cs.registerOutParameter(4, Types.NUMERIC);
            	cs.registerOutParameter(5, Types.NUMERIC);
            	cs.registerOutParameter(6, Types.NUMERIC);
            	cs.registerOutParameter(7, Types.NUMERIC);
            	cs.registerOutParameter(8, Types.NUMERIC);
            	cs.registerOutParameter(9, Types.NUMERIC);
            	cs.registerOutParameter(10, Types.NUMERIC);
            	cs.registerOutParameter(11, Types.NUMERIC);
            	cs.registerOutParameter(12, Types.VARCHAR);
            	
            	cs.execute();
            	err_Msg = cs.getString(12);
            	if(err_Msg==null || "".equalsIgnoreCase(err_Msg)){
            		installments = cs.getBigDecimal(4);
            		penalty = cs.getBigDecimal(5);
            		overdueCharges = cs.getBigDecimal(6);
            		currlpi = cs.getBigDecimal(7);
            		currMnthFInt = cs.getBigDecimal(8);
            		intRefund = cs.getBigDecimal(9);
            		balPrincipal = cs.getBigDecimal(10);
            		excessAmount = cs.getBigDecimal(11);
            		
            		list.add(installments!=null?installments.doubleValue():0);
                	list.add(penalty!=null?penalty.doubleValue():0);
                	list.add(overdueCharges!=null?overdueCharges.doubleValue():0);
                	list.add(currlpi!=null?currlpi.doubleValue():0);
                	list.add(currMnthFInt!=null?currMnthFInt.doubleValue():0);
                	list.add(intRefund!=null?intRefund.doubleValue():0);
                	list.add(balPrincipal!=null?balPrincipal.doubleValue():0);
            	}
            	
            	cs.close();
            	return list;
            }});
		}
	
	//SR663
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String checkDueDate(final String applid, final String runDate) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
            	String err_Msg = "0";
            	
            	GregorianCalendar cal = new GregorianCalendar();
            	cal.setTime(DateUtils.toDate(runDate, DateUtils.ddMMyyyy));
            	
            	String sql = "{call CHECK_AGREEMENT_DUEDATE(?, ?, ?)}";
            	CallableStatement cs; cs = getSession().connection().prepareCall(sql);
            	cs.setObject(1, new Integer(applid));
            	cs.setObject(2, new Date(cal.getTimeInMillis()));
            	cs.registerOutParameter(3, Types.VARCHAR);
            	
            	cs.execute();
            	err_Msg = cs.getString(3);
            	cs.close();
            	return err_Msg;
            }});
		}
	
	@SuppressWarnings("unchecked")
	public int checkTB6(final String applid) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
            	int err_Msg = 1;
            	String sql = "{call CHECK_AGREEMENT_TB6(?, ?)}";
            	CallableStatement cs; cs = getSession().connection().prepareCall(sql);
            	cs.setObject(1, new Integer(applid));
            	cs.registerOutParameter(2, Types.INTEGER);
            	
            	cs.execute();
            	err_Msg = cs.getInt(2);
            	cs.close();
            	return err_Msg;
            }});
		}
	
	@SuppressWarnings("unchecked")
	public int checkClosed(final String applid) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
            	int err_Msg = 1;
            	String sql = "{call CHECK_AGREEMENT_CLOSED(?, ?)}";
            	CallableStatement cs; cs = getSession().connection().prepareCall(sql);
            	cs.setObject(1, new Integer(applid));
            	cs.registerOutParameter(2, Types.INTEGER);
            	
            	cs.execute();
            	err_Msg = cs.getInt(2);
            	cs.close();
            	return err_Msg;
            }});
		}
		
	@SuppressWarnings("unchecked")
	public String printCheckFCL(final String applid, final String runDate) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
            	String err_Msg = "0";
            	
            	GregorianCalendar cal = new GregorianCalendar();
            	cal.setTime(DateUtils.toDate(runDate, DateUtils.ddMMyyyy));
            	
            	String sql = "{call ? = F_PRINTSERVER_CHECK_FCL(?, ?)}";
            	CallableStatement cs; 
            	cs = getSession().connection().prepareCall(sql);
            	cs.registerOutParameter(1, Types.VARCHAR);
            	cs.setObject(2, new Integer(applid));
            	cs.setObject(3, new Date(cal.getTimeInMillis()));
            	
            	cs.execute();
            	err_Msg = cs.getString(1);
            	cs.close();
            	return err_Msg;
            }});
		}

	@SuppressWarnings("unchecked")
	public String checkCreditShield(final String applid,final  String runDate) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
		GregorianCalendar cal = new GregorianCalendar();
    	cal.setTime(DateUtils.toDate(runDate, "ddMMyyyy"));
		String sql = "{? = call printsrv.F_LOAN_CHECK_CREDIT_SHEILD(?,?)}";
		CallableStatement cs = getSession().connection().prepareCall(sql);
		cs.registerOutParameter(1, Types.NUMERIC);
		cs.setObject(2, new Integer(applid));
		cs.setObject(3, new java.sql.Date(cal.getTimeInMillis()));
		cs.execute();
		String errorCode = cs.getBigDecimal(1).toPlainString();
		cs.close();
		return errorCode;
		   }});
	}
	
	
	@SuppressWarnings("unchecked")
	public String printFCLPercent(final String applid) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public String doInHibernate(Session session) throws HibernateException, SQLException {
            	String result = "0";
    	    	try{
    	        	String sql = "{call ? := F_FCL_PERCENT(?)}";
    	        	CallableStatement cs = session.connection().prepareCall(sql);
    	        	cs.registerOutParameter(1, Types.VARCHAR);
    	        	cs.setObject(2, new Integer(applid));
    	        	cs.execute();
    	        	result = String.valueOf(cs.getInt(1));
    	        	cs.close();
    	    	}catch(SQLException ex){
    	    		ex.printStackTrace();
    	    	}finally{
    	    		session.close();
    	    	}
    	    	return result;
		   }});
	}
}