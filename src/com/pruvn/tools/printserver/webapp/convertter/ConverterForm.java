package com.pruvn.tools.printserver.webapp.convertter;

import java.util.Date;

import com.pruvn.tools.printserver.pojo.Usermaster;
import com.pruvn.tools.printserver.webapp.editor.CreateUserForm;
import com.pruvn.tools.utils.ParameterApplication;
import com.pruvn.tools.utils.SHA;

public class ConverterForm {
//convert form CreateuserForm --> usermaster
	public Usermaster conUsermaster(CreateUserForm create){
		Usermaster usermaster=new Usermaster();
		usermaster.setId(create.getId());
		usermaster.setUsername(create.getUsername());
		usermaster.setRoleId(create.getUserrole());
		usermaster.setPassword(SHA.encode("abc123"));
		usermaster.setFinnoneSecurityCode(create.getUsercode());
		usermaster.setUserPlace(create.getUserplace());
		usermaster.setEmailCode(create.getEmail());
		usermaster.setNameFull(create.getUserfullname());
		usermaster.setDepartment(create.getDepartment());
		usermaster.setUserCodeId(create.getUsersecid());
		usermaster.setLoggedin("N");
		usermaster.setLastlogindate(new Date());
		usermaster.setStatus(ParameterApplication.NOACTIVE.getStatus());
		return usermaster;
	}

	public CreateUserForm conCreateUserForm(Usermaster usermaster) {
		// TODO Auto-generated method stub
		CreateUserForm createUserForm = new CreateUserForm();
		createUserForm.setUsername(usermaster.getUsername());
		createUserForm.setEmail(usermaster.getEmailCode());
		createUserForm.setUserfullname(usermaster.getNameFull());
		createUserForm.setUserplace(usermaster.getUserPlace());
		createUserForm.setUsersecid(usermaster.getUserCodeId());
		createUserForm.setUsercode(usermaster.getFinnoneSecurityCode());
		createUserForm.setDepartment(usermaster.getDepartment());
		createUserForm.setUserrole(usermaster.getRoleId());
		createUserForm.setId(usermaster.getId());
		return createUserForm;
		
	}
}
