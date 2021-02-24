package com.pruvn.tools.printserver.webapp.controller;

import com.pruvn.tools.common.hibernate.finnone.printsrv.AuditlogFcl;
import com.pruvn.tools.common.hibernate.finnone.printsrv.hibernate.impl.AuditlogFclHibernateDAO;
import com.pruvn.tools.common.util.JxlUtil;
import com.pruvn.tools.printserver.webapp.editor.ReportFclForm;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.write.WritableWorkbook;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportFclController
{
  private static Log logger = LogFactory.getLog(ReportFclController.class);
  private AuditlogFclHibernateDAO auditLogFclDAO;
  
  @RequestMapping(value={"/reportFcl.html"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String viewReportFcl(Model model)
  {
    model.addAttribute("ReportFclSearch", new ReportFclForm());
    return "fcl/searchFcl";
  }
  
  @RequestMapping(value={"/exportReportFcl.html"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String runReport(@ModelAttribute("ReportFclSearch") ReportFclForm reportform, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("fromdate") String from, @RequestParam("todate") String to)
  {
    try
    {
      SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
      Date fromdate = simple.parse(from);
      Date todate = simple.parse(to);
      String rootpath = request.getSession().getServletContext()
        .getRealPath("/");
      SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd");
      String namefile = rootpath + "\\FclReport_" + sdfFile.format(fromdate) + 
        "_" + sdfFile.format(todate) + ".xls";
      
      Timestamp fromDt = new Timestamp(fromdate.getTime());
      Timestamp toDt = new Timestamp(todate.getTime() + 86400000L);
      try
      {
        List<AuditlogFcl> listLog = this.auditLogFclDAO.findByFromDate(
          fromDt, toDt);
        System.out.println(listLog.size());
        genFile(namefile, listLog);
        
        response.setHeader("Content-disposition", 
          "attachment; filename=FCL_" + sdfFile.format(fromdate) + 
          "_" + sdfFile.format(todate) + ".xls");
        File xls = new File(namefile);
        FileInputStream in = new FileInputStream(xls);
        
        OutputStream out = response.getOutputStream();
        
        byte[] buffer = new byte['?'];
        int length = 0;
        while ((length = in.read(buffer)) > 0) {
          out.write(buffer, 0, length);
        }
        in.close();
        out.close();
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      return null;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
  
  public static Log getLogger()
  {
    return logger;
  }
  
  public static void setLogger(Log logger)
  {
    logger = logger;
  }
  
  public AuditlogFclHibernateDAO getAuditLogFclDAO()
  {
    return this.auditLogFclDAO;
  }
  
  @Autowired
  public void setAuditLogFclDAO(AuditlogFclHibernateDAO auditLogFclDAO)
  {
    this.auditLogFclDAO = auditLogFclDAO;
  }
  
  private void genFile(String filename, List<AuditlogFcl> listLog)
    throws Exception
  {
    String dataSheet = "Data";
    JxlUtil util = new JxlUtil();
    WritableWorkbook workBook = util.createWorkBook(filename, dataSheet);
    util.addCellToSheet(0, 0, "Username", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(1, 0, "Department", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(2, 0, "Branch", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(3, 0, "App Id", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(4, 0, "Printed Date", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(5, 0, "Status", null,workBook.getSheet(dataSheet));
      
     // add column on sheet SR 1027
    //util.addCellToSheet(6, 0, "Approach", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(6, 0, "Estimate closing date", null,workBook.getSheet(dataSheet));
    util.addCellToSheet(7, 0, "Reason", null, workBook.getSheet(dataSheet)); 
    
    int colIdx = 0;
    Map<String, String> department = new HashMap();
    HashMap<String, Integer[]> hmap = new HashMap();
    if ((listLog != null) && (listLog.size() > 0))
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      for (int i = 0; i < listLog.size(); i++)
      {
        AuditlogFcl dto = (AuditlogFcl)listLog.get(i);
        String closedDate = dto.getEstClosedDate();
        if(closedDate != null && !closedDate.trim().equals("")){
        	try{
        		closedDate = closedDate.substring(0, 2) + "/" +closedDate.substring(2, 4) + "/"+closedDate.substring(4, 8); 
        	}catch (Exception e) {
        		closedDate = null;
			}
        }
        String dept = dto.getDepartment();
        String place = dto.getUserPlace();
        if (("O&T".equalsIgnoreCase(dept)) && ("PiCo Plaza".equalsIgnoreCase(place))) {
          place = "CS";
        }
        util.addCellToSheet(0, colIdx + 1,dto.getPrintedUser(), null,workBook.getSheet(dataSheet));
        util.addCellToSheet(1, colIdx + 1, dept,null, workBook.getSheet(dataSheet));
        util.addCellToSheet(2, colIdx + 1, place,null, workBook.getSheet(dataSheet));
        util.addCellToSheet(3, colIdx + 1,String.valueOf(dto.getAppId()), null, workBook.getSheet(dataSheet));
        util.addCellToSheet(4, colIdx + 1,sdf.format(dto.getCreatedDate()), null,workBook.getSheet(dataSheet));
        util.addCellToSheet(5, colIdx + 1, dto.getStatus(),null, workBook.getSheet(dataSheet));
        //add SR 1027
        //util.addCellToSheet(6, colIdx + 1, dto.getApproach(),null, workBook.getSheet(dataSheet));
        util.addCellToSheet(6, colIdx + 1, closedDate,null, workBook.getSheet(dataSheet));
        util.addCellToSheet(7, colIdx + 1, dto.getReasonFcl(),null, workBook.getSheet(dataSheet));
        
        colIdx++;
        
        String key = dept + "-" + place;
        Integer[] arr = new Integer[2];
        if (hmap.containsKey(key))
        {
          arr = (Integer[])hmap.get(key);
        }
        else
        {
          arr[0] = Integer.valueOf(0);
          arr[1] = Integer.valueOf(0);
        }
        if ("C".equalsIgnoreCase(dto.getStatus())) {
          arr[1] = Integer.valueOf(arr[1].intValue() + 1);
        }
        arr[0] = Integer.valueOf(arr[0].intValue() + 1);
        
        hmap.put(key, arr);
      }
    }
    String summarySheet = "Summary";
    workBook.createSheet(summarySheet, 1);
    util.addCellToSheet(0, 0, "Department", null, 
      workBook.getSheet(summarySheet));
    util.addCellToSheet(1, 0, "Branch", null, 
      workBook.getSheet(summarySheet));
    util.addCellToSheet(2, 0, "FCL", null, 
      workBook.getSheet(summarySheet));
    util.addCellToSheet(3, 0, "Closed", null, 
      workBook.getSheet(summarySheet));
    Set set = hmap.entrySet();
    Iterator iterator = set.iterator();
    colIdx = 0;
    Integer totalFCL = Integer.valueOf(0);
    Integer totalClosed = Integer.valueOf(0);
    while (iterator.hasNext())
    {
      Map.Entry me = (Map.Entry)iterator.next();
      String key = (String)me.getKey();
      String[] arrKey = key.split("-");
      Integer[] arr = (Integer[])me.getValue();
      util.addCellToSheet(0, colIdx + 1, 
        arrKey[0], null, 
        workBook.getSheet(summarySheet));
      util.addCellToSheet(1, colIdx + 1, 
        arrKey[1], null, 
        workBook.getSheet(summarySheet));
      util.addCellToSheet(2, colIdx + 1, 
        arr[0], null, 
        workBook.getSheet(summarySheet));
      util.addCellToSheet(3, colIdx + 1, 
        arr[1], null, 
        workBook.getSheet(summarySheet));
      totalFCL = Integer.valueOf(totalFCL.intValue() + arr[0].intValue());
      totalClosed = Integer.valueOf(totalClosed.intValue() + arr[1].intValue());
      colIdx++;
    }
    util.addCellToSheet(2, colIdx + 1, 
      totalFCL, null, 
      workBook.getSheet(summarySheet));
    util.addCellToSheet(3, colIdx + 1, 
      totalClosed, null, 
      workBook.getSheet(summarySheet));
    util.flush(workBook);
  }
}
