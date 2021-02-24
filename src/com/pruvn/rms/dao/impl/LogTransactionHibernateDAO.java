package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.LogTransactionDAO;
import com.pruvn.rms.domain.LogTransaction;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class LogTransactionHibernateDAO extends
		AbstractHibernateDAO<LogTransaction, Integer> implements
		LogTransactionDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<LogTransaction> getByRecordId(final Integer recordId) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("FROM LogTransaction WHERE recordId = :recordId");
        		query.setParameter("recordId", recordId);
        		return query.list();
            }
        });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<LogTransaction> getLogTransactions(final Integer recordId, final String logType){
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("FROM LogTransaction WHERE recordId = :recordId AND (logType is null or logType =:logType)");
        		query.setParameter("recordId", recordId);
        		query.setParameter("logType", logType);
        		return query.list();
            }
        });
	}
}
