/*
 * Java bean class for entity table user_level_a 
 * Created on 17 May 2013 ( Time 17:03:25 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.UserLevelA;
import com.pruvn.rms.domain.UserLevelM;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.dto.UserLevelADto;
import com.pruvn.rms.service.abstracts.GenericService;


/**
 * Service interface for table "user_level_a"
 * 
 * @author Telosys Tools Generator
 *
 */
public interface UserLevelAService extends GenericService<UserLevelA, Integer> 
{
    

	/**
	 * Find UserLevelA by username
	 */
	public UserLevelADto findByuserid(UserM userid);
	/**
	 * Find UserLevelA by username
	 */
	public List<UserLevelADto> findBylevelid(UserLevelM levelid);
}