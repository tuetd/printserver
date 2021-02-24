package com.pruvn.printserver.dao;

import java.util.List;

import com.pruvn.printserver.entity.UserDocPrinter;


public interface UserDocPrinterDAO extends GenericDAO<UserDocPrinter,Long>{

	List<UserDocPrinter> findByUserid(Long id);

	List<UserDocPrinter> findByUseridAnDocid(Long userid, Long docid);

}
