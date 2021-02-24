package com.pruvn.printserver.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.printserver.entity.Docmaster;
import com.pruvn.printserver.entity.Fieldtypemaster;
import com.pruvn.printserver.entity.Sqlparammaster;
import com.pruvn.printserver.entity.UserDocPrinter;
import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.form.DocumentForm;
import com.pruvn.printserver.form.DocumentParameterDto;
import com.pruvn.printserver.form.DocumentParametersForm;
import com.pruvn.printserver.services.DocmasterService;
import com.pruvn.printserver.services.FieldtypemasterService;
import com.pruvn.printserver.services.SqlparammasterDetailService;
import com.pruvn.printserver.services.SqlparammasterService;
import com.pruvn.printserver.services.UserDocPrinterService;
import com.pruvn.printserver.services.UsermasterService;
import com.pruvn.printserver.utils.Constant;




@Controller
public class DocumentListController {
	private static final Logger logger=Logger.getLogger(DocumentListController.class);  
	private List<Docmaster> documentlist;
	private DocumentForm documentform;
    private File fileUpload;
    private InputStream inputStream;
    private List<String> paramlist;
    private String documentname;
    private UsermasterService usermasterServer;
    private UserDocPrinterService userdocprinterServer;
    private DocmasterService docmasterServer;
    private SqlparammasterService sqlparammasterService;
    private FieldtypemasterService fieldtypemasterService;
    private SqlparammasterDetailService sqlparammasterDetailService;
    
    
	public DocumentForm getDocumentform() {
		return documentform;
	}
	public void setDocumentform(DocumentForm documentform) {
		this.documentform = documentform;
	}
	public FieldtypemasterService getFieldtypemasterService() {
		return fieldtypemasterService;
	}
	@Autowired
	public void setFieldtypemasterService(
			FieldtypemasterService fieldtypemasterService) {
		this.fieldtypemasterService = fieldtypemasterService;
	}
	public SqlparammasterService getSqlparammasterService() {
		return sqlparammasterService;
	}
	@Autowired
	public void setSqlparammasterService(SqlparammasterService sqlparammasterService) {
		this.sqlparammasterService = sqlparammasterService;
	}
	public DocmasterService getDocmasterServer() {
		return docmasterServer;
	}
    @Autowired
	public void setDocmasterServer(DocmasterService docmasterServer) {
		this.docmasterServer = docmasterServer;
	}

	
	public UsermasterService getUsermasterServer() {
		return usermasterServer;
	}
	@Autowired
	public void setUsermasterServer(UsermasterService usermasterServer) {
		this.usermasterServer = usermasterServer;
	}

	
	public UserDocPrinterService getUserdocprinterServer() {
		return userdocprinterServer;
	}
	@Autowired
	public void setUserdocprinterServer(UserDocPrinterService userdocprinterServer) {
		this.userdocprinterServer = userdocprinterServer;
	}

	public SqlparammasterDetailService getSqlparammasterDetailService() {
		return sqlparammasterDetailService;
	}
	@Autowired
	public void setSqlparammasterDetailService(
			SqlparammasterDetailService sqlparammasterDetailService) {
		this.sqlparammasterDetailService = sqlparammasterDetailService;
	}
	public static Logger getLogger() {
		return logger;
	}

	public List<Docmaster> getDocumentlist() {
		return documentlist;
	}

	public void setDocumentlist(List<Docmaster> documentlist) {
		this.documentlist = documentlist;
	}
	@RequestMapping(value="listDocument.html",method=RequestMethod.GET)
	public String listDocument(Model model){
		try {			
			documentlist = new ArrayList<Docmaster>();
			documentform = new DocumentForm();
			Usermaster user=usermasterServer.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
			List<UserDocPrinter> lstPerbyUser=userdocprinterServer.findByUserid(user.getId());
			for (UserDocPrinter userdocprinter : lstPerbyUser) {
				Docmaster dos = docmasterServer.getById(userdocprinter
						.getUserdocprinterPK().getDocid());
				if(dos.getStatus().toUpperCase().equals("ACTIVE")){
				documentlist.add(dos);
				}
			}
			model.addAttribute("documentlist", documentlist);
			model.addAttribute("documentForm", documentform);
		} catch (Exception e) {			
		}	
		return "document/listDocument";
	}
	
