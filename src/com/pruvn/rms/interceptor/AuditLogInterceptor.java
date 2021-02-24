package com.pruvn.rms.interceptor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pruvn.rms.dao.AuditLogDAO;
import com.pruvn.rms.domain.AuditLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;



@SuppressWarnings("serial")
public class AuditLogInterceptor extends EmptyInterceptor {
	private AuditLogDAO auditLogDAO;
	private UserMService userMService;
	private static final Logger logger=Logger.getLogger(AuditLogInterceptor.class);  
	@SuppressWarnings("rawtypes")
	private Set inserts = new HashSet();
	@SuppressWarnings("rawtypes")
	private Set updates = new HashSet();
	@SuppressWarnings("rawtypes")
	private Set deletes = new HashSet();
	
	
	@SuppressWarnings("unchecked")
	public boolean onSave(Object entity,Serializable id,
			Object[] state,String[] propertyNames,Type[] types)
			throws CallbackException {
		logger.info("onSave");
			if (entity instanceof IAuditLog){
				inserts.add(entity);
			}
			return false;
	 
		}
	@SuppressWarnings("unchecked")
		public boolean onFlushDirty(Object entity,Serializable id,
			Object[] currentState,Object[] previousState,
			String[] propertyNames,Type[] types)
			throws CallbackException {
	 
		logger.info("onFlushDirty");
	 
			if (entity instanceof IAuditLog){
				updates.add(entity);
			}
			return false;
	 
		}
	@SuppressWarnings("unchecked")
		public void onDelete(Object entity, Serializable id, 
			Object[] state, String[] propertyNames, 
			Type[] types) {
	 
		logger.info("onDelete");
	 
			if (entity instanceof IAuditLog){
				deletes.add(entity);
			}
		}
		//called before commit into database
		@SuppressWarnings("rawtypes")
		public void preFlush(Iterator iterator) {
			logger.info("preFlush");
		}	
		@SuppressWarnings("rawtypes")
		//called after committed into database
		public void postFlush(Iterator iterator) {
			logger.info("postFlush");
			try {
			for (Iterator it = inserts.iterator(); it.hasNext();) {
			    IAuditLog entity = (IAuditLog) it.next();
			    logger.info("postFlush - insert");		
			    LogIt("Saved",entity);
			}	
	 
			for (Iterator it = updates.iterator(); it.hasNext();) {
			    IAuditLog entity = (IAuditLog) it.next();
			    logger.info("postFlush - update");
			    LogIt("Updated",entity);
			}	
	 
			for (Iterator it = deletes.iterator(); it.hasNext();) {
			    IAuditLog entity = (IAuditLog) it.next();
			    logger.info("postFlush - delete");
			    LogIt("Deleted",entity);
			}	
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	       }	
	
	
	public  void LogIt(String action,
		     IAuditLog entity){
		try {
			if(SecurityContextHolder.getContext().getAuthentication()!=null){
			String username	= SecurityContextHolder.getContext().getAuthentication().getName();
			if(!username.equalsIgnoreCase("anonymoususer")){
				UserM userlogin = userMService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
				AuditLog auditRecord = new AuditLog(action,userlogin.getUsername() +" - "+userlogin.getFullname()+ " - "+ entity.getLogDeatil()
					, new Date(),entity.getIdAuditLog(), entity.getClass().toString());
				auditLogDAO.save(auditRecord);
			}
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		  }

	public AuditLogDAO getAuditLogDAO() {
		return auditLogDAO;
	}
	@Autowired
	public void setAuditLogDAO(AuditLogDAO auditLogDAO) {
		this.auditLogDAO = auditLogDAO;
	}
	public UserMService getUserMService() {
		return userMService;
	}
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}
	
	
}
