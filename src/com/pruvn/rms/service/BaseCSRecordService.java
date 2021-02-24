package com.pruvn.rms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pruvn.rms.domain.CSLogTransaction;

public interface BaseCSRecordService {
	String callInvolve(HttpServletRequest request, String action, String username, String stage, Integer recordId, String... values);
	
	List<CSLogTransaction> getCSLogTransactionsByCSRecordId(Integer recordId);
}
