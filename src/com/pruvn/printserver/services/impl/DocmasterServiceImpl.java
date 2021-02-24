package com.pruvn.printserver.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.DocmasterDAO;
import com.pruvn.printserver.entity.Docmaster;
import com.pruvn.printserver.services.DocmasterService;

public class DocmasterServiceImpl implements DocmasterService{
		private DocmasterDAO docmasterDAO;
		
		@Autowired
		public DocmasterDAO getDocmasterDAO() {
			return docmasterDAO;
		}
		
		public void setDocmasterDAO(DocmasterDAO docmasterDAO) {
			this.docmasterDAO = docmasterDAO;
		}

		@Override
		public Docmaster getById(Long docid) {
			// TODO Auto-generated method stub
			return docmasterDAO.getById(docid);
		}

		@Override
		public List<Docmaster> findByNameDocmaster(String documentname) {
			return docmasterDAO.findByNameDocmaster(documentname);
		}

		@Override
		public int checkVersionFCL(String agreementno, Date startIntDate,
				int instlnum, Date duedate, String check_CREDIT_SHEILD) {
			// TODO Auto-generated method stub
			return docmasterDAO.checkVersionFCL(agreementno,startIntDate,instlnum,duedate,check_CREDIT_SHEILD);
		}

		@Override
		public String printCheckFCL(String agreementno, String fcldate) {
			return docmasterDAO.printCheckFCL(agreementno,fcldate);
		}

		@Override
		public String printFCLPercent(String agreementno,String fcl) {
			// TODO Auto-generated method stub
			return docmasterDAO.printFCLPercent(agreementno,fcl);
		}

		@Override
		public void saveNoSignatureWithCEO(String loanno, String name,
				String name_doc, String singture) {
			docmasterDAO.saveNoSignatureWithCEO(loanno,name,name_doc,singture);
			
		}

		@Override
		public String printAllow(String agreementno) {
			return docmasterDAO.printAllow(agreementno);
		}

	

}
