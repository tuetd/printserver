package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.GroupM;

public interface GroupMService {
	
	List<GroupM> findAll();
	
	GroupM getById(Integer id);
	
	public GroupM findByCode(String groupcode);
	
	void saveOrUpdate(GroupM dept);

	void delete(GroupM dept);

}
