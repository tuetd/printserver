package com.pruvn.tools.common.hibernate.finnone.service;

import java.util.List;

import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintLogM;
import com.pruvn.tools.common.hibernate.finnone.printsrv.PrtReprintM;

public interface PrintServerReprintService {
	List<PrtReprintM> listAllReprint();
	void saveOrUpdate(PrtReprintM entity);
	PrtReprintM findPrtReprintById(Integer id);
	void deleteReprint(PrtReprintM prtReprint);
	List<PrtReprintM> findPrtReprintByApplid(String applid);
	
	List<PrtReprintLogM> searchReprintLogByApplid(String applid);
	List<PrtReprintLogM> searchReprintLogByNotes(String notes);
	List<PrtReprintLogM> listAllReprintLog();
}
