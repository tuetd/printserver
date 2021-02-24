package com.pruvn.tools.printserver.webapp.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Channel_Dtl_DAO;
import com.pruvn.tools.common.util.UtilConverter;
import com.pruvn.tools.printserver.DocmasterDAO;
import com.pruvn.tools.printserver.FieldtypemasterDAO;
import com.pruvn.tools.printserver.ParammasterDAO;
import com.pruvn.tools.printserver.SqlparammasterDAO;
import com.pruvn.tools.printserver.UserdocprinterDAO;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.pojo.Docmaster;
import com.pruvn.tools.printserver.pojo.Fieldtypemaster;
import com.pruvn.tools.printserver.pojo.Sqlparammaster;
import com.pruvn.tools.printserver.pojo.Userdocprinter;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.webapp.editor.DocumentForm;
import com.pruvn.tools.printserver.webapp.editor.DocumentParameterDto;
import com.pruvn.tools.printserver.webapp.editor.DocumentParametersForm;
import com.pruvn.tools.printserver.webapp.service.DocumentPrintingEngineService;



@Controller
public class DocumentListController {
	private static final Logger logger=Logger.getLogger(DocumentListController.class);  
	private List<String> documentlist;
	private DocumentForm documentform;
	private UsermasterDAO usermasterDAO;
	private UserdocprinterDAO userdocprinterDAO;
	private SqlparammasterDAO sqlparammasterDAO;
	private FieldtypemasterDAO fieldtypemasterDAO;
	private DocmasterDAO docmasterDAO;
	private DocumentPrintingEngineService documentPrintingEngineService;
    private File fileUpload;
    private InputStream inputStream;
    private List<String> paramlist;
    private String documentname;
    private ParammasterDAO parammasterDAO;
    private Channel_Dtl_DAO channelDAO;
	public List<String> getDocumentlist() {
		return documentlist;
	}

	public void setDocumentlist(List<String> documentlist) {
		this.documentlist = documentlist;
	}
	@RequestMapping(value="listDocument.html",method=RequestMethod.GET)
	public String listDocument(Model model){
		try {			
			documentlist = new ArrayList<String>();
			documentform = new DocumentForm();
			Usermaster usermaster =usermasterDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			List<Userdocprinter> lstPerbyUser = userdocprinterDAO
					.findByUserid(usermaster.getId());
			for (Userdocprinter userdocprinter : lstPerbyUser) {
				Docmaster dos = docmasterDAO.getById(userdocprinter
						.getUserdocprinterPK().getDocid());
				if(dos.getStatus().toUpperCase().equals("ACTIVE")){
				documentlist.add(dos.getName());
				}
			}
			model.addAttribute("documentlist", documentlist);
			model.addAttribute("documentForm", documentform);
		} catch (Exception e) {			
		}	
		return "document/listDocument";
	}
	
