package com.pruvn.printserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.UsermasterLogDAO;
import com.pruvn.printserver.entity.UsermasterLog;
import com.pruvn.printserver.services.UsermasterLogService;

public class UsermasterLogServiceImpl implements UsermasterLogService{
		@Autowired	
		private UsermasterLogDAO usermasterLogDAO;

		public UsermasterLogDAO getUsermasterLogDAO() {
			return usermasterLogDAO;
		}

		public void setUsermasterLogDAO(UsermasterLogDAO usermasterLogDAO) {
			this.usermasterLogDAO = usermasterLogDAO;
		}

		@Override
		public void save(UsermasterLog userlog) {
			usermasterLogDAO.save(userlog);
			
		}
		

}
