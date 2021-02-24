package com.pruvn.printserver.services;

import java.util.List;

import com.pruvn.printserver.entity.Sqlparammaster;



public interface SqlparammasterService  {
	 List<Sqlparammaster> findByNameSqlPara(String name);
	 List<Sqlparammaster> findByFriendlyname(String friendlyname);
	 List<Sqlparammaster> findByTypeid(Long typeid);
	 List<Sqlparammaster> findByDocid(Long datasourceid);
	 List<Sqlparammaster> findByFieldtype(String fieldtype);

}