	@RequestMapping(value="listDocument.html",method=RequestMethod.POST)
	public String selectlistDocument(@ModelAttribute("documentForm") DocumentForm documentForm,Model model){
		logger.info(documentForm.getDocumentname());
		 DocumentParametersForm paradocumentForm= new DocumentParametersForm();
		 paradocumentForm.setList(new ArrayList<DocumentParameterDto>());
		 paradocumentForm.setParamnamelist(new ArrayList<String>());
		 paradocumentForm.setParamtypelist(new ArrayList<String>());
		 List<Docmaster> listDocmaster= new ArrayList<Docmaster>();
		 listDocmaster = docmasterDAO.findByName(documentForm.getDocumentname());
		if(!listDocmaster.isEmpty()){
		Docmaster docmaster =  docmasterDAO.findByName(documentForm.getDocumentname()).get(0);
		List<Sqlparammaster> lstSqlparamaseter = sqlparammasterDAO.findByDatasourceid(docmaster.getDatasourceId());
		DocumentParameterDto[] arrayParam = new DocumentParameterDto[lstSqlparamaseter.size()];
		Iterator iterator = lstSqlparamaseter.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Sqlparammaster sqlparammaster = (Sqlparammaster)iterator.next();
			DocumentParameterDto dto = new DocumentParameterDto();
			dto.setParamFriendlyName(sqlparammaster.getFriendlyname());
			Fieldtypemaster filetypemasters= fieldtypemasterDAO.getById(sqlparammaster.getTypeid());
			dto.setParamType(filetypemasters.getName());
			dto.setParamName(sqlparammaster.getName());
			dto.setFieldType(sqlparammaster.getFieldtype());
			dto.setID(sqlparammaster.getId());
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
	    paradocumentForm.setDocumentname(documentForm.getDocumentname());
	    model.addAttribute("paradocumentForm",paradocumentForm);
		return "document/listParameterDocument";
	}
	
	@RequestMapping(value="printDocument.html",method=RequestMethod.POST)
	public String printDocument(@ModelAttribute("paradocumentForm") DocumentParametersForm paradocumentForm,@RequestParam("paramnamelist") List<String> paramnamelist,@RequestParam("paramtypelist") List<String> paramtypelist,@RequestParam("documentname") String documentname,Model model,HttpServletResponse response) throws  Exception {
		paramlist = new ArrayList<String>();
		if(paradocumentForm.getList()!=null){
		for (DocumentParameterDto documentParameterDto : paradocumentForm.getList()) {
			paramlist.add(documentParameterDto.getParamType());
		}
		}
		Usermaster usermatser = usermasterDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		paramnamelist=pasrseList(paramnamelist);
		paramtypelist=pasrseList(paramtypelist);
		int flag = 0;
		for(int i = 0; i< paramnamelist.size(); i ++)
		{
			
			if( paramnamelist.get(i).equals("applid") || paramnamelist.get(i).equals("APPLID")  || paramnamelist.get(i).equals("APP_ID_C") || paramnamelist.get(i).equals("app_id_c"))
			{
				flag = 1;
			}
		}
		if(flag == 1)
		{
			String appid = paramnamelist.get(0);
			if("".equalsIgnoreCase(appid))
			{
				model.addAttribute("message", "Application id is not null!");
				model.addAttribute("backUrl", "listDocument.html");
				 return "usermanager/common_success";
			}
			
			String checkAllow = channelDAO.printAllow(documentname, appid);
			if(!"0".equalsIgnoreCase(checkAllow)){
				model.addAttribute("message", checkAllow);
				model.addAttribute("backUrl", "listDocument.html");
				 return "usermanager/common_success";
			}
			String channelList = usermatser.getUserChannel();
			if(channelList!=null && !"".equalsIgnoreCase(channelList))
			{
				int checkExistChannel = channelDAO.check_app_id_by_channels(appid, channelList);
				if(checkExistChannel==0)
				{
					model.addAttribute("message", "Your role can not access this form no!");
					model.addAttribute("backUrl", "listDocument.html");
					 return "usermanager/common_success";
				}
			}
		}

			InputStream inputStreams = null;
			try {
				inputStreams = documentPrintingEngineService.printDocument(documentname, paramlist, paramnamelist, paramtypelist);
				//view pdf ra file browse
			    response.setContentType("application/pdf");
			    response.setHeader("Content-Disposition", "inline;filename="+UtilConverter.getHttpSessionID()+".pdf");
	
		        IOUtils.copy(inputStreams, response.getOutputStream());
		        response.flushBuffer();
		        return null;
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
				model.addAttribute("backUrl", "listDocument.html");
				e.printStackTrace();
			} finally {
				if(inputStreams!=null){
				inputStreams.close();
				}
			}
		
	    return "usermanager/common_success";
	}
	
	
	private List<String> pasrseList(List<String> source){
		List<String> dest= new ArrayList<String>();
	  	int count=1;
		 for (int i = 0; i < source.size(); i++) {
			 String para=source.get(i);
			if(count==1){
				para=para.substring(1,para.length());
				
			}
			if(count==source.size()){
				para = para.substring(0,para.length()-1);
			}
			dest.add(para);
			count++;
		}
		return dest;
	}
	
	public DocumentForm getDocumentform() {
		return documentform;
	}

	public void setDocumentform(DocumentForm documentform) {
		this.documentform = documentform;
	}

	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}
	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	public UserdocprinterDAO getUserdocprinterDAO() {
		return userdocprinterDAO;
	}
	@Autowired
	public void setUserdocprinterDAO(UserdocprinterDAO userdocprinterDAO) {
		this.userdocprinterDAO = userdocprinterDAO;
	}

	public DocmasterDAO getDocmasterDAO() {
		return docmasterDAO;
	}
	@Autowired
	public void setDocmasterDAO(DocmasterDAO docmasterDAO) {
		this.docmasterDAO = docmasterDAO;
	}

	public SqlparammasterDAO getSqlparammasterDAO() {
		return sqlparammasterDAO;
	}
	@Autowired
	public void setSqlparammasterDAO(SqlparammasterDAO sqlparammasterDAO) {
		this.sqlparammasterDAO = sqlparammasterDAO;
	}

	public FieldtypemasterDAO getFieldtypemasterDAO() {
		return fieldtypemasterDAO;
	}
	@Autowired
	public void setFieldtypemasterDAO(FieldtypemasterDAO fieldtypemasterDAO) {
		this.fieldtypemasterDAO = fieldtypemasterDAO;
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

	public DocumentPrintingEngineService getDocumentPrintingEngineService() {
		return documentPrintingEngineService;
	}
	@Autowired
	public void setDocumentPrintingEngineService(
			DocumentPrintingEngineService documentPrintingEngineService) {
		this.documentPrintingEngineService = documentPrintingEngineService;
	}

	public ParammasterDAO getParammasterDAO() {
		return parammasterDAO;
	}
	@Autowired
	public void setParammasterDAO(ParammasterDAO parammasterDAO) {
		this.parammasterDAO = parammasterDAO;
	}

	/**
	 * @return the channelDAO
	 */
	public Channel_Dtl_DAO getChannelDAO() {
		return channelDAO;
	}

	/**
	 * @param channelDAO the channelDAO to set
	 */
	@Autowired
	public void setChannelDAO(Channel_Dtl_DAO channelDAO) {
		this.channelDAO = channelDAO;
	}

	
	
	
}
