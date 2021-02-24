package com.pruvn.tools.common.hibernate.finnone.fa.hibernate;


import com.pruvn.tools.common.hibernate.finnone.fa.LOS_APP_APPLICATIONS_FA;
import com.pruvn.tools.printserver.GenericDAO;

public interface Los_App_Applications_DAO extends GenericDAO<LOS_APP_APPLICATIONS_FA,Integer>{
	 LOS_APP_APPLICATIONS_FA getApplicationByID(String app_id_c);
	 Object queryUnique(String[] columns, Object[] values, String[] operators, Class classObject);
}
