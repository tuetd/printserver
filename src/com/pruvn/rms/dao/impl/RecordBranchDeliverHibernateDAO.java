package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordBranchDeliverDAO;
import com.pruvn.rms.domain.RecordBranchDeliver;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for RecordBranchDeliver</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordBranchDeliverHibernateDAO extends
		AbstractHibernateDAO<RecordBranchDeliver, Integer> implements
		RecordBranchDeliverDAO {

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordBranchDeliver> findAllRecordBranchDeliver_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_RECORD_BRANCH_DELIVER_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordBranchDeliver.class);
        		List<RecordBranchDeliver> result = query.list();
        		return result;
            }
        });
		
		
	}

}
