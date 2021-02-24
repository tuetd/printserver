package com.pruvn.tools.printserver.webapp.service;

import java.io.FileInputStream;
import java.util.List;

public interface DocumentPrintingEngineService {
	FileInputStream printDocument(String documentname, List<String> paramlist, List<String> paramnamelist, List<String> paramtypelist)  throws Exception;
}
