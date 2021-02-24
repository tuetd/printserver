package com.pruvn.printserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pruvn.printserver.dao.SqlparammasterDAO;
import com.pruvn.printserver.entity.Sqlparammaster;
import com.pruvn.printserver.services.SqlparammasterService;

public class SqlparammasterServiceImpl implements SqlparammasterService{
		private SqlparammasterDAO sqlparammasterDAO;
		
	
		public SqlparammasterDAO getSqlparammasterDAO() {
			return sqlparammasterDAO;
		}

		@Autowired
		public void setSqlparammasterDAO(SqlparammasterDAO sqlparammasterDAO) {
			this.sqlparammasterDAO = sqlparammasterDAO;
		}

		@Override
		public List<Sqlparammaster> findByNameSqlPara(String name) {
			return sqlparammasterDAO.findByNameSqlPara(name);
		}

		@Override
		public List<Sqlparammaster> findByFriendlyname(String friendlyname) {
			return sqlparammasterDAO.findByFriendlyname(friendlyname);
		}

		@Override
		public List<Sqlparammaster> findByTypeid(Long typeid) {
			return sqlparammasterDAO.findByTypeid(typeid);
		}

		@Override
		public List<Sqlparammaster> findByDocid(Long datasourceid) {
			return sqlparammasterDAO.findByDocid(datasourceid);
		}

		@Override
		public List<Sqlparammaster> findByFieldtype(String fieldtype) {
			return sqlparammasterDAO.findByFieldtype(fieldtype);
		}

		


	

}
