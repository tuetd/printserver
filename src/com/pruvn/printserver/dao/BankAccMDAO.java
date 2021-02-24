package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.BankAccM;


public interface BankAccMDAO extends GenericDAO<BankAccM,Long>{

	List<BankAccM> findByBankCode(String bank_CODE);

}
