package com.pruvn.printserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.UserplaceDAO;
import com.pruvn.printserver.services.UserplaceService;

public class UserplaceServiceImpl implements UserplaceService{
		private UserplaceDAO userplaceDAO;
		@Autowired
		public UserplaceDAO getUserplaceDAO() {
			return userplaceDAO;
		}

		public void setUserplaceDAO(UserplaceDAO userplaceDAO) {
			this.userplaceDAO = userplaceDAO;
		}
		
		
		
		
		

}
