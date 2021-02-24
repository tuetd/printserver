package com.pruvn.rms.service.impl;

import org.springframework.stereotype.Service;

import com.pruvn.rms.dao.Cm_doc_printed_trialDAO;
import com.pruvn.rms.domain.finnbank.Cm_doc_printed_trial;
import com.pruvn.rms.service.Cm_doc_printed_trialService;
import com.pruvn.rms.service.abstracts.GenericServiceImpl;

@Service("cm_doc_printed_trialService")
public class Cm_doc_printed_trialServiceImpl extends GenericServiceImpl<Cm_doc_printed_trialDAO, Cm_doc_printed_trial, Integer> implements Cm_doc_printed_trialService {
	private Cm_doc_printed_trialDAO cm_doc_printed_trialDAO;
	
	
	
	public Cm_doc_printed_trialDAO getCm_doc_printed_trialDAO() {
		return cm_doc_printed_trialDAO;
	}



	public void setCm_doc_printed_trialDAO(
			Cm_doc_printed_trialDAO cm_doc_printed_trialDAO) {
		this.cm_doc_printed_trialDAO = cm_doc_printed_trialDAO;
	}



	@Override
	public Boolean checkCM(String loanNo) {
		return cm_doc_printed_trialDAO.findByLoanNo(loanNo);		
	}


}
