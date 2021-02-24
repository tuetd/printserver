/*
 * Java bean class for entity table RM_BRANCH 
 * Created on 3 Oct 2013 ( Time 12:18:01 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.model;



/**
 * Model/Form for table "RM_BRANCH"
 * 
 * @author Telosys Tools Generator
 *
 */
public class BranchForm
{

    private Integer    id           ; // Primary Key

    private String     code         ;
    private String     name         ;
    private Boolean     isActived         ;

    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "ID" ( type "NUMBER", NotNull : true ) 
     * @param id
     */
	public void setId( Integer id )
    {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "ID" ( type "NUMBER", NotNull : true ) 
     * @return the field value
     */
	public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : CODE ( VARCHAR2 ) 
    /**
     * Set the "code" field value
     * This field is mapped on the database column "CODE" ( type "VARCHAR2", NotNull : true ) 
     * @param code
     */
    public void setCode( String code )
    {
        this.code = code;
    }
    /**
     * Get the "code" field value
     * This field is mapped on the database column "CODE" ( type "VARCHAR2", NotNull : true ) 
     * @return the field value
     */
    public String getCode()
    {
        return this.code;
    }

    //--- DATABASE MAPPING : NAME ( VARCHAR2 ) 
    /**
     * Set the "name" field value
     * This field is mapped on the database column "NAME" ( type "VARCHAR2", NotNull : false ) 
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    }
    /**
     * Get the "name" field value
     * This field is mapped on the database column "NAME" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getName()
    {
        return this.name;
    }
	public Boolean getIsActived() {
		return isActived;
	}
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}

}
