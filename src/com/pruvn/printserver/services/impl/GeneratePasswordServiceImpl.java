package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.GeneratePasswordDAO;
import com.pruvn.printserver.entity.GeneratePassword;
import com.pruvn.printserver.services.DepartmentService;
import com.pruvn.printserver.services.GeneratePasswordService;

public class GeneratePasswordServiceImpl implements GeneratePasswordService{
		@Autowired
		private GeneratePasswordDAO generatePasswordDAO;

		public GeneratePasswordDAO getGeneratePasswordDAO() {
			return generatePasswordDAO;
		}

		public void setGeneratePasswordDAO(GeneratePasswordDAO generatePasswordDAO) {
			this.generatePasswordDAO = generatePasswordDAO;
		}

		@Override
		public GeneratePassword findByApplid(String string) {
			List<GeneratePassword> lst=generatePasswordDAO.findByApplid(string);
			if(lst.isEmpty()){
				return null;
			}
			return lst.get(0);
		}

		@Override
		public int generatePasswordDefault(String loanno) {
			return generatePasswordDAO.generatePasswordDefault(loanno);
			
		}

		
		
		
		

}
