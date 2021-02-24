package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordFL2DAO;
import com.pruvn.rms.domain.RecordFL1;
import com.pruvn.rms.domain.RecordFL2;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordFL2HibernateDAO extends
		AbstractHibernateDAO<RecordFL2, Integer> implements
		RecordFL2DAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordFL2> findAllReceivedRMT_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<RecordFL2>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_REVEIVE_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordFL2.class);
        		List<RecordFL2> result = query.list();
        		return result;
            }
        });
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RecordFL2 getRecordFL2ByLoanId(final String loanId) {
		return (RecordFL2) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		SQLQuery query =session.createSQLQuery("select po.* from RM_RECORD_FL2 po WHERE po.AGREEMENTNO = :loanId");
             	query.setParameter("loanId",loanId);
            	query.addEntity(RecordFL2.class);
            	List<RecordFL2> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			return users.get(0);
        		}
        		return null;
            }
        });
	}
}
