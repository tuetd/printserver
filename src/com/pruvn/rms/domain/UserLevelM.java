/*
 * Java bean class for entity table user_level_m 
 * Created on 17 May 2013 ( Time 17:03:25 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * Domain/Entity bean for table "user_level_m"
 * 
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name = "USER_LEVEL_M")
public class UserLevelM implements Serializable
{
    private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_USER_LEVEL_M"))
    @GeneratedValue(generator = "generator")
	@Column(name = "LEVELID")
    private int        levelid      ; // Primary Key

	@Column(name = "LEVELCODE", nullable = true)
    private String     levelcode    ;
    
    @Column(name = "LEVELNAME", nullable = true)
    private String     levelname    ;
    
    @Column(name = "LEVELDESC", nullable = true)
    private String     leveldesc    ;
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "levelid" field value
     * This field is mapped on the database column "levelid" ( type "INT", NotNull : true ) 
     * @param levelid
     */
	public void setLevelid( int levelid )
    {
        this.levelid = levelid ;
    }
    /**
     * Get the "levelid" field value
     * This field is mapped on the database column "levelid" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public int getLevelid()
    {
        return this.levelid;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : levelcode ( VARCHAR ) 
    /**
     * Set the "levelcode" field value
     * This field is mapped on the database column "levelcode" ( type "VARCHAR", NotNull : false ) 
     * @param levelcode
     */
    public void setLevelcode( String levelcode )
    {
        this.levelcode = levelcode;
    }
    /**
     * Get the "levelcode" field value
     * This field is mapped on the database column "levelcode" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getLevelcode()
    {
        return this.levelcode;
    }

    //--- DATABASE MAPPING : levelname ( VARCHAR ) 
    /**
     * Set the "levelname" field value
     * This field is mapped on the database column "levelname" ( type "VARCHAR", NotNull : false ) 
     * @param levelname
     */
    public void setLevelname( String levelname )
    {
        this.levelname = levelname;
    }
    /**
     * Get the "levelname" field value
     * This field is mapped on the database column "levelname" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getLevelname()
    {
        return this.levelname;
    }

    //--- DATABASE MAPPING : leveldesc ( VARCHAR ) 
    /**
     * Set the "leveldesc" field value
     * This field is mapped on the database column "leveldesc" ( type "VARCHAR", NotNull : false ) 
     * @param leveldesc
     */
    public void setLeveldesc( String leveldesc )
    {
        this.leveldesc = leveldesc;
    }
    /**
     * Get the "leveldesc" field value
     * This field is mapped on the database column "leveldesc" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getLeveldesc()
    {
        return this.leveldesc;
    }

    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(levelid); 
        sb.append("|"); 
        sb.append(levelcode); 
        sb.append( "|" ); 
        sb.append(levelname); 
        sb.append( "|" ); 
        sb.append(leveldesc); 
        return sb.toString();
    }

}