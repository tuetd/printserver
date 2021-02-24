package com.pruvn.tools.printserver.webapp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pruvn.tools.common.hibernate.finnone.lending.Channel;
import com.pruvn.tools.common.hibernate.finnone.lending.hibernate.Channel_Dtl_DAO;
import com.pruvn.tools.printserver.DepartmentDAO;
import com.pruvn.tools.printserver.DocmasterDAO;
import com.pruvn.tools.printserver.UserRoleMDAO;
import com.pruvn.tools.printserver.UserdocprinterDAO;
import com.pruvn.tools.printserver.UsermasterDAO;
import com.pruvn.tools.printserver.UserplaceDAO;
import com.pruvn.tools.printserver.pojo.Department;
import com.pruvn.tools.printserver.pojo.Docmaster;
import com.pruvn.tools.printserver.pojo.UserRoleM;
import com.pruvn.tools.printserver.pojo.Userdocprinter;
import com.pruvn.tools.printserver.pojo.Userdocprinter.UserdocprinterPK;
import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.pojo.Userplace;
import com.pruvn.tools.printserver.webapp.convertter.ConverterForm;
import com.pruvn.tools.printserver.webapp.editor.CreateUserForm;
import com.pruvn.tools.utils.ParameterApplication;
import com.pruvn.tools.utils.SHA;

@Controller
public class CreateUserController {
	private static Log logger = LogFactory.getLog(CreateUserController.class);
	private DocmasterDAO docmasterDAO;
	private Channel_Dtl_DAO channelDAO;
	private UserdocprinterDAO userdocprinterDAO;
	private UsermasterDAO usermasterDAO;
	private UserRoleMDAO userRoleMDAO;
	private UserplaceDAO userplaceDAO;
	private DepartmentDAO departmentDAO;
	private Map<Integer, String> mapdocmaster;
	private Map<Integer, String> mapperdocmaster;
	private List<UserRoleM> lstrole;
	private List<Department> lstder;
	private List<Userplace> lstplace;
	private List<Channel> lstchannel;
	private Map<Integer, String> mapchannel;
	private Map<Integer, String> mapperchannel;
	private String status = "";
	private int idusermaster = 0;
	private ConverterForm convertterform = new ConverterForm();

