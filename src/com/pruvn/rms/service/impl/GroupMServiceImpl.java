package com.pruvn.rms.service.impl;

import java.util.List;

import com.pruvn.rms.dao.GroupMDAO;
import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.service.GroupMService;

public class GroupMServiceImpl implements GroupMService  {

	private GroupMDAO groupMDAO;

	public GroupMDAO getGroupMDAO() {
		return groupMDAO;
	}

	public void setGroupMDAO(GroupMDAO groupMDAO) {
		this.groupMDAO = groupMDAO;
	}
	public List<GroupM> getAllDepts() {
		return groupMDAO.findAll();
	}

	public GroupM getById(Integer integer) {
		return groupMDAO.getById(integer);
	}

	public GroupM findByCode(String groupCode) {
		List<GroupM> list = groupMDAO.findByGroupcode(groupCode);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public void saveOrUpdate(GroupM group) {
		groupMDAO.saveOrUpdate(group);
	}

	public void delete(GroupM dept) {
		groupMDAO.delete(dept);
		
	}

	@Override
	public List<GroupM> findAll() {
		return groupMDAO.findAll();
	}

}
