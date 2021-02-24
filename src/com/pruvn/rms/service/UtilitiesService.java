package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.CSRecord;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.Screen;

public interface UtilitiesService extends BaseRecordService {

	String synchronizeData();

	Record searchRecordByAgreementNo(String agreementNo);
	
	List<Screen> getListScreen(String stage, String system);
	
	List<Screen> getListScreen2(String stage, String system);
	
	Screen getScreen(String stage);

	List<Record> getAllRecords(Map<String, Object> filters);

	List<Record> getAllRecordsCreditShield(Map<String, Object> filters);
	
	Record getRecordById(Integer id);
	
	CSRecord searchCSByAgreementNo(String agreementNo);
	
	String getRefNoSeq();
	
}
 