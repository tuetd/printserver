package com.pruvn.rms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.rms.domain.DeptM;
import com.pruvn.rms.model.DeptForm;
import com.pruvn.rms.service.DeptMService;
import com.pruvn.rms.utils.CommonUtils;


@Controller
public class DeptController 
{
	private static final Logger logger = Logger.getLogger(DeptController.class);
	
	private DeptMService deptMService;
	
	
	/**
	 * @return the deptMService
	 */
	public DeptMService getDeptMService() {
		return deptMService;
	}

	/**
	 * @param deptMService the deptMService to set
	 */
	@Autowired
	public void setDeptMService(DeptMService deptMService) {
		this.deptMService = deptMService;
	}

	@RequestMapping(value = "/admin/deptlist", method = RequestMethod.GET)
	public String userlist(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$userlist...");
		List<DeptM> list = deptMService.getAllDepts();
		model.addAttribute("deptList", list);
		return "deptlist";
	}

	@RequestMapping(value = "/admin/deptmod", method = RequestMethod.GET)
	public String createuser(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$createdept...");
		DeptForm form = new DeptForm();
		String userid = request.getParameter("id");
		if (StringUtils.isNotEmpty(userid) && CommonUtils.isNumeric(userid.trim())) {
			// Get user and popular this user to form
			DeptM dept = deptMService.getDeptByDeptId(new Integer(userid));
			if (dept != null) {
				form.setId(dept.getId());
				form.setDeptCode(dept.getDeptcode());
				form.setDeptName(dept.getDeptname());
			}
		}
		model.addAttribute(form);
		return "deptmod";
	}
	
	@RequestMapping(value = "/admin/deptmod", method = RequestMethod.POST)
	public String createdeptSubmit(@Valid DeptForm form, BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response)  throws Exception
	{
		logger.debug("reach DeptController$createdeptSubmit...");
		DeptM dept = null;
		
		if (StringUtils.isEmpty(form.getDeptCode())) {
			result.reject("dept.emptyrequiredfield", "Please input required field !");
			model.addAttribute(form);
			return "deptmod";
		}
		
		if (form.getId() != null && form.getId() > 0) {
			dept = deptMService.getDeptByDeptId(form.getId());	
		} else {
			dept = deptMService.getDeptByDeptCode(form.getDeptCode());
			
			if (dept != null) {
				result.reject("dept.notexisted", "Dept " + form.getDeptCode() + " existed !");
				model.addAttribute(form);
				return "deptmod";
			}
		}
		
		if (dept == null) {
			dept = new DeptM();
		}
		
		dept.setDeptcode(form.getDeptCode());
		dept.setDeptname(form.getDeptName());
		
		deptMService.saveOrUpdate(dept);

		return "redirect:/admin/deptlist.html";
	}
	
	@RequestMapping(value = "/admin/deptdel", method = RequestMethod.GET)
	public String deptdel(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach DeptController$deptdel...");
		String deptid = request.getParameter("id");
		if (StringUtils.isNotEmpty(deptid) && CommonUtils.isNumeric(deptid.trim())) {
			// Get dept and popular this user to form
			DeptM dept = deptMService.getDeptByDeptId(new Integer(deptid));
			if (dept != null) {
				deptMService.delete(dept);
			}
		}
		return "redirect:/admin/deptlist.html";
	}
}


