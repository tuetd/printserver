/*
 * Java bean class for entity table RM_SCREEN 
 * Created on 3 Oct 2013 ( Time 12:18:01 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.dao;

import java.util.List;

import com.pruvn.rms.domain.Insurance;


/**
 * Data Access Object interface for table "RM_INSURANCE"
 * 
 * @author Telosys Tools Generator
 *
 */

public interface InsuranceDao extends GenericDAO<Insurance,Integer> 
{

	Insurance findByLoanNo(String loanNo);
    
	List<Insurance> searchByLoanNo(String loanNo);
}


