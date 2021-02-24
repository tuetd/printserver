package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.TB6;
import com.pruvn.rms.model.FilterMRCForm;
import com.pruvn.rms.service.response.UploadTB6Response;


public interface TB6Service extends BaseRecordService {

	List<TB6> getTB6List(String upload,
			FilterMRCForm filterMRCForm);

	UploadTB6Response saveUploadTB6(List<TB6> listTB6);

	//void receiveTB6(String id, String remark, String dateReturn);

	//void markWatingTB6(String id, String remark, String dateWaiting);

	//void markCompleteTB6(String id, String remark, String dateComplete);

	
	

}
