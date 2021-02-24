package com.pruvn.printserver.services.impl;

import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.UsermasterDAO;
import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.services.UsermasterService;

public class UsermasterServiceImpl implements UsermasterService{
		private UsermasterDAO usermasterDAO;
		@Autowired
		public UsermasterDAO getUsermasterDAO() {
			return usermasterDAO;
		}

		public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
			this.usermasterDAO = usermasterDAO;
		}
		
		
		
		@Override
		public Usermaster getUserByUserName(String username) throws UserException {
			List<Usermaster> users = usermasterDAO.findByUsername(username);
			if (users != null && users.size() > 0) {
				return users.get(0);
			}
			
			return null;
		}

		@Override
		public List<Usermaster> findAll() {
			return usermasterDAO.findAll();
		}

		@Override
		public List<Usermaster> findByUsername(String username) {
			return usermasterDAO.findByUsername(username);
		}

		@Override
		public Usermaster findById(Long pkFromRequest) {
			return usermasterDAO.findById(pkFromRequest);
		}

		@Override
		public void update(Usermaster create) {
			usermasterDAO.update(create);
		}

		@Override
		public void save(Usermaster create) {
			usermasterDAO.save(create);
			
		}

		@Override
		public void saveOrUpdate(Usermaster user) {
			usermasterDAO.update(user);
		}

}
