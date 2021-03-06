/*
 * Java bean class for entity table user_level_a 
 * Created on 17 May 2013 ( Time 17:03:25 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.UserLevelADao;
import com.pruvn.rms.domain.UserLevelA;
import com.pruvn.rms.domain.UserLevelM;
import com.pruvn.rms.domain.UserM;


/**
 * Data Access Object implementation for table "user_level_a"
 * 
 * @author Telosys Tools Generator
 *
 */
public class UserLevelAHibernateDao extends AbstractHibernateDAO<UserLevelA,Integer>  implements UserLevelADao
{
    
	/**
	 * Find UserLevelA by userid
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserLevelA findByuserid(final UserM user) {
		//List<UserLevelA> ret = findByCriteria(Restrictions.eq("user", userid));
		return (UserLevelA) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("from UserLevelA u join fetch u.level Where u.user = :user");
        		query.setParameter("user", user);
        		List<UserLevelA> ret =  query.list();	
        		if (ret != null && ret.size() > 0) {
        			return ret.get(0);
        		}
        		return null;
            }
        });
		
		
	}
	
	/**
	 * Find UserLevelA by levelid
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserLevelA findBylevelid(final UserLevelM level) {
		//List<UserLevelA> ret = findByCriteria(Restrictions.eq("level", levelid));
		return (UserLevelA) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	Query query  = session.createQuery("from UserLevelA u join fetch u.user Where u.level = :level");
        		query.setParameter("level", level);
        		List<UserLevelA> ret =  query.list();
        		if (ret != null && ret.size() > 0) {
        			return ret.get(0);
        		}
        		return null;
            }
        });
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void deleteByUserId(final Integer userId) {
		getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	SQLQuery query =session.createSQLQuery("DELETE FROM USER_LEVEL_A WHERE USERID = " + userId);
        		query.executeUpdate();
        		return true;
            }
        });
		
	}
}