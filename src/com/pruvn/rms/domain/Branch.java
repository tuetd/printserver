/*
 * Java bean class for entity table RM_BRANCH 
 * Created on 3 Oct 2013 ( Time 12:18:01 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.domain;

import java.io.Serializable;

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


/**
 * Domain/Entity bean for table "RM_BRANCH"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity 
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="RM_BRANCH")
public class Branch implements Serializable
{
    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_BRANCH"))
    @GeneratedValue(generator = "generator")
    private Integer    id           ; // Primary Key

	@Column(name="CODE")
    private String     code         ;
	
	@Column(name="NAME")
    private String     name         ;
	
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
        return sb.toString();
    }

}