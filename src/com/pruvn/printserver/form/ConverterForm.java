package com.pruvn.printserver.form;

import java.util.Date;

import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.utils.ParameterApplication;
import com.pruvn.printserver.utils.SHA;


public class ConverterForm {
//convert form CreateuserForm --> usermaster
	public Usermaster conUsermaster(CreateUserForm create){
		Usermaster usermaster=new Usermaster();
		usermaster.setId(create.getId());
		usermaster.setUsername(create.getUsername());
		usermaster.setRole_id(create.getUserrole());
		usermaster.setPassword(SHA.encode("abc123"));
		usermaster.setFinnone_security_code(create.getUsercode());
		usermaster.setUser_place(create.getUserplace());
		usermaster.setEmail_code(create.getEmail());
		usermaster.setFullname(create.getUserfullname());
		usermaster.setDepartment(create.getDepartment());
		usermaster.setUsercodeid(create.getUsersecid());
		usermaster.setLoggedin("N");
		usermaster.setLastlogindate(new Date());
		usermaster.setStatus(ParameterApplication.NOACTIVE.getStatus());
		return usermaster;
	}

	public CreateUserForm conCreateUserForm(Usermaster usermaster) {
		// TODO Auto-generated method stub
		CreateUserForm createUserForm = new CreateUserForm();
		createUserForm.setUsername(usermaster.getUsername());
		createUserForm.setEmail(usermaster.getEmail_code());
		createUserForm.setUserfullname(usermaster.getFullname());
		createUserForm.setUserplace(usermaster.getUser_place());
		createUserForm.setUsersecid(usermaster.getUsercodeid());
		createUserForm.setUsercode(usermaster.getFinnone_security_code());
		createUserForm.setDepartment(usermaster.getDepartment());
		createUserForm.setUserrole(usermaster.getRole_id());
		createUserForm.setId(usermaster.getId());
		return createUserForm;
		
	}
}
