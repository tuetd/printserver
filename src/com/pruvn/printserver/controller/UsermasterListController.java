package com.pruvn.printserver.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruvn.printserver.dao.DepartmentDAO;
import com.pruvn.printserver.dao.DocmasterDAO;
import com.pruvn.printserver.dao.UserDocPrinterDAO;
import com.pruvn.printserver.dao.UserRoleMDAO;
import com.pruvn.printserver.dao.UserplaceDAO;
import com.pruvn.printserver.entity.Department;
import com.pruvn.printserver.entity.Docmaster;
import com.pruvn.printserver.entity.UserDocPrinter;
import com.pruvn.printserver.entity.UserDocPrinter.UserdocprinterPK;
import com.pruvn.printserver.entity.UserRoleM;
import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.entity.Userplace;
import com.pruvn.printserver.form.ChangePwdForm;
import com.pruvn.printserver.form.ConverterForm;
import com.pruvn.printserver.form.CreateUserForm;
import com.pruvn.printserver.services.UsermasterService;
import com.pruvn.printserver.utils.ParameterApplication;
import com.pruvn.printserver.utils.SHA;


/**
 * <p>
 * Spring controller to diplay list of Usermasters
 * </p>
 * <p>
 * Generated at Thu Jul 19 10:40:45 ICT 2012
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Generate a complete Spring/Hibernate and
 *         Spring MVC webapp
 */
@Controller
public class UsermasterListController  {
	@Autowired
	private UsermasterService usermasterService;
	@Autowired
	private DocmasterDAO docmasterDAO;
	@Autowired
	private UserDocPrinterDAO userDocPrinterDAO;
	@Autowired
	private UserRoleMDAO userRoleMDAO;
	@Autowired
	private UserplaceDAO userplaceDAO;
	@Autowired
	private DepartmentDAO departmentDAO;
	
	private Map<Long, String> mapdocmaster;
	private Map<Long, String> mapperdocmaster;
	private List<UserRoleM> lstrole;
	private List<Department> lstder;
	private List<Userplace> lstplace;
	private String status = "";
	private int idusermaster = 0;
	private ConverterForm convertterform = new ConverterForm();
	
	
	public DocmasterDAO getDocmasterDAO() {
		return docmasterDAO;
	}

	public void setDocmasterDAO(DocmasterDAO docmasterDAO) {
		this.docmasterDAO = docmasterDAO;
	}


	public UserDocPrinterDAO getUserDocPrinterDAO() {
		return userDocPrinterDAO;
	}

	public void setUserDocPrinterDAO(UserDocPrinterDAO userDocPrinterDAO) {
		this.userDocPrinterDAO = userDocPrinterDAO;
	}

	public UserRoleMDAO getUserRoleMDAO() {
		return userRoleMDAO;
	}

	public void setUserRoleMDAO(UserRoleMDAO userRoleMDAO) {
		this.userRoleMDAO = userRoleMDAO;
	}

	public UserplaceDAO getUserplaceDAO() {
		return userplaceDAO;
	}

	public void setUserplaceDAO(UserplaceDAO userplaceDAO) {
		this.userplaceDAO = userplaceDAO;
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}


	
	public UsermasterService getUsermasterService() {
		return usermasterService;
	}

	public void setUsermasterService(UsermasterService usermasterService) {
		this.usermasterService = usermasterService;
	}




