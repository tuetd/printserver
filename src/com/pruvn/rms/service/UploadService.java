package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.HomeLoan;
import com.pruvn.rms.domain.Insurance;
import com.pruvn.rms.domain.MRC;
import com.pruvn.rms.model.FilterHomeLoanForm;
import com.pruvn.rms.model.FilterInsuranceForm;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.service.response.UploadInsuranceResponse;

public interface UploadService extends BaseRecordService {

	List<MRC> getMRCList(FilterMRCForm filterMRCForm);
	
	List<HomeLoan> getHomeLoanList(FilterHomeLoanForm filterHomeLoanForm);

	void saveUploadMRC(List<MRC> listMRC);

	void saveUploadHomeLoan(List<HomeLoan> listHomeLoan);

	void remarkMRC(String id, String remark, String dateReceive);

	void remarkHomeLoan(String id, String remark, String dateReceive);

	List<Insurance> getInsuranceList(FilterInsuranceForm filterInsuranceForm);

	void remarkInsurance(String id, String remark, String dateReceive);

	UploadInsuranceResponse saveUploadInsurance(List<Insurance> listInsurance);

//	String synchronizeData();
//
//	Record searchRecordByAgreementNo(String agreementNo);
//	
//	List<Screen> getListScreen(String stage, String system);
//	
//	List<Screen> getListScreen2(String stage, String system);
//	
//	Screen getScreen(String stage);
	
	
}
