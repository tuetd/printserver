package com.pruvn.cfcwebsite.dao;

import java.util.List;

import com.pruvn.cfcwebsite.entity.CfcCustomerM;
import com.pruvn.printserver.form.FCLForm;


public interface CfcCustomerMDAO extends GenericDAO<CfcCustomerM,String>{

	List<CfcCustomerM> findByApplid(String string);

	int generatePasswordDefault(String loanno,String app_id,String password_encode,String pass_text);

	FCLForm getInstartDate(String loanno);


}