	@RequestMapping(value="listUsermaster.html",method=RequestMethod.GET)
	public String listUsermaster(Model model){
			Usermaster usermaster = new Usermaster();
			model.addAttribute("Usermasterlist", usermasterService.findAll());
			model.addAttribute("usermaster", usermaster);
		return "usermanager/usermasterList";
	}
	@RequestMapping(value="searchUsermaster.html",method=RequestMethod.POST)
	public String searchUsermaster(@ModelAttribute("usermaster") Usermaster usermaster,Model model,HttpServletRequest request,HttpServletResponse response){
		List<Usermaster> lst=usermasterService.findByUsername(usermaster.getUsername());
		model.addAttribute("Usermasterlist", lst);
		model.addAttribute("usermaster", usermaster);
		return "usermanager/usermasterList";
	}
	
	
	@RequestMapping(value = "createUserForm.html", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) throws UserException {
		CreateUserForm userform = null;
		mapdocmaster = new HashMap<Long, String>();
		mapperdocmaster = new HashMap<Long, String>();
		for (Docmaster docmaster : docmasterDAO.findAll()) {
			mapdocmaster.put(docmaster.getId(), docmaster.getName_doc());
		}
		
		lstrole = userRoleMDAO.findAll();
		lstplace = userplaceDAO.findAll();
		lstder = departmentDAO.findAll();
		
		Usermaster useradmin =usermasterService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		if(useradmin.getRole_id()!=6){
			lstrole.remove(5);
		}
		
		if (request.getParameter("usermasterId") != null) {
			Usermaster usermaster = usermasterService.findById(getPkFromRequest(request));
			List<UserDocPrinter> lstPerbyUser = userDocPrinterDAO.findByUserid(usermaster.getId());
			if (!lstPerbyUser.isEmpty()) {
				removeListDocmaster(lstPerbyUser, mapdocmaster);
				addListPerDocmaseter(lstPerbyUser, mapperdocmaster);
			}
			userform = convertterform.conCreateUserForm(usermaster);
			idusermaster = Integer.parseInt(request
					.getParameter("usermasterId"));
			status = "update";
		} else {
			userform = new CreateUserForm();
			idusermaster = 0;
			status = "create";
		}
		model.addAttribute("mapdocmaster", mapdocmaster);
		model.addAttribute("mapperdocmaster", mapperdocmaster);
		model.addAttribute("lstrole", lstrole);
		model.addAttribute("lstplace", lstplace);
		model.addAttribute("lstder", lstder);
		model.addAttribute("userform", userform);
		return "usermanager/createUserForm";
	}
	
	
	@Transactional
	@RequestMapping(value = "/createUserForm.html", method = RequestMethod.POST)
	public String createUser(
			HttpServletRequest request,
			@RequestParam(value = "lsthidden", required = false) String[] idlst,
			@RequestParam(value = "lstChannelHidden", required = false) String[] lstChannelHidden,
			@ModelAttribute("userform") CreateUserForm userform,
			BindingResult result, Model model) throws UserException {
		String url = "usermanager/createUserForm";
		String channellist = request.getParameter("lstChannelHidden");
		channellist = channellist.replaceAll("--,", "");
		channellist = channellist.replaceAll("--", "");		
		Usermaster usermaster = usermasterService.getUserByUserName(userform
				.getUsername());
		if (usermaster != null && status.equals("create")) {
			result.rejectValue("username", "user.valid.username");
			model.addAttribute("mapdocmaster", mapdocmaster);
			model.addAttribute("mapperdocmaster", mapperdocmaster);
			model.addAttribute("userform", userform);
			model.addAttribute("lstrole", lstrole);
			model.addAttribute("lstplace", lstplace);
			model.addAttribute("lstder", lstder);
			return url;
		} else {
			mapperdocmaster = new HashMap<Long, String>();
			Usermaster create = new Usermaster();			
			if (idusermaster != 0) {
				Usermaster usermaster1 = usermasterService.findById((long)idusermaster);
				userform.setId(usermaster1.getId());
				userform.setUsername(usermaster1.getUsername());
				List<UserDocPrinter> lstPerbyUser = userDocPrinterDAO
						.findByUserid(userform.getId());
				for (UserDocPrinter userdocprinter : lstPerbyUser) {
					userDocPrinterDAO.delete(userdocprinter);
				}

				create = convertterform.conUsermaster(userform);
				create.setPassword(usermaster1.getPassword());
				usermasterService.update(create);
				model.addAttribute("message",
						"The Application has been successfully update!");
				model.addAttribute("backUrl", "createUserForm.html");
				url = "usermanager/common_success";
			} else {
				create = convertterform.conUsermaster(userform);
				usermasterService.save(create);
				result.rejectValue("username", "user.valid.create");
			}

			for (int i = 0; i < idlst.length; i++) {
				if (!idlst[i].equals("--")) {
					Docmaster docmasper = docmasterDAO.findById(Long.parseLong(idlst[i]));
					mapperdocmaster.put(docmasper.getId(), docmasper.getName_doc());
				}
			}

			if (!mapperdocmaster.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Iterator iterator = mapperdocmaster.keySet().iterator();
				while (iterator.hasNext()) {
					long idper = (Long) iterator.next();
					UserdocprinterPK userdocprintpk = new UserdocprinterPK();
					userdocprintpk.setDocid(idper);
					userdocprintpk.setUserid(create.getId());
					UserDocPrinter userdocprint = new UserDocPrinter();
					userdocprint.setUserdocprinterPK(userdocprintpk);
					userDocPrinterDAO.save(userdocprint);
				}
			}
			removeListDocmasterCurrent(mapperdocmaster,mapdocmaster);
			model.addAttribute("mapdocmaster", mapdocmaster);
			model.addAttribute("mapperdocmaster", mapperdocmaster);
			model.addAttribute("userform", userform);
			model.addAttribute("lstrole", lstrole);
			model.addAttribute("lstplace", lstplace);
			model.addAttribute("lstder", lstder);
			return url;
		}

	}
	
	
	@RequestMapping(value = "/resetpassword.html", method = RequestMethod.GET)
	public String resetPassUser( @RequestParam("id") Long id,HttpServletRequest request,Model model) {
		Usermaster usermaster = usermasterService.findById(id);
		if (usermaster != null) {
			usermaster.setPassword(SHA.encode("abc123"));
			usermaster.setLast_changed_pw(null);
			usermaster.setCount_login_temp(null);
			usermaster.setDate_login_temp(null);
			usermaster.setLastlogindate(new Date());
			usermasterService.update(usermaster);
			model.addAttribute("message","Update password success!");
		}
		model.addAttribute("usermaster", usermaster);
		model.addAttribute("Usermasterlist", usermasterService.findAll());
		return "usermanager/usermasterList";
	}
	@RequestMapping(value = "/unlockuser.html", method = RequestMethod.GET)
	public String unlockUser( @RequestParam("id") Long id,HttpServletRequest request,Model model) {
		Usermaster usermaster = usermasterService.findById(id);
		if (usermaster != null) {
			usermaster.setStatus(ParameterApplication.NOACTIVE.getStatus());
			usermaster.setCount_login_temp(null);
			usermaster.setDate_login_temp(null);
			usermaster.setReason_lock(null);
			usermaster.setLastlogindate(new Date());
			usermasterService.update(usermaster);
			model.addAttribute("message","Unlock user success!");
		}
		model.addAttribute("usermaster", usermaster);
		model.addAttribute("Usermasterlist", usermasterService.findAll());
		return "usermanager/usermasterList";
	}
	@RequestMapping(value = "/deleteuser.html", method = RequestMethod.GET)
	public String deleteUser( @RequestParam("id") Long id,HttpServletRequest request,Model model) {
		Usermaster usermaster = usermasterService.findById(id);
		if (usermaster != null) {
			usermaster.setStatus(ParameterApplication.DELETE.getStatus());
			usermaster.setUsername(usermaster.getUsername()+"-deleted");
			usermaster.setCount_login_temp(null);
			usermaster.setDate_login_temp(null);
			usermasterService.update(usermaster);
			model.addAttribute("message","Delete user success!");
		}
		model.addAttribute("usermaster", usermaster);
		model.addAttribute("Usermasterlist", usermasterService.findAll());
		return "usermanager/usermasterList";
	}
	
	
	@RequestMapping(value = "/changepwd.html",method = RequestMethod.GET)
	public String getChangePwdForm(Model model)
	{
		ChangePwdForm form = new ChangePwdForm();
		form.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute(form);
		return "usermanager/changepwd";
	}

