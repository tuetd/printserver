package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.BankAccMDAO;
import com.pruvn.printserver.entity.BankAccM;
import com.pruvn.printserver.services.BankAccMService;

public class BankAccMServiceImpl implements BankAccMService{
		private BankAccMDAO bankAccMDAO;
		@Autowired
		public BankAccMDAO getBankAccMDAO() {
			return bankAccMDAO;
		}

		public void setBankAccMDAO(BankAccMDAO bankAccMDAO) {
			this.bankAccMDAO = bankAccMDAO;
		}

		@Override
		public BankAccM findByBankCode(String bank_CODE) {
			List<BankAccM> lst=bankAccMDAO.findByBankCode(bank_CODE);
			if(!lst.isEmpty()){
				return lst.get(0);
			}
			return  null;
		}
		

		
		
		
		
		

}
