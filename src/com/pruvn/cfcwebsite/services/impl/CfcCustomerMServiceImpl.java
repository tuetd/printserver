package com.pruvn.cfcwebsite.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.cfcwebsite.dao.CfcCustomerMDAO;
import com.pruvn.cfcwebsite.entity.CfcCustomerM;
import com.pruvn.cfcwebsite.services.CfcCustomerMService;
import com.pruvn.printserver.form.FCLForm;
import com.pruvn.printserver.services.GeneratePasswordService;

public class CfcCustomerMServiceImpl implements CfcCustomerMService{
		@Autowired
		private CfcCustomerMDAO cfcCustomerMDAO;

		public CfcCustomerMDAO getCfcCustomerMDAO() {
			return cfcCustomerMDAO;
		}

		public void setCfcCustomerMDAO(CfcCustomerMDAO cfcCustomerMDAO) {
			this.cfcCustomerMDAO = cfcCustomerMDAO;
		}

		@Override
		public CfcCustomerM findByApplid(String string) {
			List<CfcCustomerM> lst=cfcCustomerMDAO.findByApplid(string);
			if(lst.isEmpty()){
				return null;
			}
			return lst.get(0);
		}

		@Override
		public int generatePasswordDefault(String loanno,String app_id,String password_encode,String pass_text) {
			return cfcCustomerMDAO.generatePasswordDefault(loanno,app_id,password_encode,pass_text);
			
		}

		@Override
		public FCLForm getInstartDate(String loanno) {
			return cfcCustomerMDAO.getInstartDate(loanno);
		}

		
		
		
		

}
