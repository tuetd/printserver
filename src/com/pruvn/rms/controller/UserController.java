package com.pruvn.rms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pruvn.rms.common.Constants;
import com.pruvn.rms.domain.Branch;
import com.pruvn.rms.domain.Product;
import com.pruvn.rms.domain.UserDeptA;
import com.pruvn.rms.domain.UserLevelA;
import com.pruvn.rms.domain.UserLog;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.dto.UserLevelADto;
import com.pruvn.rms.dto.UserMDTO;
import com.pruvn.rms.model.ReportUserM;
import com.pruvn.rms.model.SearchUserForm;
import com.pruvn.rms.model.UserForm;
import com.pruvn.rms.service.UserLevelAService;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.PasswordUtil;
import com.pruvn.rms.utils.Constant.ACTIONS;
import com.pruvn.rms.utils.Constant.LOG_STATUS;
import com.pruvn.rms.utils.Constant.LOG_TYPE;
import com.pruvn.rms.utils.Constant.ParameterApplication;
import com.pruvn.rms.utils.MD5;
import com.pruvn.rms.utils.MailUtil;
import com.pruvn.rms.utils.ReportUtils;


@Controller
public class UserController 
{
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	private UserMService userMService;
	private UserLevelAService  userLevelAService;
	private UserLogService userLogService;
	
