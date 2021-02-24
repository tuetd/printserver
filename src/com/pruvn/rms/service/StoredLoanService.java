package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.StoredLoan;
import com.pruvn.rms.model.FilterStoredLoanForm;
import com.pruvn.rms.service.response.UploadStoredLoanResponse;


public interface StoredLoanService extends BaseRecordService {

	List<StoredLoan> getForeclosureList(FilterStoredLoanForm filterMRCForm);

	void saveUploadForeclosure(List<StoredLoan> listForeclosure);
	UploadStoredLoanResponse updateStoredLoan(List<StoredLoan> listForeclosure,String username);

	StoredLoan getStoredLoanById(Integer id);
	void updateStoredLoan(StoredLoan loan);
	//void receiveTB6(String id, String remark, String dateReturn);

	//void markWatingTB6(String id, String remark, String dateWaiting);

	//void markCompleteTB6(String id, String remark, String dateComplete);

	
	

}
