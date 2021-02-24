package com.pruvn.printserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.SignatureDAO;
import com.pruvn.printserver.entity.Signature;
import com.pruvn.printserver.services.SignatureService;

public class SignatureServiceImpl implements SignatureService{
		private SignatureDAO signatureDAO;

		public SignatureDAO getSignatureDAO() {
			return signatureDAO;
		}
		@Autowired
		public void setSignatureDAO(SignatureDAO signatureDAO) {
			this.signatureDAO = signatureDAO;
		}
		@Override
		public Signature findByid(Long id) {
			return signatureDAO.findById(id);
		}
		
	

		


	

}
