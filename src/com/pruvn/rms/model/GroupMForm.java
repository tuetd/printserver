/*
 * Java bean class for entity table GROUP_M 
 * Created on 3 Oct 2013 ( Time 10:38:10 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.model;



/**
 * Model/Form for table "GROUP_M"
 * 
 * @author Telosys Tools Generator
 *
 */
public class GroupMForm
{


    private Integer    groupid      ;
    private String     groupcode    ;
    private String     groupname    ;
    private String     groupdesc    ;
    private Boolean isActived     ;

    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : GROUPID ( NUMBER ) 
    /**
     * Set the "groupid" field value
     * This field is mapped on the database column "GROUPID" ( type "NUMBER", NotNull : true ) 
     * @param groupid
     */
    public void setGroupid( Integer groupid )
    {
        this.groupid = groupid;
    }
    /**
     * Get the "groupid" field value
     * This field is mapped on the database column "GROUPID" ( type "NUMBER", NotNull : true ) 
     * @return the field value
     */
    public Integer getGroupid()
    {
        return this.groupid;
    }

    //--- DATABASE MAPPING : GROUPCODE ( VARCHAR2 ) 
    /**
     * Set the "groupcode" field value
     * This field is mapped on the database column "GROUPCODE" ( type "VARCHAR2", NotNull : false ) 
     * @param groupcode
     */
    public void setGroupcode( String groupcode )
    {
        this.groupcode = groupcode;
    }
    /**
     * Get the "groupcode" field value
     * This field is mapped on the database column "GROUPCODE" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getGroupcode()
    {
        return this.groupcode;
    }

    //--- DATABASE MAPPING : GROUPNAME ( VARCHAR2 ) 
    /**
     * Set the "groupname" field value
     * This field is mapped on the database column "GROUPNAME" ( type "VARCHAR2", NotNull : false ) 
     * @param groupname
     */
    public void setGroupname( String groupname )
    {
        this.groupname = groupname;
    }
    /**
     * Get the "groupname" field value
     * This field is mapped on the database column "GROUPNAME" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getGroupname()
    {
        return this.groupname;
    }

    //--- DATABASE MAPPING : GROUPDESC ( VARCHAR2 ) 
    /**
     * Set the "groupdesc" field value
     * This field is mapped on the database column "GROUPDESC" ( type "VARCHAR2", NotNull : false ) 
     * @param groupdesc
     */
    public void setGroupdesc( String groupdesc )
    {
        this.groupdesc = groupdesc;
    }
    /**
     * Get the "groupdesc" field value
     * This field is mapped on the database column "GROUPDESC" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getGroupdesc()
    {
        return this.groupdesc;
    }
	public Boolean getIsActived() {
		return isActived;
	}
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}

}