	private Integer getPkFromRequest(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("usermasterId"));
	}

	@RequestMapping(value = "createUserForm.html", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		CreateUserForm userform = null;
		mapdocmaster = new HashMap<Integer, String>();
		mapperdocmaster = new HashMap<Integer, String>();
		mapchannel = new HashMap<Integer, String>();
		mapperchannel = new HashMap<Integer, String>();
		for (Docmaster docmaster : docmasterDAO.findAll()) {
			mapdocmaster.put(docmaster.getId(), docmaster.getName());
		}
		for (Channel channel : channelDAO.findAll()) {
			mapchannel.put(channel.getChannelId(), channel.getChannelName());
		}
		lstrole = userRoleMDAO.findAll();
		lstplace = userplaceDAO.findAll();
		lstder = departmentDAO.findAll();
		
		Usermaster useradmin =usermasterDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if(useradmin.getRoleId()!=6){
			lstrole.remove(5);
		}
		
		if (request.getParameter("usermasterId") != null) {
			Usermaster usermaster = usermasterDAO
					.getById(getPkFromRequest(request));
			List<Userdocprinter> lstPerbyUser = userdocprinterDAO
					.findByUserid(usermaster.getId());
			if(usermaster.getUserChannel()!=null && usermaster.getUserChannel().length()>0)
			{
				String channelArr[] = usermaster.getUserChannel().split(",");
				List<String> channelList = Arrays.asList(channelArr); 
				removeListChannelMap(channelList, mapchannel);
				addListChannelMapper(channelList, mapperchannel);
			}
			 
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
		model.addAttribute("mapchannel", mapchannel);
		model.addAttribute("mapperchannel", mapperchannel);
		model.addAttribute("userform", userform);
		return "usermanager/createUserForm";
	}

	private void addListPerDocmaseter(List<Userdocprinter> lstPerbyUser,
			Map<Integer, String> mappre) {
		for (Userdocprinter userdocprinter : lstPerbyUser) {
			Docmaster dos = docmasterDAO.getById(userdocprinter
					.getUserdocprinterPK().getDocid());
			mappre.put(dos.getId(), dos.getName());
		}
	}
	
	private void addListChannelMapper(List<String> lstChannel,
			Map<Integer, String> mappre) {
		for (int i = 0; i<lstChannel.size(); i++) {
			Channel channel = channelDAO.getById(Integer.parseInt(lstChannel.get(i)));
			mappre.put(channel.getChannelId(), channel.getChannelName());
		}
	}

	private void removeListDocmaster(List<Userdocprinter> lstUserdoc,
			Map<Integer, String> lstdoc) {
		@SuppressWarnings("rawtypes")
		Iterator iterator = lstdoc.keySet().iterator();
		while (iterator.hasNext()) {
			int idper = (Integer) iterator.next();
			for (int i = 0; i < lstUserdoc.size(); i++) {
				if (idper == lstUserdoc.get(i).getUserdocprinterPK().getDocid()) {
					iterator.remove();
				}
			}
		}

	}
	
	private void removeListChannelMap(List<String> lstChannel,
			Map<Integer, String> lstdoc) {
		@SuppressWarnings("rawtypes")
		Iterator iterator = lstdoc.keySet().iterator();
		while (iterator.hasNext()) {
			int idChannelMap = (Integer) iterator.next();
			for (int i = 0; i < lstChannel.size(); i++) {
				if (idChannelMap == Integer.parseInt(lstChannel.get(i))) {
					iterator.remove();
				}
			}
		}

	}

	private void removeListDocmasterCurrent(Map<Integer, String> lstper,
			Map<Integer, String> lstdoc) {
		@SuppressWarnings("rawtypes")
		Iterator iteratordoc = lstdoc.keySet().iterator();
		while (iteratordoc.hasNext()) {
			int iddoc = (Integer) iteratordoc.next();
			@SuppressWarnings("rawtypes")
			Iterator iteratorperdoc = lstper.keySet().iterator();
			while (iteratorperdoc.hasNext()) {
				int idperdoc = (Integer) iteratorperdoc.next();
				if (iddoc == idperdoc) {
					logger.info("iddoc"+iddoc);
					iteratordoc.remove();
				}
				logger.info("lstdoc.size() size:====>"+lstdoc.size());
			}

		}
	}

	@Transactional
	@RequestMapping(value = "/createUserForm.html", method = RequestMethod.POST)
	public String createUser(
			HttpServletRequest request,
			@RequestParam(value = "lsthidden", required = false) String[] idlst,
			@RequestParam(value = "lstChannelHidden", required = false) String[] lstChannelHidden,
			@ModelAttribute("userform") CreateUserForm userform,
			BindingResult result, Model model) {
		String url = "usermanager/createUserForm";
		String channellist = request.getParameter("lstChannelHidden");
		channellist = channellist.replaceAll("--,", "");
		channellist = channellist.replaceAll("--", "");		
//		channellist = channellist.substring(3, channellist.length());
		Usermaster usermaster = usermasterDAO.findByUsername(userform
				.getUsername());
		if (usermaster != null && status.equals("create")) {
			result.rejectValue("username", "user.valid.username");
			model.addAttribute("mapdocmaster", mapdocmaster);
			model.addAttribute("mapperdocmaster", mapperdocmaster);
			model.addAttribute("mapchannel", mapchannel);
			model.addAttribute("mapperchannel", mapperchannel);
			model.addAttribute("userform", userform);
			model.addAttribute("lstrole", lstrole);
			model.addAttribute("lstplace", lstplace);
			model.addAttribute("lstder", lstder);
			return url;
		} else {
			mapperdocmaster = new HashMap<Integer, String>();
			mapperchannel = new HashMap<Integer, String>();
			Usermaster create = new Usermaster();			
			if (idusermaster != 0) {
				Usermaster usermaster1 = usermasterDAO.getById(idusermaster);
				userform.setId(usermaster1.getId());
				userform.setUsername(usermaster1.getUsername());
				List<Userdocprinter> lstPerbyUser = userdocprinterDAO
						.findByUserid(userform.getId());
				for (Userdocprinter userdocprinter : lstPerbyUser) {
					userdocprinterDAO.delete(userdocprinter);
				}

				create = convertterform.conUsermaster(userform);
				create.setPassword(usermaster1.getPassword());
//				if( channellist !="" && channellist.length()>0)
//				{
				create.setUserChannel(channellist);
//				}
				usermasterDAO.update(create);
				model.addAttribute("message",
						"The Application has been successfully update!");
				model.addAttribute("backUrl", "createUserForm.html");
				url = "usermanager/common_success";
			} else {
				create = convertterform.conUsermaster(userform);
//				if( channellist !="" && channellist.length()>0)
//				{
					create.setUserChannel(channellist);
//				}
				usermasterDAO.save(create);
				result.rejectValue("username", "user.valid.create");
			}

			for (int i = 0; i < idlst.length; i++) {
				if (!idlst[i].equals("--")) {
					Docmaster docmasper = docmasterDAO.getById(Integer
							.parseInt(idlst[i]));
					mapperdocmaster.put(docmasper.getId(), docmasper.getName());
				}
			}

			if (!mapperdocmaster.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Iterator iterator = mapperdocmaster.keySet().iterator();
				while (iterator.hasNext()) {
					int idper = (Integer) iterator.next();
					UserdocprinterPK userdocprintpk = new UserdocprinterPK();
					userdocprintpk.setDocid(idper);
					userdocprintpk.setUserid(create.getId());
					Userdocprinter userdocprint = new Userdocprinter();
					userdocprint.setUserdocprinterPK(userdocprintpk);
					userdocprinterDAO.save(userdocprint);
				}
			}
			removeListDocmasterCurrent(mapperdocmaster,mapdocmaster);
			model.addAttribute("mapdocmaster", mapdocmaster);
			model.addAttribute("mapperdocmaster", mapperdocmaster);
			
			model.addAttribute("mapchannel", mapchannel);
			model.addAttribute("mapperchannel", mapperchannel);
			model.addAttribute("userform", userform);
			model.addAttribute("lstrole", lstrole);
			model.addAttribute("lstplace", lstplace);
			model.addAttribute("lstder", lstder);
			return url;
		}

	}

	@RequestMapping(value = "user/validateuser.html", method = RequestMethod.POST)
	public @ResponseBody
	String valiUser(
			@RequestParam(value = "username", required = false) String username,
			HttpServletRequest request,Model model) {
		
		String result="";
		
		if (StringUtils.isEmpty(username)) {
			result = "Please input username";
			return result;
		}
		
		Usermaster usermaster = usermasterDAO.findByUsername(username);
		if (usermaster != null) {
			model.addAttribute("usermaster", usermaster);
			result = "User existed";
		}else{
			result = "Ok to create";
		}
		model.addAttribute("usermaster", usermaster);
		return result;
	}
	
	@RequestMapping(value = "/resetpassword.html", method = RequestMethod.GET)
	public String resetPassUser( @RequestParam("id") Integer id,HttpServletRequest request,Model model) {
		Usermaster usermaster = usermasterDAO.getById(id);
		if (usermaster != null) {
			usermaster.setPassword(SHA.encode("abc123"));
			usermaster.setLastChangedPw(null);
			usermaster.setCountlogintemp(null);
			usermaster.setDatelogintemp(null);
			usermaster.setLastlogindate(new Date());
			usermasterDAO.update(usermaster);
			model.addAttribute("message","Update password success!");
		}
		model.addAttribute("usermaster", usermaster);
		model.addAttribute("Usermasterlist", usermasterDAO.findAll());
		return "usermasterList";
	}
	@RequestMapping(value = "/unlockuser.html", method = RequestMethod.GET)
	public String unlockUser( @RequestParam("id") Integer id,HttpServletRequest request,Model model) {
		Usermaster usermaster = usermasterDAO.getById(id);
		if (usermaster != null) {
			usermaster.setStatus(ParameterApplication.NOACTIVE.getStatus());
			usermaster.setCountlogintemp(null);
			usermaster.setDatelogintemp(null);
			usermaster.setReasonlock(null);
			usermaster.setLastlogindate(new Date());
			usermasterDAO.update(usermaster);
			model.addAttribute("message","Unlock user success!");
		}
		model.addAttribute("usermaster", usermaster);
		model.addAttribute("Usermasterlist", usermasterDAO.findAll());
		return "usermasterList";
	}
	@RequestMapping(value = "/deleteuser.html", method = RequestMethod.GET)
	public String deleteUser( @RequestParam("id") Integer id,HttpServletRequest request,Model model) {
		Usermaster usermaster = usermasterDAO.getById(id);
		if (usermaster != null) {
			usermaster.setStatus(ParameterApplication.DELETE.getStatus());
			usermaster.setUsername(usermaster.getUsername()+"-deleted");
			usermaster.setCountlogintemp(null);
			usermaster.setDatelogintemp(null);
			usermasterDAO.update(usermaster);
			model.addAttribute("message","Delete user success!");
		}
		model.addAttribute("usermaster", usermaster);
		model.addAttribute("Usermasterlist", usermasterDAO.findAll());
		return "usermasterList";
	}
	
	

	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}

	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	public DocmasterDAO getDocmasterDAO() {
		return docmasterDAO;
	}

	@Autowired
	public void setDocmasterDAO(DocmasterDAO docmasterDAO) {
		this.docmasterDAO = docmasterDAO;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		CreateUserController.logger = logger;
	}

	public UserdocprinterDAO getUserdocprinterDAO() {
		return userdocprinterDAO;
	}

	@Autowired
	public void setUserdocprinterDAO(UserdocprinterDAO userdocprinterDAO) {
		this.userdocprinterDAO = userdocprinterDAO;
	}

	public ConverterForm getConvertterform() {
		return convertterform;
	}

	public void setConvertterform(ConverterForm convertterform) {
		this.convertterform = convertterform;
	}

	public List<UserRoleM> getLstrole() {
		return lstrole;
	}

	public void setLstrole(List<UserRoleM> lstrole) {
		this.lstrole = lstrole;
	}

	public UserRoleMDAO getUserRoleMDAO() {
		return userRoleMDAO;
	}

	@Autowired
	public void setUserRoleMDAO(UserRoleMDAO userRoleMDAO) {
		this.userRoleMDAO = userRoleMDAO;
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

	public UserplaceDAO getUserplaceDAO() {
		return userplaceDAO;
	}

	@Autowired
	public void setUserplaceDAO(UserplaceDAO userplaceDAO) {
		this.userplaceDAO = userplaceDAO;
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	@Autowired
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdusermaster() {
		return idusermaster;
	}

	public void setIdusermaster(int idusermaster) {
		this.idusermaster = idusermaster;
	}

	public Map<Integer, String> getMapdocmaster() {
		return mapdocmaster;
	}

	public void setMapdocmaster(Map<Integer, String> mapdocmaster) {
		this.mapdocmaster = mapdocmaster;
	}

	public Channel_Dtl_DAO getChannelDAO() {
		return channelDAO;
	}

	@Autowired
	public void setChannelDAO(Channel_Dtl_DAO channelDAO) {
		this.channelDAO = channelDAO;
	}

	public List<Channel> getLstchannel() {
		return lstchannel;
	}

	public void setLstchannel(List<Channel> lstchannel) {
		this.lstchannel = lstchannel;
	}

	public Map<Integer, String> getMapperdocmaster() {
		return mapperdocmaster;
	}

	public void setMapperdocmaster(Map<Integer, String> mapperdocmaster) {
		this.mapperdocmaster = mapperdocmaster;
	}

	public Map<Integer, String> getMapchannel() {
		return mapchannel;
	}

	public void setMapchannel(Map<Integer, String> mapchannel) {
		this.mapchannel = mapchannel;
	}

	public Map<Integer, String> getMapperchannel() {
		return mapperchannel;
	}

	public void setMapperchannel(Map<Integer, String> mapperchannel) {
		this.mapperchannel = mapperchannel;
	}

}
