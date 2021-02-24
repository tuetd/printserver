package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.GeneratePassword;


public interface GeneratePasswordDAO extends GenericDAO<GeneratePassword,String>{

	List<GeneratePassword> findByApplid(String string);

	int generatePasswordDefault(String loanno);


}
