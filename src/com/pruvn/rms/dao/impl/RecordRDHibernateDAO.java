package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordRDDAO;
import com.pruvn.rms.domain.RecordPending;
import com.pruvn.rms.domain.RecordReady;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordRDHibernateDAO extends
		AbstractHibernateDAO<RecordReady, Integer> implements
		RecordRDDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordReady> findAllRecordRDs_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_RECORD_READY_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordReady.class);
        		List<RecordReady> result = query.list();
        		return result;
            }
        });
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RecordReady getRecordRDByLoanId(final String loanId) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		SQLQuery query =session.createSQLQuery("select po.* from RM_RECORD_READY po WHERE po.AGREEMENTNO = :loanId");
             	query.setParameter("loanId",loanId);
            	query.addEntity(RecordReady.class);
            	List<RecordReady> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			return users.get(0);
        		}
        		return null;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RecordPending getRecordPendingByLoanId(final String loanId) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		SQLQuery query =session.createSQLQuery("select po.* from RM_RECORD_PENDING po WHERE po.AGREEMENTNO = :loanId");
             	query.setParameter("loanId",loanId);
            	query.addEntity(RecordPending.class);
            	List<RecordPending> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			return users.get(0);
        		}
        		return null;
            }
        });
	}
}
