package com.pruvn.rms.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.CSRecordDAO;
import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>
 * Hibernate DAO layer for UserMs
 * </p>
 * <p>
 * Generated at Mon Jul 11 14:56:25 ICT 2011
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class CSRecordHibernateDAO extends AbstractHibernateDAO<CSRecord, Integer>
		implements CSRecordDAO {

	private String errorCode = "";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String callInvolve(final String action, final String username, final String stage,
			final Integer recordId, final String... values) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
	            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
	            	session.doWork(new Work() {
	    				@Override
	    				public void execute(Connection arg0) throws SQLException {
	    					String sql = SqlConstant.STORED_INVOLVE;// "{? = call printsrv.F_LOAN_DOC_PRINT_CHECK(?)}";
	    					CallableStatement cs;
	    					cs = arg0.prepareCall(sql);
	    					cs.setObject(1, action);
	    					cs.setObject(2, username);
	    					cs.setObject(3, stage);
	    					cs.setObject(4, recordId);
	    					cs.registerOutParameter(5, Types.NUMERIC);
	    					cs.registerOutParameter(6, Types.VARCHAR);
	    					String param = null;
	    					int length = values.length;
	    					for(int index = 0; index < 10; index ++)
	    					{
	    						param = null;
	    						if(length > index){
	    							param = values[index];	
	    						}
	    						cs.setObject(index + 7, param);
	    					}
	    					cs.execute();
	    					errorCode = cs.getString(6);
	    					System.out.println("errorCode" + cs.getString(6));
	    				}
	    			});
	            	return errorCode;
	            }
	        });
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		} catch (HibernateException e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		}
		return errorCode;
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public CSRecord getRecordRMTById(final Integer recordId) {
//		return getHibernateTemplate().execute(new HibernateCallback() {
//            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
//            	SQLQuery query = session.createSQLQuery(
//        				SqlConstant.SELECT_RMT_BY_ID);
//        		query.setParameter("id", recordId);
//        		query.addEntity(CSRecord.class);
//        		return (CSRecord) query.uniqueResult();
//            }
//        });
//	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String  synchronizeData() {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
	            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
	            	session.doWork(new Work() {
	    				@Override
	    				public void execute(Connection arg0) throws SQLException {
	    					String sql = SqlConstant.SYNCHRONIZE_DATA;
	    					CallableStatement cs;
	    					cs = arg0.prepareCall(sql);
	    					cs.registerOutParameter(1, Types.VARCHAR);
	    					cs.execute();
	    					errorCode = cs.getString(1);
	    					System.out.println("errorCode" + cs.getString(1));
	    				}
	    			});
	            	return errorCode;
	            }
	        });
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		} catch (HibernateException e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		}
		return errorCode;
	}
	
	public CSRecord searchByAgreementNo(final String agreementNo) {
		List<CSRecord> list = (List<CSRecord>) findByCriteria(Restrictions.like(
				"agreementno", agreementNo, MatchMode.ANYWHERE));
		for (CSRecord object : list) {
			if (object.getClass() != CSRecord.class) {
				return object;
			}
		}
		// if(list != null && list.size() > 0)
		// return list.get(0);
		return null;

	}
}
