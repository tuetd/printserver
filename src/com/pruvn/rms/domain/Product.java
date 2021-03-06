/*
 * Java bean class for entity table RM_PRODUCT 
 * Created on 3 Oct 2013 ( Time 12:18:07 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.domain;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;

/**
 * Domain/Entity bean for table "RM_PRODUCT"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity 
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="RM_PRODUCT")
public class Product implements Serializable
{
    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_PRODUCT"))
    @GeneratedValue(generator = "generator")
    private Integer id           ; // Primary Key

	@Column(name="CODE")
    private String     code         ;
	
	@Column(name="NAME")
    private String     name         ;
	
	@Column(name="DESCRIPTION")
    private String     description  ;
	
	@Column(name="BRANCH_ID")
    private Integer branchId     ;
	
	@Column(name = "IS_ACTIVED", nullable = true, length = 1)
	private Integer isActived;
    
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
     * This field is mapped on the database column "CODE" ( type "VARCHAR2", NotNull : false ) 
     * @param code
     */
    public void setCode( String code )
    {
        this.code = code;
    }
    /**
     * Get the "code" field value
     * This field is mapped on the database column "CODE" ( type "VARCHAR2", NotNull : false ) 
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

    //--- DATABASE MAPPING : DESCRIPTION ( VARCHAR2 ) 
    /**
     * Set the "description" field value
     * This field is mapped on the database column "DESCRIPTION" ( type "VARCHAR2", NotNull : false ) 
     * @param description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }
    /**
     * Get the "description" field value
     * This field is mapped on the database column "DESCRIPTION" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getDescription()
    {
        return this.description;
    }

    //--- DATABASE MAPPING : BRANCH_ID ( NUMBER ) 
    /**
     * Set the "branchId" field value
     * This field is mapped on the database column "BRANCH_ID" ( type "NUMBER", NotNull : false ) 
     * @param branchId
     */
    public void setBranchId( Integer branchId )
    {
        this.branchId = branchId;
    }
    /**
     * Get the "branchId" field value
     * This field is mapped on the database column "BRANCH_ID" ( type "NUMBER", NotNull : false ) 
     * @return the field value
     */
    public Integer getBranchId()
    {
        return this.branchId;
    }

    public Integer getIsActived() {
		return isActived;
	}
	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}
	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id); 
        sb.append("|"); 
        sb.append(code); 
        sb.append( "|" ); 
        sb.append(name); 
        sb.append( "|" ); 
        sb.append(description); 
        sb.append( "|" ); 
        sb.append(branchId); 
        return sb.toString();
    }

}