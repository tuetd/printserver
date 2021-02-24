package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.LoanPurposeMDAO;
import com.pruvn.printserver.entity.LoanPurposeM;
import com.pruvn.printserver.services.LoanPurposeMService;

public class LoanPurposeMServiceImpl implements LoanPurposeMService{
		private LoanPurposeMDAO loanPurposeMDAO;
		@Autowired
		public LoanPurposeMDAO getLoanPurposeMDAO() {
			return loanPurposeMDAO;
		}

		public void setLoanPurposeMDAO(LoanPurposeMDAO loanPurposeMDAO) {
			this.loanPurposeMDAO = loanPurposeMDAO;
		}

		@Override
		public LoanPurposeM findByPurposeCode(String purpose) {
			List<LoanPurposeM> lst=loanPurposeMDAO.findByPurposeCode(purpose); 
			if(!lst.isEmpty()){
				return lst.get(0);
			}
			return null;
		}
		
		

		
		
		
		
		

}
