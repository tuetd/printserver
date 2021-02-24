package com.pruvn.rms.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordDAO;
import com.pruvn.rms.domain.Record;
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
public class RecordHibernateDAO extends AbstractHibernateDAO<Record, Integer>
		implements RecordDAO {

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Record getRecordRMTById(final Integer recordId) {
		return (Record) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	SQLQuery query = session.createSQLQuery(
        				SqlConstant.SELECT_RMT_BY_ID);
        		query.setParameter("id", recordId);
        		query.addEntity(Record.class);
        		return (Record) query.uniqueResult();
            }
        });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Record getRecordRMTReturnById(final Integer recordId) {
		return (Record) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	SQLQuery query = session.createSQLQuery(
        				SqlConstant.SELECT_RMT_RETURN_BY_ID);
        		query.setParameter("id", recordId);
        		query.addEntity(Record.class);
        		return (Record) query.uniqueResult();
            }
        });
	}
	
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
	
	public Record searchByAgreementNo(final String agreementNo) {
		List<Record> list = (List<Record>) findByCriteria(Restrictions.like(
				"agreementno", agreementNo, MatchMode.ANYWHERE));
		for (Record object : list) {
			if (object.getClass() != Record.class) {
				return object;
			}
		}
		// if(list != null && list.size() > 0)
		// return list.get(0);
		return null;

	}
	
	public Record searchRecord(final String agreementNo) {
		List<Record> list = (List<Record>) findByCriteria(Restrictions.and(Restrictions.like(
				"agreementno", agreementNo, MatchMode.ANYWHERE), Restrictions.like(
						"agreementno", agreementNo, MatchMode.ANYWHERE)));
		for (Record object : list) {
			if (object.getClass() != Record.class) {
				return object;
			}
		}
		// if(list != null && list.size() > 0)
		// return list.get(0);
		return null;

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Record> getAllRecords(final Map<String, Object> filters) {
		return (List<Record>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RECORD_LIST_ACL);
        		buildFilterQuerySearch(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());       		
        		query.addEntity(Record.class);
        		List<Record> result = query.list();
        		return result;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Record> getAllRecordsCreditShield(final Map<String, Object> filters) {
		return (List<Record>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RECORD_CREDIT_SHIELD);
        		buildFilterQueryCreditShield(filters, sqlQuery);
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());       		
        		query.addEntity(Record.class);
        		List<Record> result = query.list();
        		return result;
            }
        });
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String callAction(final String stage, final String action, final String userName, final String id, final String[] values) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
	            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
	            	session.doWork(new Work() {
	    				@Override
	    				public void execute(Connection arg0) throws SQLException {
	    					String sql = SqlConstant.STORED_ACTION;
	    					CallableStatement cs;
	    					cs = arg0.prepareCall(sql);
	    				
	    					cs.setObject(1, action);
	    					cs.setObject(2, userName);
	    					cs.setObject(3, stage);
	    					cs.setObject(4,  Integer.parseInt(id));
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

	@SuppressWarnings("unchecked")
	@Override
	public String getRefNoSeq() {
		return (String) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		StringBuffer sqlQuery  = new StringBuffer("select RMS_REF_NO_SEQ.nextval from dual");
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());       		
        		if(!query.list().isEmpty()) {
        			BigDecimal result = (BigDecimal) query.list().get(0);
        			return SqlConstant.CONST_WS_REF_ID_REFIX + String.valueOf(result);
        		}
        		return null;
            }
        });
	}

}
