package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.UserDocPrinterDAO;
import com.pruvn.printserver.entity.UserDocPrinter;
import com.pruvn.printserver.services.UserDocPrinterService;

public class UserDocPrinterServiceImpl implements UserDocPrinterService{
		private UserDocPrinterDAO userDocPrinterDAO;
		@Autowired
		public UserDocPrinterDAO getUserDocPrinterDAO() {
			return userDocPrinterDAO;
		}

		public void setUserDocPrinterDAO(UserDocPrinterDAO userDocPrinterDAO) {
			this.userDocPrinterDAO = userDocPrinterDAO;
		}

		@Override
		public List<UserDocPrinter> findByUserid(Long id) {
			// TODO Auto-generated method stub
			return userDocPrinterDAO.findByUserid(id);
		}

		@Override
		public List<UserDocPrinter> findByUseridAnDocid(Long userid, Long docid) {
			return userDocPrinterDAO.findByUseridAnDocid(userid,docid);
		}
		

}
