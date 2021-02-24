package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.UserM;

public interface UserGroupAService {

	List<String> getAllPermissions();

	List<String> getGrantedPermissionsByUser(UserM user);

	void grantPermissions(UserM user, List<String> list);
}