	@RequestMapping(value = "/selectDocument.html", method = RequestMethod.GET)
	public String selectDocument( @RequestParam("documentname") String documentname,HttpServletRequest request,Model model) throws UserException {
		logger.info(documentname);
		Usermaster user=usermasterServer.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()); 
		 DocumentParametersForm paradocumentForm= new DocumentParametersForm();
		 paradocumentForm.setList(new ArrayList<DocumentParameterDto>());
		 paradocumentForm.setParamnamelist(new ArrayList<String>());
		 paradocumentForm.setParamtypelist(new ArrayList<String>());
		 List<Docmaster> listDocmaster= new ArrayList<Docmaster>();
		 listDocmaster = docmasterServer.findByNameDocmaster(documentname);
		if(!listDocmaster.isEmpty()){
		Docmaster docmaster =  docmasterServer.findByNameDocmaster(documentname).get(0);
		//check Role user duoc in hay khong.
		List<UserDocPrinter> lstPerbyUser=userdocprinterServer.findByUseridAnDocid(user.getId(),docmaster.getId());
		if(lstPerbyUser.isEmpty()){
			model.addAttribute("message", "User not accept print document: "+documentname);
			model.addAttribute("backUrl", "listDocument.html");
			return "document/common_success"; 
		}
		List<Sqlparammaster> lstSqlparamaseter = sqlparammasterService.findByDocid(docmaster.getId());
		DocumentParameterDto[] arrayParam = new DocumentParameterDto[lstSqlparamaseter.size()];
		Iterator iterator = lstSqlparamaseter.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Sqlparammaster sqlparammaster = (Sqlparammaster)iterator.next();
			DocumentParameterDto dto = new DocumentParameterDto();
			dto.setParamFriendlyName(sqlparammaster.getFriendlyname());
			Fieldtypemaster filetypemasters= fieldtypemasterService.getById(sqlparammaster.getTypeid());
			dto.setParamType(filetypemasters.getName());
			dto.setParamName(sqlparammaster.getName());
			dto.setFieldType(sqlparammaster.getFieldtype());
			dto.setID(sqlparammaster.getId());
			//if sqlparammaster.getFIELDTYPE() is combobox
			List<String> lstcombox=new ArrayList<String>();
			if(sqlparammaster.getFieldtype()!=null&&sqlparammaster.getFieldtype().equalsIgnoreCase(Constant.SQLPARAMMASTER_FIELDTYPE_COMBOBOX)){
				if (documentname.equals(Constant.DOCNAME_EARLYTERMINATION) || 
					documentname.equals(Constant.DOCNAME_EARLYTERMINATIONANDTRANSFERPAYMENT) 
					){
					lstcombox=sqlparammasterDetailService.getFieldsWith_FCL_REQ_ESTIMATED(docmaster.getId(),Constant.SQLPARAMMASTER_DETAIL_REASON_FCL);
				} 
			}
			dto.setFields(lstcombox);
			arrayParam[count] = dto;
			count++;
		}		
		Arrays.sort(arrayParam);
		for (DocumentParameterDto docparam: arrayParam) {
			paradocumentForm.getList().add(docparam);
			paradocumentForm.getParamnamelist().add(docparam.getParamName());
			paradocumentForm.getParamtypelist().add(docparam.getParamType());
		}
		}
	    paradocumentForm.setDocumentname(documentname);
	    model.addAttribute("paradocumentForm",paradocumentForm);
		return "document/listParameterDocument";
	}
	

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<String> getParamlist() {
		return paramlist;
	}

	public void setParamlist(List<String> paramlist) {
		this.paramlist = paramlist;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	
	
	
}
