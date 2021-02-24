package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.CSRecordPendingDAO;
import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.domain.CSRecordWaiting;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class CSRecordPendingHibernateDAO extends
		AbstractHibernateDAO<CSRecordPending, Integer> implements
		CSRecordPendingDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CSRecordPending> findAll_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<CSRecordPending>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_PENDING_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage); 
        		
        		query.addEntity(CSRecordPending.class);
        		List<CSRecordPending> result = query.list();
        		return result;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CSRecordPending getCSRecordPendingByIdAndCusName(final String loanID,
			final String customerName){
		return (CSRecordPending) getHibernateTemplate().execute(new HibernateCallback() {
	        public Object doInHibernate(final Session session) throws HibernateException, SQLException {	    		
	    		SQLQuery query =session.createSQLQuery("select w.* from CS_RECORD_PENDING w WHERE w.AGREEMENTNO like :loanId " +
	    				" AND REPLACE(upper(w.CUSTOMERNAME),' ','') = REPLACE(upper(:customerName),' ','') ");	    		
	    		query.setParameter("loanId", "%" + loanID);
	         	query.setParameter("customerName", customerName);
	        	query.addEntity(CSRecordPending.class);
	        	List<CSRecordPending> users = query.list(); 
	    		if (users != null && users.size() > 0) {
	    			return users.get(0);
	    		}
	    		return null;
	        }
	    });
	}
}
