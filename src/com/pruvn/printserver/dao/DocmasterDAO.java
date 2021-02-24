package com.pruvn.printserver.dao;

import java.util.Date;
import java.util.List;

import com.pruvn.printserver.entity.Docmaster;


public interface DocmasterDAO extends GenericDAO<Docmaster,Long>{

	Docmaster getById(Long docid);
	List<Docmaster> findByNameDocmaster(String documentname);
	int checkVersionFCL(String agreementno, Date startIntDate, int instlnum,
			Date duedate, String check_CREDIT_SHEILD);
	String printCheckFCL(String agreementno, String fcldate);
	String printFCLPercent(String agreementno,String fcl);
	void saveNoSignatureWithCEO(String loanno, String name, String name_doc,
			String singture);
	String printAllow(String agreementno);

}