	@RequestMapping(value = "/changepwd.html",method = RequestMethod.POST)
	public String changePwd(@Valid ChangePwdForm form, BindingResult result, Model model)
	{
		try {
			
			if (result.hasErrors())
			{
				model.addAttribute(form);
				return "usermanager/changepwd";
			}
			
			if (form.getNewPwd().equals(form.getOldPwd())) {
				result.reject("common.form.reuse.old.pwd", "New password is the same as old password !");
				model.addAttribute(form);
				return "usermanager/changepwd";
			}
			
			if (!form.getNewPwd().equals(form.getReenterredNewPwd())) {
				result.reject("common.form.notmatched", "New password not matched !");
				model.addAttribute(form);
				return "usermanager/changepwd";
			}
			
			//Do some stuffs to change password for current user
			Usermaster user = usermasterService.getUserByUserName(form.getUsername());
			String oldPwd = form.getOldPwd();
			oldPwd = SHA.encode(oldPwd);
			
			if (!user.getPassword().equals(oldPwd)) {
				result.reject("common.form.oldpwd.invalid", "Invalid old password !");
				model.addAttribute(form);
				return "usermanager/changepwd";
			}
			
			// Set new password and save it to database
			Date dateupdate= new Date();
			user.setLast_changed_pw(new Timestamp(dateupdate.getTime()));
			user.setPassword(SHA.encode(form.getNewPwd()));
			user.setCount_login_temp(null);
			user.setDate_login_temp(null);
			usermasterService.saveOrUpdate(user);
			result.reject("operation.successful.message", "Operation completed successfully !");
			
			return "usermanager/changepwd";
		} catch (Exception e) {
			result.reject("operation.checkinput.message", e.getMessage());
			return "usermanager/changepwd";
		}
	}
	
	
	
