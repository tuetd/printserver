package com.pruvn.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pruvn.rms.dao.BranchDao;
import com.pruvn.rms.dao.DeptMDAO;
import com.pruvn.rms.dao.ProductDao;
import com.pruvn.rms.dao.UserDeptADAO;
import com.pruvn.rms.dao.UserGroupADAO;
import com.pruvn.rms.dao.UserLevelADao;
import com.pruvn.rms.dao.UserLevelMDao;
import com.pruvn.rms.dao.UserMDAO;
import com.pruvn.rms.domain.Branch;
import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.Product;
import com.pruvn.rms.domain.UserDeptA;
import com.pruvn.rms.domain.UserGroupA;
import com.pruvn.rms.domain.UserLevelA;
import com.pruvn.rms.domain.UserLevelM;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.dto.UserMDTO;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;

public class UserMServiceImpl implements UserMService {
	
	private UserMDAO userMDAO;
	private DeptMDAO deptMDAO;
	
	private UserDeptADAO userDeptADAO;
	private UserGroupADAO userGroupADAO;
	
	private UserLevelADao userLevelADao;
	private UserLevelMDao userLevelMDao;
	
	private BranchDao branchDao;
	
	private ProductDao productDao;


	/**
	 * @return the userLevelADao
	 */
	public UserLevelADao getUserLevelADao() {
		return userLevelADao;
	}

	/**
	 * @param userLevelADao the userLevelADao to set
	 */
	public void setUserLevelADao(UserLevelADao userLevelADao) {
		this.userLevelADao = userLevelADao;
	}

	/**
	 * @return the userLevelMDao
	 */
	public UserLevelMDao getUserLevelMDao() {
		return userLevelMDao;
	}

	/**
	 * @param userLevelMDao the userLevelMDao to set
	 */
	public void setUserLevelMDao(UserLevelMDao userLevelMDao) {
		this.userLevelMDao = userLevelMDao;
	}

	/**
	 * @return the userGroupADAO
	 */
	public UserGroupADAO getUserGroupADAO() {
		return userGroupADAO;
	}

	/**
	 * @param userGroupADAO the userGroupADAO to set
	 */
	public void setUserGroupADAO(UserGroupADAO userGroupADAO) {
		this.userGroupADAO = userGroupADAO;
	}

	/**
	 * @return the deptMDAO
	 */
	public DeptMDAO getDeptMDAO() {
		return deptMDAO;
	}

	/**
	 * @param deptMDAO the deptMDAO to set
	 */
	public void setDeptMDAO(DeptMDAO deptMDAO) {
		this.deptMDAO = deptMDAO;
	}

	/**
	 * @return the userDeptADAO
	 */
	public UserDeptADAO getUserDeptADAO() {
		return userDeptADAO;
	}

	/**
	 * @param userDeptADAO the userDeptADAO to set
	 */
	public void setUserDeptADAO(UserDeptADAO userDeptADAO) {
		this.userDeptADAO = userDeptADAO;
	}

	public UserMDAO getUserMDAO() {
		return userMDAO;
	}

	public void setUserMDAO(UserMDAO userMDAO) {
		this.userMDAO = userMDAO;
	}
	
