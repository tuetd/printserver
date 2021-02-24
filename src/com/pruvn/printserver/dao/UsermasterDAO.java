package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.Usermaster;


public interface UsermasterDAO extends GenericDAO<Usermaster,Long>{
	 List<Usermaster> findByUsername(String username);
}
