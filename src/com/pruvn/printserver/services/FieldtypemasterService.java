package com.pruvn.printserver.services;

import java.util.List;

import com.pruvn.printserver.entity.Fieldtypemaster;



public interface FieldtypemasterService  {
	List<Fieldtypemaster> findByNameField(String name);
	Fieldtypemaster getById(Long typeid);

}
