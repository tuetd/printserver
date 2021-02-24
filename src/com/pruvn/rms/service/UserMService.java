package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.Branch;
import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.Product;
import com.pruvn.rms.domain.UserDeptA;
import com.pruvn.rms.domain.UserLevelA;
import com.pruvn.rms.domain.UserLevelM;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.dto.UserMDTO;
import com.pruvn.rms.service.exceptions.UserException;

public interface UserMService {
	UserM getUserByUserName(String username) throws UserException;
	
	UserM getUserByEmail(String email) throws UserException;
	
	UserM findUser(String email, String username) throws UserException;
	
	UserDeptA getUserDeptByUser(UserM user);
	
	UserDeptA getUserDeptByUsername(String username);

	void saveOrUpdate(UserM user);

	UserM getUserByUserId(Integer integer);

	List<UserM> getAllUsers();

	void delete(String userid);
	
	void deactive(String userid);

	List<DeptM> loadAllDepartments();

	UserDeptA findUserDeptByUser(UserM user);

	DeptM findDeptByDeptCode(String department);

	void saveOrUpdateUserDept(UserDeptA userDept);

	List<UserM> searchUserByUserName(String username);

	void saveOrUpdate(UserM user, String deptCode, String levelCode) throws UserException;

	List<GroupM> getAllGroupByUser(UserM user);

	UserLevelA getUserLevelByUser(UserM user);

	List<UserLevelM> loadAllUserLevels();

	List<UserM> findUserByDeptCodeAndLevelCode(String string, String string2);

	List<String> findByFullName(String user,String grouprole);

	List<UserM> findByRole(String type);
	
	List<Branch> loadAllBranchs();
	
	List<Product> loadAllProductsByBranchId(Integer branchId);
	
	List<UserMDTO> getListUsers();
}