	private Long getPkFromRequest(HttpServletRequest request) {
		return Long.parseLong(request.getParameter("usermasterId"));
	}
	private void removeListDocmaster(List<UserDocPrinter> lstUserdoc,
			Map<Long, String> lstdoc) {
		@SuppressWarnings("rawtypes")
		Iterator iterator = lstdoc.keySet().iterator();
		while (iterator.hasNext()) {
			long idper = (Long) iterator.next();
			for (int i = 0; i < lstUserdoc.size(); i++) {
				if (idper == lstUserdoc.get(i).getUserdocprinterPK().getDocid()) {
					iterator.remove();
				}
			}
		}

	}
	
	private void addListPerDocmaseter(List<UserDocPrinter> lstPerbyUser,
			Map<Long, String> mappre) {
		for (UserDocPrinter userdocprinter : lstPerbyUser) {
			Docmaster dos = docmasterDAO.getById(userdocprinter
					.getUserdocprinterPK().getDocid());
			mappre.put(dos.getId(), dos.getName_doc());
		}
	}
	
	
	private void removeListDocmasterCurrent(Map<Long, String> lstper,
			Map<Long, String> lstdoc) {
		@SuppressWarnings("rawtypes")
		Iterator iteratordoc = lstdoc.keySet().iterator();
		while (iteratordoc.hasNext()) {
			long iddoc = (Long) iteratordoc.next();
			@SuppressWarnings("rawtypes")
			Iterator iteratorperdoc = lstper.keySet().iterator();
			while (iteratorperdoc.hasNext()) {
				long idperdoc = (Long) iteratorperdoc.next();
				if (iddoc == idperdoc) {
					iteratordoc.remove();
				}
			}

		}
	}

	public Map<Long, String> getMapdocmaster() {
		return mapdocmaster;
	}

	public void setMapdocmaster(Map<Long, String> mapdocmaster) {
		this.mapdocmaster = mapdocmaster;
	}

	public Map<Long, String> getMapperdocmaster() {
		return mapperdocmaster;
	}

	public void setMapperdocmaster(Map<Long, String> mapperdocmaster) {
		this.mapperdocmaster = mapperdocmaster;
	}

	public List<UserRoleM> getLstrole() {
		return lstrole;
	}

	public void setLstrole(List<UserRoleM> lstrole) {
		this.lstrole = lstrole;
	}

	public List<Department> getLstder() {
		return lstder;
	}

	public void setLstder(List<Department> lstder) {
		this.lstder = lstder;
	}

	public List<Userplace> getLstplace() {
		return lstplace;
	}

	public void setLstplace(List<Userplace> lstplace) {
		this.lstplace = lstplace;
	}

	public int getIdusermaster() {
		return idusermaster;
	}

	public void setIdusermaster(int idusermaster) {
		this.idusermaster = idusermaster;
	}

	public ConverterForm getConvertterform() {
		return convertterform;
	}

	public void setConvertterform(ConverterForm convertterform) {
		this.convertterform = convertterform;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}