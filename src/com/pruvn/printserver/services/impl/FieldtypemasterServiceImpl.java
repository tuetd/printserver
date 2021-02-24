package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.FieldtypemasterDAO;
import com.pruvn.printserver.entity.Fieldtypemaster;
import com.pruvn.printserver.services.FieldtypemasterService;

public class FieldtypemasterServiceImpl implements FieldtypemasterService{
		private FieldtypemasterDAO fieldtypemasterDAO;
		

		public FieldtypemasterDAO getFieldtypemasterDAO() {
			return fieldtypemasterDAO;
		}

		@Autowired
		public void setFieldtypemasterDAO(FieldtypemasterDAO fieldtypemasterDAO) {
			this.fieldtypemasterDAO = fieldtypemasterDAO;
		}


		@Override
		public List<Fieldtypemaster> findByNameField(String name) {
			return fieldtypemasterDAO.findByNameField(name);
		}

		@Override
		public Fieldtypemaster getById(Long typeid) {
			return fieldtypemasterDAO.findById(typeid);
		}



}
