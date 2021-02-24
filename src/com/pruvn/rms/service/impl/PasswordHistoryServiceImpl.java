package com.pruvn.rms.service.impl;

import com.pruvn.rms.dao.PasswordHistoryDAO;
import com.pruvn.rms.domain.PasswordHistory;
import com.pruvn.rms.service.PasswordHistoryService;

public class PasswordHistoryServiceImpl implements PasswordHistoryService {
	
	private PasswordHistoryDAO passwordHistoryDAO;

	public PasswordHistoryDAO getPasswordHistoryDAO() {
		return passwordHistoryDAO;
	}

	public void setPasswordHistoryDAO(PasswordHistoryDAO passwordHistoryDAO) {
		this.passwordHistoryDAO = passwordHistoryDAO;
	}

	@Override
	public boolean isExitInHistory(String userName, String newPwd) {
		return passwordHistoryDAO.isExitInHistory(userName, newPwd);
	}
	
	@Override
	public void storeHistory(PasswordHistory passwordHistory) {
		 passwordHistoryDAO.storeHistory(passwordHistory);
		 
	}


}
