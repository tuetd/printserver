package com.pruvn.rms.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

import com.pruvn.rms.dao.PasswordHistoryDAO;
import com.pruvn.rms.domain.PasswordHistory;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.SqlConstant;

public class PasswordHistoryHibernateDAO extends
		AbstractHibernateDAO<PasswordHistory, Integer> implements
		PasswordHistoryDAO {

	@Override
	public boolean isExitInHistory(String userName, String newPwd) {
		List<PasswordHistory> list =  findByCriteria(Restrictions.and(Restrictions.eq("userName", userName), Restrictions.eq("passwordCode", newPwd)));
		System.out.println(list.size());
		return list.size() > 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void storeHistory(final PasswordHistory passwordHistory) {
		// Clean up history
		try {
			save(passwordHistory);
			getHibernateTemplate().execute(new HibernateCallback() {
	            public Object doInHibernate(Session session) throws HibernateException, SQLException {
	            	session.doWork(new Work() {
	    				@Override
	    				public void execute(Connection arg0) throws SQLException {
	    					String sql = SqlConstant.CLEANUP_PASSWORD_HISTORY;
	    					CallableStatement cs;
	    					cs = arg0.prepareCall(sql);
	    					cs.setObject(1, passwordHistory.getUserName());
	    					cs.setObject(2, Constant.NUM_PASSWORD_HISTORY);
	    					cs.execute();
	    				}
	    			});
	            	return true;
	            }
	        });
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
}
