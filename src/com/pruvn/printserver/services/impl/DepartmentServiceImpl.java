package com.pruvn.printserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.DepartmentDAO;
import com.pruvn.printserver.services.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
		@Autowired
		private DepartmentDAO departmentDAO;

		public DepartmentDAO getDepartmentDAO() {
			return departmentDAO;
		}

		public void setDepartmentDAO(DepartmentDAO departmentDAO) {
			this.departmentDAO = departmentDAO;
		}
		
		
		
		
		

}
