package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.UserRoleM;


public interface UserRoleMDAO extends GenericDAO<UserRoleM,Long>{

	 List<UserRoleM> getRole(Long code);
}
