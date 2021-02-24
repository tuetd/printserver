package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.AutoDebit;
import com.pruvn.rms.domain.DocumentMaintenance;
import com.pruvn.rms.service.abstracts.GenericService;

public interface AutoDebitService  extends GenericService<AutoDebit, Integer> {
	
		
	List<AutoDebit> getAllRecords_AD(String username, String stage, Map<String, Object> filters);
	
	List<DocumentMaintenance> getAllDocument(String username, String stage, Map<String, Object> filters);
	
	int countAllRecord(String username, String stage, Map<String, Object> filters);
	public AutoDebit findById(Long id);
	List<AutoDebit> getlistBank();
	List<AutoDebit> getlistBranch();

}
