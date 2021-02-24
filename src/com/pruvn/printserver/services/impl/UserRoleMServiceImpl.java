package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.UserRoleMDAO;
import com.pruvn.printserver.entity.UserRoleM;
import com.pruvn.printserver.services.UserRoleMService;

public class UserRoleMServiceImpl implements UserRoleMService{
		private UserRoleMDAO userRoleMDAO;
		@Autowired
		public UserRoleMDAO getUserRoleMDAO() {
			return userRoleMDAO;
		}

		public void setUserRoleMDAO(UserRoleMDAO userRoleMDAO) {
			this.userRoleMDAO = userRoleMDAO;
		}

		@Override
		public List<UserRoleM> getRole(Long code) {
			return userRoleMDAO.getRole(code);
		}

		
		
		
		
		

}
