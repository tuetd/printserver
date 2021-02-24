package com.pruvn.rms.service.impl;

import com.pruvn.rms.dao.UserLogDAO;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.service.UserLogService;

public class UserLogServiceImpl implements UserLogService {
	
	private UserLogDAO userLogDAO;

	public UserLogDAO getUserLogDAO() {
		return userLogDAO;
	}

	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
	}

	@Override
	public void save(UserLog userlog) {
		 userLogDAO.save(userlog);
		
	}
	


}
