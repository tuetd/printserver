package com.pruvn.printserver.services;

import com.pruvn.printserver.entity.GeneratePassword;



public interface GeneratePasswordService {

	GeneratePassword findByApplid(String string);
	int generatePasswordDefault(String loanno);

}