	public BranchDao getBranchDao() {
		return branchDao;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public UserM getUserByUserName(String username) throws UserException {
		List<UserM> users = userMDAO.findByUsername(username);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdate(UserM user) {
		userMDAO.saveOrUpdate(user);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(UserM user, String deptCode, String levelCode) throws UserException {
		userMDAO.saveOrUpdate(user);
		
		user = getUserByUserName(user.getUsername());
		if(!"0".equalsIgnoreCase(deptCode)) {
			UserDeptA userDept = findUserDeptByUser(user);
			if (userDept == null) {
				userDept = new UserDeptA();
			}
					
			DeptM dept = findDeptByDeptCode(deptCode);
			userDept.setDept(dept);
			userDept.setUser(user);
			
			saveOrUpdateUserDept(userDept);
		} else {
			//remove user dept by user id
			userDeptADAO.deleteByUserId(user.getId());
		}
		if(!"0".equalsIgnoreCase(levelCode)) {
			UserLevelA userLevelA = userLevelADao.findByuserid(user);
			if (userLevelA == null) {
				userLevelA = new UserLevelA();
			}
			
			UserLevelM userLevelM = findBylevelcode(levelCode);
			userLevelA.setLevel(userLevelM);
			userLevelA.setUser(user);
			
			userLevelADao.saveOrUpdate(userLevelA);
		} else {
			//remove user level by user id
			userLevelADao.deleteByUserId(user.getId());
		}
	}

	private UserLevelM findBylevelcode(String levelCode) {
		List<UserLevelM> list = userLevelMDao.findBylevelcode(levelCode);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public UserDeptA getUserDeptByUser(UserM user) {
		List<UserDeptA> list = userDeptADAO.findByUser(user);
		if (list != null && list.size() > 0 ) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public UserDeptA getUserDeptByUsername(String username) {
		return userDeptADAO.getUserDeptByUsername(username);
	}

	@Override
	public UserM findUser(String email, String username) throws UserException {
		List<UserM> users = userMDAO.findUser(email, username);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		
		throw new UserException("User not found !");
	}

	@Override
	public UserM getUserByUserId(Integer integer) {
		return userMDAO.getById(integer);
	}

	@Override
	public List<UserM> getAllUsers() {
		return userMDAO.findAll();
	}

	@Override
	@Transactional
	public void delete(String userid) {
		UserM user = userMDAO.getById(new Integer(userid));
		List<UserGroupA> userGroups = userGroupADAO.findByUser(user);
		UserLevelA userLevelA = userLevelADao.findByuserid(user);
		
		for (UserGroupA userGroupA : userGroups) {
			userGroupADAO.delete(userGroupA);
		}
		
		if (userLevelA != null) {
			userLevelADao.delete(userLevelA);
		}
		
		userMDAO.delete(user);
	}
	
	@Override
	public void deactive(String userid) {
		userMDAO.deactive(userid);
	}

	@Override
	public List<DeptM> loadAllDepartments() {
		return deptMDAO.findAll();
	}

	@Override
	public UserDeptA findUserDeptByUser(UserM user) {
		List<UserDeptA> list = userDeptADAO.findByUser(user);
		
		if (list != null && list.size() > 0) {
			list.get(0);
		}
		
		return null;
	}

	@Override
	public DeptM findDeptByDeptCode(String department) {
		List<DeptM> list = deptMDAO.findByDeptcode(department);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void saveOrUpdateUserDept(UserDeptA userDept) {
		userDeptADAO.saveOrUpdate(userDept);
		
	}

	@Override
	public List<UserM> searchUserByUserName(String username) {
		return userMDAO.searchUserByUserName(username);
	}

	@Override
	public UserM getUserByEmail(String email) throws UserException {
		List<UserM> users = userMDAO.findByEmailCode(email);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		
		throw new UserException("User not found !");
	}

	@Override
	public List<GroupM> getAllGroupByUser(UserM user) {
		List<UserGroupA> lst = userGroupADAO.findByUser(user);
		List<GroupM> ret = new ArrayList<GroupM>();
		for (UserGroupA userGroupA : lst) {
			ret.add(userGroupA.getGroup());
		}
		
		return ret;
	}

	@Override
	public UserLevelA getUserLevelByUser(UserM user) {
		return userLevelADao.findByuserid(user);
	}

	@Override
	public List<UserLevelM> loadAllUserLevels() {
		return userLevelMDao.findAll();
	}

	@Override
	public List<UserM> findUserByDeptCodeAndLevelCode(String deptCode,
			String levelCode) {
		return userMDAO.findUserByDeptCodeAndLevelCode(deptCode, levelCode);
	}

	@Override
	public List<String> findByFullName(String user,String grouprole) {
		return userMDAO.findByFullName(user,grouprole);
	}

	@Override
	public List<UserM> findByRole(String type) {
		return userMDAO.findByRole(type);
	}
	
	@Override
	public List<Branch> loadAllBranchs(){
		return branchDao.findAll();
	}
	
	@Override
	public List<Product> loadAllProductsByBranchId(Integer branchId){
		return productDao.findByBranchId(branchId);
	}
	
	@Override
	public List<UserMDTO> getListUsers(){
		List<UserMDTO> result = new ArrayList<UserMDTO>();
		List<UserM> list = getAllUsers();
		UserMDTO dto = null;
		for(UserM user: list){
			//Copy priority from user to userDTO
			dto = new UserMDTO();
			dto.setId(user.getId());
			dto.setUsername(user.getUsername());
			dto.setFullname(user.getFullname());
			dto.setEmailCode(user.getEmailCode());
			dto.setUserPlace(user.getUserPlace());
			dto.setIsActived(user.getIsActived());
			UserDeptA  userDept = getUserDeptByUser(user);
			if(userDept != null && userDept.getDept() != null) {
				dto.setDepartment(userDept.getDept().getDeptname()); 
			}
			
			List<GroupM> listGroup  = getAllGroupByUser(user);
			String roles  = "";
			for(GroupM group : listGroup) {
				roles += group.getGroupcode() +  "\n";
			}
			dto.setRoles(roles);
			
			String status =  user.getIsActived() == 1 ?  "Active" : "Deactive";
			dto.setStatus(status);
			result.add(dto);
			
		
		}
		
		return result;
	}
}
