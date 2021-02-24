package com.pruvn.tools.printserver.hibernate;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.utils.ConstantApp;
import com.pruvn.tools.utils.CustomJob;
import com.pruvn.tools.utils.ParameterApplication;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * <p>Hibernate DAO layer for Usermasters</p>
 * <p>Generated at Thu Jul 19 10:40:45 ICT 2012</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class UsermasterHibernateDAO extends
		AbstractHibernateDAO<Usermaster, Integer> implements
		UsermasterDAO {
	protected static Logger logger = Logger.getLogger(UsermasterHibernateDAO.class);
	/**
	 * Find Usermaster by username
	 */
	public Usermaster findByUsername(String username) {
		List<Usermaster> users = findByCriteria(Restrictions.eq("username", username));
		
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	/**
	 * Find Usermaster by loggedin
	 */
	public List<Usermaster> findByLoggedin(String loggedin) {
		return findByCriteria(Restrictions.eq("loggedin", loggedin));
	}
	
	/**
	 * Find Usermaster by roleId
	 */
	public List<Usermaster> findByRoleId(Integer roleId) {
		return findByCriteria(Restrictions.eq("roleId", roleId));
	}
	
	/**
	 * Find Usermaster by sessionId
	 */
	public List<Usermaster> findBySessionId(String sessionId) {
		return findByCriteria(Restrictions.eq("sessionId", sessionId));
	}
	
	/**
	 * Find Usermaster by finnoneSecurityCode
	 */
	public List<Usermaster> findByFinnoneSecurityCode(String finnoneSecurityCode) {
		return findByCriteria(Restrictions.eq("finnoneSecurityCode", finnoneSecurityCode));
	}
	
	/**
	 * Find Usermaster by emailCode
	 */
	public List<Usermaster> findByEmailCode(String emailCode) {
		return findByCriteria(Restrictions.eq("emailCode", emailCode));
	}
	
	/**
	 * Find Usermaster by password
	 */
	public List<Usermaster> findByPassword(String password) {
		return findByCriteria(Restrictions.eq("password", password));
	}
	
	/**
	 * Find Usermaster by lastChangedPw
	 */
	public List<Usermaster> findByLastChangedPw(Timestamp lastChangedPw) {
		return findByCriteria(Restrictions.eq("lastChangedPw", lastChangedPw));
	}
	
	/**
	 * Find Usermaster by userPlace
	 */
	public List<Usermaster> findByUserPlace(String userPlace) {
		return findByCriteria(Restrictions.eq("userPlace", userPlace));
	}

	@SuppressWarnings("unchecked")
	public List<Usermaster> findByReport(Date todate, Date fromdate,
			Integer status) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usermaster.class); 
		if(status!=null){
			criteria.add(Restrictions.eq("status", status));
		}
		if(fromdate!=null){
			criteria.add(Restrictions.sqlRestriction("lastlogindate >= ? ", fromdate,Hibernate.DATE));
		}
		if(todate!=null){
			criteria.add(Restrictions.sqlRestriction("lastlogindate <= ? ", todate,Hibernate.DATE));
		}
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	@SuppressWarnings("unchecked")
	public List<Usermaster> findByReportPagination(int page, int pageSize,
			Date todate, Date fromdate, Integer status) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usermaster.class); 
		int firstResult=-1;
		int naxResults=-1;
		if(status!=null){
			criteria.add(Restrictions.eq("status", status));
		}
		if(fromdate!=null){
			criteria.add(Restrictions.sqlRestriction("lastlogindate >= ? ", fromdate,Hibernate.DATE));
		}
		if(todate!=null){
			criteria.add(Restrictions.sqlRestriction("lastlogindate <= ? ", todate,Hibernate.DATE));
		}
		if(page > 0 && pageSize > 0){
			firstResult = (page-1)*pageSize;
			naxResults=pageSize;
        }
		
		return getHibernateTemplate().findByCriteria(criteria,firstResult,naxResults);
	}

	public void excuteJobLockUser() {
		String hqlUpdate = "update usermaster u set u.status= :status,u.date_login_temp = :datelogintemp,u.count_login_temp = :countlogintemp,u.reason_lock = :reasonlock  where u.status !=:status and u.lastlogindate <= date_sub(now(),interval 3 month)";
		SQLQuery query  = getSession().createSQLQuery(hqlUpdate);
		query.setParameter("status",ParameterApplication.BLOCK.getStatus());
		query.setParameter("datelogintemp", null);
		query.setParameter("countlogintemp", null);
		query.setParameter("reasonlock",ConstantApp.REASON_LOCK.TIME_EXPIRED.getName());
		int count = query.executeUpdate();
		logger.info("count"+count);
		getSession().close();
		     
	}
	
	
	
	

}
