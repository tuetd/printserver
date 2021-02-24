package com.pruvn.printserver.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.pruvn.printserver.dao.SqlparammasterDetailDAO;
import com.pruvn.printserver.entity.SqlparammasterDetail;
import com.pruvn.printserver.services.SqlparammasterDetailService;

public class SqlparammasterDetailServiceImpl implements SqlparammasterDetailService{
		private SqlparammasterDetailDAO sqlparammasterDetailDAO;

		public SqlparammasterDetailDAO getSqlparammasterDetailDAO() {
			return sqlparammasterDetailDAO;
		}

		public void setSqlparammasterDetailDAO(
				SqlparammasterDetailDAO sqlparammasterDetailDAO) {
			this.sqlparammasterDetailDAO = sqlparammasterDetailDAO;
		}

		@Override
		public List<String> getFieldsWith_FCL_REQ_ESTIMATED(Long id,
				String sqlparammasterDetailReasonFcl) {
			List<String> lst=new ArrayList<String>();
			List<SqlparammasterDetail> detail=sqlparammasterDetailDAO.getFieldsWith_FCL_REQ_ESTIMATED(id,sqlparammasterDetailReasonFcl);
			for (SqlparammasterDetail sqlparammasterDetail : detail) {
				lst.add(sqlparammasterDetail.getValue());
			}
			return lst;
		}
		

	

}
