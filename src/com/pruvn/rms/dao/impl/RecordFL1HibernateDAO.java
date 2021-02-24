package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordFL1DAO;
import com.pruvn.rms.domain.RecordFL1;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordFL1HibernateDAO extends
		AbstractHibernateDAO<RecordFL1, Integer> implements
		RecordFL1DAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordFL1> findAllToRMT_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordFL1.class);
        		List<RecordFL1> result = query.list();
        		return result;
            }
        });
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RecordFL1 getRecordFL1ByLoanId(final String loanId) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		SQLQuery query =session.createSQLQuery("select po.* from RM_RECORD_FL1 po WHERE po.AGREEMENTNO = :loanId");
             	query.setParameter("loanId",loanId);
            	query.addEntity(RecordFL1.class);
            	List<RecordFL1> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			return users.get(0);
        		}
        		return null;
            }
        });
	}

}
