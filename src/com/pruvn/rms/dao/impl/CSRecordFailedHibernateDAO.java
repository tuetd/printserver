package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.CSRecordFailedDAO;
import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.CSRecordFailed;
import com.pruvn.rms.domain.CSRecordPending;
import com.pruvn.rms.utils.SqlConstant;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class CSRecordFailedHibernateDAO extends
		AbstractHibernateDAO<CSRecordFailed, Integer> implements
		CSRecordFailedDAO {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CSRecordFailed> findAll_ACL(final String username, final String stage, final Map<String, Object> filters) {
		return (List<CSRecordFailed>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
        		StringBuffer sqlQuery  = new StringBuffer(SqlConstant.SELECT_RMT_FAILED_LIST_ACL);
        		buildFilterQuery(filters, sqlQuery);
        		
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.setParameter("username", username);
        		query.setParameter("stage", stage); 
        		
        		query.addEntity(CSRecordFailed.class);
        		List<CSRecordFailed> result = query.list();
        		return result;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CSRecordFailed getCSRecordFailedByIdAndCusName(final String loanID,
			final String customerName){
		return (CSRecordFailed) getHibernateTemplate().execute(new HibernateCallback() {
	        public Object doInHibernate(final Session session) throws HibernateException, SQLException {
	    		SQLQuery query =session.createSQLQuery("select w.* from CS_RECORD_FAILED w WHERE w.AGREEMENTNO like :loanId " +
	    				" AND REPLACE(upper(w.CUSTOMERNAME),' ','') = REPLACE(upper(:customerName),' ','') ");
	    		query.setParameter("loanId", "%" + loanID);
	         	query.setParameter("customerName", customerName);
	        	query.addEntity(CSRecordFailed.class);
	        	List<CSRecordFailed> users = query.list(); 
	    		if (users != null && users.size() > 0) {
	    			return users.get(0);
	    		}
	    		return null;
	        }
	    });
	}

}
