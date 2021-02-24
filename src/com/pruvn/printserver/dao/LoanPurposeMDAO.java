package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.LoanPurposeM;


public interface LoanPurposeMDAO extends GenericDAO<LoanPurposeM,String>{

	List<LoanPurposeM> findByPurposeCode(String purpose);


}
