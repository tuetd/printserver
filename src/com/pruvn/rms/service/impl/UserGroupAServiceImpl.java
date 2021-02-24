package com.pruvn.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pruvn.rms.dao.GroupMDAO;
import com.pruvn.rms.dao.UserGroupADAO;
import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.UserGroupA;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.service.UserGroupAService;

public class UserGroupAServiceImpl implements UserGroupAService {
	private UserGroupADAO userGroupADAO;
	private GroupMDAO groupMDAO;

	/**
	 * @return the groupMDAO
	 */
	public GroupMDAO getGroupMDAO() {
		return groupMDAO;
	}

	/**
	 * @param groupMDAO the groupMDAO to set
	 */
	public void setGroupMDAO(GroupMDAO groupMDAO) {
		this.groupMDAO = groupMDAO;
	}

	public UserGroupADAO getUserGroupADAO() {
		return userGroupADAO;
	}

	public void setUserGroupADAO(UserGroupADAO userGroupADAO) {
		this.userGroupADAO = userGroupADAO;
	}

	@Override
	public List<String> getAllPermissions() {
		List<String> permissions = new ArrayList<String>();
		List<GroupM> groups = groupMDAO.findAll();
		if (groups != null && groups.size() > 0) {
			for (GroupM groupM : groups) {
				if(groupM.getIsActived() == 1)
					permissions.add(groupM.getGroupcode());
			}
		}
		return permissions;
	}

	@Override
	public List<String> getGrantedPermissionsByUser(UserM user) {
		List<String> grantedPermissions = new ArrayList<String>();
		List<UserGroupA> userGroups = userGroupADAO.findByUser(user);
		if (userGroups != null && userGroups.size() > 0) {
			for (UserGroupA userGroupA : userGroups) {
				grantedPermissions.add(userGroupA.getGroup().getGroupcode());
			}
		}
		return grantedPermissions;
	}

	@Override
	@Transactional
	public void grantPermissions(UserM user, List<String> permissionlist) {
		List<UserGroupA> userGroups = userGroupADAO.findByUser(user);
		for (UserGroupA userGroupA : userGroups) {
			userGroupADAO.delete(userGroupA);
		}
		
		if (permissionlist != null && permissionlist.size() > 0) {
			List<GroupM> permissions = groupMDAO.findByGroupcode(permissionlist);
			
			for (GroupM groupM : permissions) {
				UserGroupA entity = new UserGroupA();
				entity.setGroup(groupM);
				entity.setUser(user);
				userGroupADAO.saveOrUpdate(entity);
			}
		}
	}

	

}
