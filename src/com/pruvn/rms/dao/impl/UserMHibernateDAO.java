package com.pruvn.rms.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.UserMDAO;
import com.pruvn.rms.domain.UserM;

/**
 * <p>Hibernate DAO layer for UserMs</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserMHibernateDAO extends
		AbstractHibernateDAO<UserM, Integer> implements
		UserMDAO {

	/**
	 * Find UserM by username
	 */
	public List<UserM> findByUsername(String username) {
		return findByCriteria(Restrictions.eq("username", username));
	}
	
	/**
	 * Find UserM by loggedin
	 */
	public List<UserM> findByLoggedin(String loggedin) {
		return findByCriteria(Restrictions.eq("loggedin", loggedin));
	}
	
	/**
	 * Find UserM by sessionId
	 */
	public List<UserM> findBySessionId(String sessionId) {
		return findByCriteria(Restrictions.eq("sessionId", sessionId));
	}
	
	/**
	 * Find UserM by finnoneSecurityCode
	 */
	public List<UserM> findByFinnoneSecurityCode(String finnoneSecurityCode) {
		return findByCriteria(Restrictions.eq("finnoneSecurityCode", finnoneSecurityCode));
	}
	
	/**
	 * Find UserM by emailCode
	 */
	public List<UserM> findByEmailCode(String emailCode) {
		return findByCriteria(Restrictions.eq("emailCode", emailCode));
	}
	
	/**
	 * Find UserM by emailCode
	 */
	public List<UserM> findUser(String emailCode, String username) {
		return findByCriteria(Restrictions.and(Restrictions.eq("emailCode", emailCode), Restrictions.eq("username", username)));
	}
	
	/**
	 * Find UserM by password
	 */
	public List<UserM> findByPassword(String password) {
		return findByCriteria(Restrictions.eq("password", password));
	}
	
	/**
	 * Find UserM by lastChangedPw
	 */
	public List<UserM> findByLastChangedPw(Timestamp lastChangedPw) {
		return findByCriteria(Restrictions.eq("lastChangedPw", lastChangedPw));
	}
	
	/**
	 * Find UserM by userPlace
	 */
	public List<UserM> findByUserPlace(String userPlace) {
		return findByCriteria(Restrictions.eq("userPlace", userPlace));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserM> searchUserByUserName(final String username) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Criteria crit = session.createCriteria(getPersistentClass());
            	
            	crit.add(Restrictions.ilike("username", username, MatchMode.ANYWHERE));
                return crit.list();
            }
        });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UserM> findUserByDeptCodeAndLevelCode(final String deptCode,
			final String levelCode) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Query query = session.createQuery("select a.user from UserDeptA a, UserLevelA b where a.user = b.user and a.user.isActived != 0 and a.dept.deptcode = :deptCode and b.level.levelcode = :levelCode");
            	query.setParameter("deptCode", deptCode);
            	query.setParameter("levelCode", levelCode);
                return query.list();
            }
        });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> findByFullName(final String username,final String groupcode) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	List<String> lstUser=new ArrayList<String>();
        		SQLQuery query = session.createSQLQuery("select users.* from user_m users,(SELECT u.* FROM user_group_a u,group_m role where role.groupid=u.groupid and role.groupcode= :groupcode) userrole where users.id=userrole.userid and users.full_name like :fullname");
             	query.setParameter("fullname", "%"+username+"%");
             	query.setParameter("groupcode", groupcode);
            	query.addEntity(UserM.class);
            	List<UserM> users = query.list(); 
        		if (users != null && users.size() > 0) {
        			for (UserM user : users) {
        				lstUser.add(user.getUsername()+"-"+user.getFullname());
        			}
        		}
        		return lstUser;
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UserM> findByRole(final String type) {
		return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	Query query = session.createQuery("select a.user from UserGroupA a where a.group.groupcode =  :type");
            	query.setParameter("type", type);
                return query.list();
            }
        });
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void deactive(final String userId){
		getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	SQLQuery query = session.createSQLQuery("UPDATE user_m SET IS_ACTIVED = 0 WHERE ID = " + userId);
        		query.executeUpdate();
        		return true;
            }
        });
		
	}

}