	public UserLogService getUserLogService() {
		return userLogService;
	}
	@Autowired
	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}
	public UserLevelAService getUserLevelAService() {
		return userLevelAService;
	}
	@Autowired
	public void setUserLevelAService(UserLevelAService userLevelAService) {
		this.userLevelAService = userLevelAService;
	}

	/**
	 * @return the userMService
	 */
	public UserMService getUserMService() {
		return userMService;
	}

	/**
	 * @param userMService the userMService to set
	 */
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}
	
	//search full
	@RequestMapping(value = "/user/userlist", method = RequestMethod.GET)
	public String userlist(Model model) throws UserException {
		logger.debug("reach UserController$userlist...");
		List<UserM> list  = new ArrayList<UserM>();
		SearchUserForm searchUserForm = new SearchUserForm();
		list = userMService.getAllUsers();
		model.addAttribute(searchUserForm);
		model.addAttribute("userList", list);
		return "userlist";
	}
	
	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public String listsearchUser(@ModelAttribute("searchUserForm") SearchUserForm searchUserForm, Model model) throws UserException {
		List<UserM> list  = new ArrayList<UserM>();
		if (StringUtils.isNotEmpty(searchUserForm.getUsername())) {
			list = userMService.searchUserByUserName(searchUserForm.getUsername().trim());
		} else {
			list = userMService.getAllUsers();
		}
		model.addAttribute(searchUserForm);
		model.addAttribute("userList", list);
		return "userlist";
	}
	@RequestMapping(value = "/user/search", method = RequestMethod.POST)
	public String postsearchUser(@ModelAttribute("searchUserForm") SearchUserForm searchUserForm, Model model) throws UserException {
		List<UserM> list  = new ArrayList<UserM>();
		if (StringUtils.isNotEmpty(searchUserForm.getUsername())) {
			list = userMService.searchUserByUserName(searchUserForm.getUsername().trim());
		} else {
			list = userMService.getAllUsers();
		}
		model.addAttribute(searchUserForm);
		model.addAttribute("userList", list);
		return "userlist";
	}
	

	@RequestMapping(value = "/user/usermod", method = RequestMethod.GET)
	public String createuser(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach UserController$createuser...");
		UserForm form = new UserForm();
		String userid = request.getParameter("id");
		if (StringUtils.isNotEmpty(userid) && CommonUtils.isNumeric(userid.trim())) {
			// Get user and popular this user to form
			UserM user = userMService.getUserByUserId(new Integer(userid));
			if (user != null) {
				form.setId(user.getId());
				form.setUsername(user.getUsername());
				form.setEmailCode(user.getEmailCode());
				form.setUserPlace(user.getUserPlace());
				form.setFullname(user.getFullname());
				form.setIsActived(user.getIsActived() != null && user.getIsActived() == ParameterApplication.ACTIVE.getStatus());
				form.setBranchId(user.getBranchId());
				form.setProductId(user.getProductId());
			
				UserDeptA userDept = userMService.getUserDeptByUser(user);
				if (userDept != null && userDept.getDept() != null) {
					form.setDepartment(userDept.getDept().getDeptcode());
				}
				
				UserLevelA userLevelA = userMService.getUserLevelByUser(user);
				if (userLevelA != null && userLevelA.getLevel() != null) {
					form.setUserLevel(userLevelA.getLevel().getLevelcode());
				}
			}
		}
		
		form.setDepartments(userMService.loadAllDepartments());
		form.setUserLevels(userMService.loadAllUserLevels());
		form.setBranchs(userMService.loadAllBranchs());
		if(form.getBranchId() != null) {
			form.setProducts(userMService.loadAllProductsByBranchId(form.getBranchId()));
		} else {
			if(form.getBranchs() != null && form.getBranchs().size() > 0){
				Branch firstBranch = form.getBranchs().get(0);
				form.setProducts(userMService.loadAllProductsByBranchId(firstBranch.getId()));
			}
		}
		model.addAttribute(form);
		return "usermod";
	}
	
	@RequestMapping(value = "/user/usermod", method = RequestMethod.POST)
	public String createuserSubmit(@Valid UserForm form, BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response)  throws Exception
	{
		logger.debug("reach UserController$createuserSubmit...");
		UserM user = null;
		boolean isCreate = false;
		if (StringUtils.isEmpty(form.getUsername()) || StringUtils.isEmpty(form.getEmailCode())) {
			result.reject("user.emptyrequirefields", "Please input required fields !");
			form.setDepartments(userMService.loadAllDepartments());
			form.setUserLevels(userMService.loadAllUserLevels());
			form.setBranchs(userMService.loadAllBranchs());
			if(form.getBranchId() != null) {
				form.setProducts(userMService.loadAllProductsByBranchId(form.getBranchId()));
			}
			model.addAttribute(form);
			return "usermod";
		}
		
		if (form.getId() != null && form.getId() > 0) {
			user = userMService.getUserByUserId(form.getId());	
		} else {
			user = userMService.getUserByUserName(form.getUsername());
			
			if (user != null) {
				result.reject("user.notexisted", "User " + form.getUsername() + " existed !");
				form.setDepartments(userMService.loadAllDepartments());
				form.setUserLevels(userMService.loadAllUserLevels());
				form.setBranchs(userMService.loadAllBranchs());
				if(form.getBranchId() != null) {
					form.setProducts(userMService.loadAllProductsByBranchId(form.getBranchId()));
				}
				model.addAttribute(form);
				return "usermod";
			}
		}
		if (user == null) {
			isCreate = true;
			user = new UserM();
			user.setPassword(MD5.encode(Constant.UTILITY_INIT_PASS));
			user.setCreatedDate(Calendar.getInstance().getTime());
			user.setCreatedBy(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			//Send email to user 
			if (MailUtil.isValidEmailAddress(form.getEmailCode())) {
				MailUtil.sendMail(form.getEmailCode(), String.format(Constant.RESET_PWD_CONTENT, form.getFullname(), form.getUsername(), Constant.UTILITY_INIT_PASS) + Constant.AUTOGENERATED_EMAIL_CONTENT, Constant.RESET_MAIL_HEADER);
			}
		}
		
		user.setEmailCode(form.getEmailCode());
		user.setUsername(form.getUsername());
		user.setUserPlace(form.getUserPlace());
		user.setFullname(form.getFullname());
		user.setIsActived(form.getIsActived() ? ParameterApplication.ACTIVE.getStatus() : ParameterApplication.NOACTIVE.getStatus());
		user.setBranchId(form.getBranchId() == 0 ? null :  form.getBranchId());
		user.setProductId(form.getProductId() == 0 ? null : form.getProductId());
		user.setLastModifiedDate(Calendar.getInstance().getTime());
		user.setLastModifiedBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		userMService.saveOrUpdate(user, form.getDepartment(), form.getUserLevel());
		
		//Save user log 
		UserLog userlog= new UserLog();
    	userlog.setLogDate(new Date());
    	userlog.setUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());    	
    	userlog.setRemoteIP(request.getRemoteAddr());
    	userlog.setSession(request.getSession().getId());    	
    	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
    	userlog.setActivity(isCreate ? ACTIONS.CREATE_USER.toString() : ACTIONS.EDIT_USER.toString());
    	userlog.setInput("Username: " + user.getUsername());
    	//userlog.setOutput(Constants.SUCCESS_RESET_PW);
    	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
    	//userlog.setStatusNote("old pw:" + oldPwd + "; new pw: " + newPwd);
    	userLogService.save(userlog);
		return "redirect:/user/userlist.html";
	}
	
	@RequestMapping(value = "/user/userdel", method = RequestMethod.GET)
	public String userdel(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("reach UserController$userdel...");
		String userid = request.getParameter("id");
		String isActived = request.getParameter("isActived");
		if (StringUtils.isNotEmpty(userid) && CommonUtils.isNumeric(userid.trim())
			&&  StringUtils.isNotEmpty(isActived) && CommonUtils.isNumeric(isActived.trim())) {
			UserM user = userMService.getUserByUserId(new Integer(userid));
			user.setIsActived(new Integer(isActived.equalsIgnoreCase("1") ? "0" : "1"));
			user.setLastModifiedDate(Calendar.getInstance().getTime());
			user.setLastModifiedBy(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			userMService.saveOrUpdate(user);
			
			//Save user log 
			UserLog userlog= new UserLog();
	    	userlog.setLogDate(new Date());
	    	userlog.setUsername(SecurityContextHolder.getContext()
					.getAuthentication().getName());    	
	    	userlog.setRemoteIP(request.getRemoteAddr());
	    	userlog.setSession(request.getSession().getId());    	
	    	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
	    	userlog.setActivity(ACTIONS.DEACTIVE_USER.toString());
	    	userlog.setInput("Username: " + user.getUsername());
	    	//userlog.setOutput(Constants.SUCCESS_RESET_PW);
	    	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
	    	//userlog.setStatusNote("old pw:" + oldPwd + "; new pw: " + newPwd);
	    	userLogService.save(userlog);
		}
		return "redirect:/user/userlist.html";
	}
	
	@RequestMapping(value = "/user/resetpwd", method = RequestMethod.GET)
	public String userResetPasswd(Model model, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, UserException
	{
		logger.debug("reach UserController$userResetPasswd...");
		String userid = request.getParameter("id");
		if (StringUtils.isNotEmpty(userid) && CommonUtils.isNumeric(userid.trim())) {
			// Get user and popular this user to form
			UserM user = userMService.getUserByUserId(new Integer(userid));
			if (user != null) {
				String email = user.getEmailCode();
				String username = user.getUsername();
				String oldPwd = user.getPassword();
				String pnewPwd = Constant.UTILITY_INIT_PASS;//PasswordUtil.getRandomString(10);//Constant.UTILITY_INIT_PASS
				String newPwd = MD5.encode(pnewPwd);//encode pw before save
				user.setPassword(newPwd);
				user.setLastChangedPw(null);
				user.setIsActived(1);
				user.setLastModifiedDate(Calendar.getInstance().getTime());
				user.setLastModifiedBy(SecurityContextHolder.getContext()
						.getAuthentication().getName());
				userMService.saveOrUpdate(user);
				
				//Save user log 
				UserLog userlog= new UserLog();
		    	userlog.setLogDate(new Date());
		    	userlog.setUsername(SecurityContextHolder.getContext()
						.getAuthentication().getName());    	
		    	userlog.setRemoteIP(request.getRemoteAddr());
		    	userlog.setSession(request.getSession().getId());    	
		    	userlog.setLogType(LOG_TYPE.AUTHENTICATION.toString());
		    	userlog.setActivity(ACTIONS.RESET_PASSWORD.toString());
		    	userlog.setInput("Username: " + user.getUsername());
		    	userlog.setOutput(Constants.SUCCESS_RESET_PW);
		    	userlog.setStatus(LOG_STATUS.SUCCESS.toString());
		    	userlog.setStatusNote("old pw:" + oldPwd + "; new pw: " + newPwd);
		    	userLogService.save(userlog);
				
				//Send email to user 
				if (MailUtil.isValidEmailAddress(email)) {
					if (StringUtils.isNotEmpty(email)) {
						user = userMService.findUser(email, username);
						if (user != null && StringUtils.isNotEmpty(user.getUsername())) {
							//user.setPassword(MD5.encode(newPwd));
							//userMService.saveOrUpdate(user);
							MailUtil.sendMail(email, String.format(Constant.CREATE_USER_CONTENT, user.getFullname(), user.getUsername(), pnewPwd) + Constant.AUTOGENERATED_EMAIL_CONTENT, Constant.RESET_MAIL_HEADER);
						}
					}			
				}
			}
		}
		return "redirect:/user/userlist.html";
	}
	
	@RequestMapping(value = "/user/prudentialreport", method = RequestMethod.GET)
	public ModelAndView exportExcel(@RequestParam("username") String username,@RequestParam("type") String type,Model model) throws UserException {
		List<UserM> list  = new ArrayList<UserM>();
		if (StringUtils.isNotEmpty(username)) {
			list = userMService.searchUserByUserName(username.trim());
		} else {
			list = userMService.getAllUsers();
		}
		List<ReportUserM> listReportUser=new ArrayList<ReportUserM>();
		for (int i = 0; i < list.size(); i++) {
			ReportUserM reportUserM=new ReportUserM();
			reportUserM.setUserM(list.get(i));
			UserLevelADto level= userLevelAService.findByuserid(list.get(i));
			if(level.getLevel()!=null){
				reportUserM.setLevel(level.getLevel().getLevelname());
			}
			listReportUser.add(reportUserM);
		}
		model.addAttribute("userList", list);
		return new ModelAndView("ExcelRevenueSummary","revenueData",listReportUser);
	
	}
	
	@RequestMapping(value = "/user/loadProducts", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> loadProducts(Model model,@RequestParam Integer branchId, HttpServletRequest request, HttpServletResponse response)
	{
		List<Product> list = userMService.loadAllProductsByBranchId(branchId);
		return list;
	}
	
	@RequestMapping(value = "/user/reportuser", method = RequestMethod.POST)
	public String reportUserList(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, UserException {
		

		List<UserMDTO> listCalls = userMService.getListUsers();
		String inputfile = "reports/templates/ReportUserList.jrxml";
		String type = "xls";	
		String rootpath = request.getSession().getServletContext()
				.getRealPath("/");
		String code = RandomStringUtils.randomAlphanumeric(5);
		String namefile =  "ReportUserList" + "_"	+ code;
		Map paramMap = new HashMap();
		ReportUtils.xuatReportWithCollectionDataSource(inputfile, paramMap,
				rootpath, type, namefile, listCalls);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ namefile + "." + type);
		rootpath += "reports/excel/" + namefile + "." + type;
		InputStream fileIn = null;
		try {
			fileIn = new FileInputStream(rootpath);
			IOUtils.copy(fileIn, response.getOutputStream());
			response.flushBuffer();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileIn != null) {
				fileIn.close();
			}
		}

		return userlist(model);
	}
}


