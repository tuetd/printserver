package com.pruvn.rms.service;

import java.util.List;
import java.util.Map;

import com.pruvn.rms.domain.RecordInBox;
import com.pruvn.rms.domain.RecordScanCM;
import com.pruvn.rms.domain.RecordScanCMCheck;

public interface RecordCMService extends BaseRecordService{
	
	RecordScanCM getRecordScanCMById(Integer id);
	
	List<RecordScanCM> getAllRecordScanCMs_ACL(String username, String state, Map<String, Object> filters);
	
	RecordScanCMCheck getRecordScanCMCheckById(Integer id);
	
	List<RecordScanCMCheck> getAllRecordScanCMChecks_ACL(String username, String state, Map<String, Object> filters);
	
	RecordInBox getRecordInBoxById(Integer id);
	
	List<RecordInBox> getAllRecordInBoxs_ACL(String username, String state, Map<String, Object> filters);
}
