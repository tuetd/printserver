package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.Foreclosure;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.service.response.UploadForeclosureResponse;


public interface ForeclosureService extends BaseRecordService {

	List<Foreclosure> getForeclosureList(String upload,
			FilterMRCForm filterMRCForm);

	UploadForeclosureResponse saveUploadForeclosure(List<Foreclosure> listForeclosure);

	//void receiveTB6(String id, String remark, String dateReturn);

	//void markWatingTB6(String id, String remark, String dateWaiting);

	//void markCompleteTB6(String id, String remark, String dateComplete);

	
	

}
