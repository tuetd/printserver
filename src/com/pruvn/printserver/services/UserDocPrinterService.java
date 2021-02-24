package com.pruvn.printserver.services;

import java.util.List;

import com.pruvn.printserver.entity.UserDocPrinter;


public interface UserDocPrinterService {

	List<UserDocPrinter> findByUserid(Long id);

	List<UserDocPrinter> findByUseridAnDocid(Long id, Long id2);

}
