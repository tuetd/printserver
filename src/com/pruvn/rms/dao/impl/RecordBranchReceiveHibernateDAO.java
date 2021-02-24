package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordBranchReceiveDAO;
import com.pruvn.rms.domain.RecordBranchReceive;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for RecordBranchReceive</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordBranchReceiveHibernateDAO extends
		AbstractHibernateDAO<RecordBranchReceive, Integer> implements
		RecordBranchReceiveDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordBranchReceive> findAllRecordBranchReceive_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<RecordBranchReceive>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_RECORD_BRANCH_RECEIVE_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordBranchReceive.class);
        		List<RecordBranchReceive> result = query.list();
        		return result;
            }
        });
	}
}
