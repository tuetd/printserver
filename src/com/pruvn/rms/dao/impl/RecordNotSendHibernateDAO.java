package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.RecordNotSendDAO;
import com.pruvn.rms.domain.RecordNotSend;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for RecordNotSend</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class RecordNotSendHibernateDAO extends
		AbstractHibernateDAO<RecordNotSend, Integer> implements
		RecordNotSendDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RecordNotSend> findAllRecordNotSends_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<RecordNotSend>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_RECORD_NOT_SEND_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage);
        		query.addEntity(RecordNotSend.class);
        		List<RecordNotSend> result = query.list();
        		return result;
            }
        });
	}

}
