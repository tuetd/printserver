package com.pruvn.printserver.services;

import java.util.Date;
import java.util.List;

import com.pruvn.printserver.entity.Docmaster;


public interface DocmasterService {

	Docmaster getById(Long docid);
	List<Docmaster> findByNameDocmaster(String documentname);
	int checkVersionFCL(String agreementno, Date startIntDate, int instlnum,
			Date duedate, String check_CREDIT_SHEILD);
	String printCheckFCL(String string, String string2);
	String printFCLPercent(String agreementno,String fcl);
	void saveNoSignatureWithCEO(String loanno, String name, String name_doc,
			String string);
	String printAllow(String string);

}
