/*
 * Java bean class for entity table RM_SCREEN 
 * Created on 3 Oct 2013 ( Time 12:18:01 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.Screen;
import com.pruvn.rms.service.abstracts.GenericService;


/**
 * Service interface for table "RM_SCREEN"
 * 
 * @author Telosys Tools Generator
 *
 */
public interface ScreenService extends GenericService<Screen, Integer>
{
    

	/**
	 * Find RmScreen by code
	 */
	public Screen findByStage(String code);
	/**
	 * Find RmScreen by name
	 */
	public List<Screen> findByname(String name);
}