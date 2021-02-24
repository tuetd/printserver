package com.pruvn.printserver.services;

import java.util.List;

import org.omg.CORBA.UserException;

import com.pruvn.printserver.entity.Usermaster;



public interface UsermasterService  {
	Usermaster getUserByUserName(String username) throws UserException;
	List<Usermaster> findAll();
	List<Usermaster> findByUsername(String username);
	Usermaster findById(Long pkFromRequest);
	void update(Usermaster create);
	void save(Usermaster create);
	void saveOrUpdate(Usermaster user);
}
