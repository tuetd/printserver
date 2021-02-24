package com.pruvn.rms.service;

import com.pruvn.rms.domain.finnbank.Cm_doc_printed_trial;
import com.pruvn.rms.service.abstracts.GenericService;


public interface Cm_doc_printed_trialService  extends GenericService<Cm_doc_printed_trial, Integer>{
	Boolean checkCM(String loanNo);
}
