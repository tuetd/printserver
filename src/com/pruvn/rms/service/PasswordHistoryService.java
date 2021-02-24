package com.pruvn.rms.service;

import com.pruvn.rms.domain.PasswordHistory;

public interface PasswordHistoryService {

	boolean isExitInHistory(String userName, String newPwd);
	
	void storeHistory(PasswordHistory passwordHistory);
	
}
