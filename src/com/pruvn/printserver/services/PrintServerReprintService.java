package com.pruvn.printserver.services;

import java.util.List;

import com.pruvn.printserver.entity.PrtReprintLogM;
import com.pruvn.printserver.entity.PrtReprintM;


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
