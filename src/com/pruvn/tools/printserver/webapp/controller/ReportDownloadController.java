package com.pruvn.tools.printserver.webapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.tools.utils.ReportUtils;

@Controller
public class ReportDownloadController {
	private static Log logger=LogFactory.getLog(ReportDownloadController.class);
	@RequestMapping(value="/xuatFileDownload.html",method=RequestMethod.GET)
	public String xuatFielDownload(@RequestParam ("type") String type,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReportUtils.downloadReportWithCollectionDataSource(type);
		logger.info("filename"+ReportUtils.nameFile);
		model.addAttribute("filename", ReportUtils.nameFile);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition","attachment;filename="+ReportUtils.nameFile+"."+type);
	    String rootpath = request.getSession().getServletContext().getRealPath("/");
	    if(type.equals("pdf")){
	    	rootpath += "reports/pdf/"+ReportUtils.nameFile+"."+type;
	    }else if(type.equals("doc")){
	    	rootpath += "reports/word/"+ReportUtils.nameFile+"."+type;
	    }else if(type.equals("xls")){
	    	rootpath += "reports/excel/"+ReportUtils.nameFile+"."+type;
	    }
	    InputStream fileIn = null;
		System.out.println("-------------------------------------------");
		try {
			fileIn = new FileInputStream(rootpath);
	        IOUtils.copy(fileIn, response.getOutputStream());
	        response.flushBuffer();
	        return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileIn!=null){
				fileIn.close();
				}
		}
		return "viewReport";
	}
	
	
}
