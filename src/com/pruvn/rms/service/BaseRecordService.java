package com.pruvn.rms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pruvn.rms.domain.LogTransaction;
import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.UserLog;

public interface BaseRecordService {
	String callInvolve(HttpServletRequest request, String action, String username, String stage, Integer recordId, String... values);
	
	List<LogTransaction> getLogTransactionsByRecordId(Integer recordId);
	
	List<LogTransaction> getLogTransactions(Integer recordId, String logType);
	
	String callAction(HttpServletRequest request, String stage, String action, String id, String... values);
	
	void saveUserLog(UserLog userlog);
	Record getRecordByLoanId(String loanId);
	
}
