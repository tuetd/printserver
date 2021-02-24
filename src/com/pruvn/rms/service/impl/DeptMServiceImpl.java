package com.pruvn.rms.service.impl;

import java.util.List;

import com.pruvn.rms.dao.DeptMDAO;
import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.service.DeptMService;

public class DeptMServiceImpl implements DeptMService {
	private DeptMDAO deptMDAO;

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

	public List<DeptM> getAllDepts() {
		return deptMDAO.findAll();
	}

	public DeptM getDeptByDeptId(Integer integer) {
		return deptMDAO.getById(integer);
	}

	public DeptM getDeptByDeptCode(String deptCode) {
		List<DeptM> list = deptMDAO.findByDeptcode(deptCode);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public void saveOrUpdate(DeptM dept) {
		deptMDAO.saveOrUpdate(dept);
		
	}

	public void delete(DeptM dept) {
		deptMDAO.delete(dept);
		
	}

}
