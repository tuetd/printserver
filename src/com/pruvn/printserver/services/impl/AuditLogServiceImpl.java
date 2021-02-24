package com.pruvn.printserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.AuditLogDAO;
import com.pruvn.printserver.entity.AuditLog;
import com.pruvn.printserver.services.AuditLogService;

public class AuditLogServiceImpl implements AuditLogService{
		@Autowired	
		private AuditLogDAO auditLogDAO;
		
		
		public AuditLogDAO getAuditLogDAO() {
			return auditLogDAO;
		}

		public void setAuditLogDAO(AuditLogDAO auditLogDAO) {
			this.auditLogDAO = auditLogDAO;
		}

		@Override
		public void saveAuditLog(AuditLog auditlog) {
			auditLogDAO.save(auditlog);
			
		}

		

}
