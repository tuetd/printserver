package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.DeptM;

public interface DeptMService {

	List<DeptM> getAllDepts();

	DeptM getDeptByDeptId(Integer integer);

	DeptM getDeptByDeptCode(String deptCode);

	void saveOrUpdate(DeptM dept);

	void delete(DeptM dept);
}
