package com.pruvn.printserver.services;

import java.util.List;

import com.pruvn.printserver.entity.UserRoleM;




public interface UserRoleMService  {
  List<UserRoleM> getRole(Long code);
}
