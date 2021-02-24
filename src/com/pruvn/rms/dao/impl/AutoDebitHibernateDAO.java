package com.pruvn.rms.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.AutoDebitDAO;
import com.pruvn.rms.domain.AutoDebit;
import com.pruvn.rms.domain.DocumentMaintenance;
import com.pruvn.rms.domain.RecordFL;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for GroupMs</p>
 * <p>Generated at Mon Jul 11 15:00:10 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class AutoDebitHibernateDAO extends
		AbstractHibernateDAO<AutoDebit, Integer> implements
		AutoDebitDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<AutoDebit> findAll_AD(final String username, final String stage, final Map<String, Object> filters) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		String sqlQuery  = SqlConstant.SELECT_AUTO_DEBIT_LIST_AD;
        		sqlQuery = buildADFilterQuery(filters, sqlQuery);
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery); 
        		query.setParameter("username", username);
        		query.setParameter("stage", stage); 
        		query.addEntity(AutoDebit.class);
        		List<RecordFL> result = query.list();
        		return result;
            }
        });
	}
	// document maintenance
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DocumentMaintenance> getAllDocument(final String username, final String stage, final Map<String, Object> filters) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		String sqlQuery  = SqlConstant.SELECT_DOCUMENT_MAINTENANCE_LST;
        		//sqlQuery = buildADFilterQuery(filters, sqlQuery);
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery); 
        		query.setParameter("username", username);
        		query.setParameter("stage", stage); 
        		query.addEntity(DocumentMaintenance.class);
        		List<DocumentMaintenance> result = query.list();
        		return result;
            }
        });
	}	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int countAll_AD(final String username, final String stage, final Map<String, Object> filters) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		String sqlQuery  = SqlConstant.COUNT_AUTO_DEBIT_LIST_AD;
        		sqlQuery = buildADFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery); 
        		
        		query.setParameter("username", username);
        		query.setParameter("stage", stage); 
        		
        		return ((BigDecimal)query.list().get(0)).intValue();  
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AutoDebit findByID(final Long id) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	
                 StringBuffer sqlQuery  = new StringBuffer(SqlConstant.FIND_BY_ID);
             	//	buildFilterQuery(filters, sqlQuery);
             		
             		SQLQuery query = session.createSQLQuery(
             				sqlQuery.toString()); 
             		query.setParameter("ID", id);
             		query.addEntity(AutoDebit.class);
             		List<AutoDebit> result = query.list();
             		return result.get(0);
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<AutoDebit> getlistBank() {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	
                 StringBuffer sqlQuery  = new StringBuffer(SqlConstant.getListBank);
             	//	buildFilterQuery(filters, sqlQuery);
             		
             		SQLQuery query = session.createSQLQuery(
             				sqlQuery.toString()); 
             		
             		List<AutoDebit> result = query.list();
             		return result;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<AutoDebit> getlistBranch() {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	
                 StringBuffer sqlQuery  = new StringBuffer(SqlConstant.getListBranch);
             	//	buildFilterQuery(filters, sqlQuery);
             		
             		SQLQuery query = session.createSQLQuery(
             				sqlQuery.toString()); 
             		
             		List<AutoDebit> result = query.list();
             		return result;
            }
        });
	}

}
