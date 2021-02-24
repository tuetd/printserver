package com.pruvn.rms.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.pruvn.rms.utils.CommonUtils;

/**
 * <p>Pojo mapping TABLE RM_RECORD</p>
 * <p></p>
 *
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 * @author Salto-db Generator v1.0.16 / EJB3
 * 
 */
@Entity
@MappedSuperclass
@Table(name = "RM_RECORD")
public class Record implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name="ID")
	@Id
    private int id           ;
	
	@Column(name="CT")
	@Temporal(TemporalType.TIMESTAMP)
    private Date       ct           ;
	
	@Column(name="MT")
	@Temporal(TemporalType.TIMESTAMP)
    private Date       mt           ;
	
	@Column(name="AGREEMENTNO")
    private String     agreementno  ;
	
	@Column(name="DISBURSALDATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date       disbursaldate ;
	
	@Column(name="LESSEEID")
    private int lesseeid     ;
	
	@Column(name="TENURE")
    private int tenure       ;
	
	@Column(name="AMT_REQUESTED")
    private int amtRequested ;
	
	@Column(name="EMI")
    private BigDecimal emi          ;
	
	@Column(name="DUEDAY")
    private String     dueday       ;
	
	@Column(name="PRODUCTFLAG")
    private String     productflag  ;
	
	@Column(name="BRANCHDESC")
    private String     branchdesc   ;
	
	@Column(name="CUSTOMERNAME")
    private String     customername ;
	
	@Column(name="PAN_NO")
    private String     panNo        ;
	
	@Column(name="ADDRESS1")
    private String     address1     ;
	
	@Column(name="ADDRESS2")
    private String     address2     ;
	
	@Column(name="ADDRESS3")
    private String     address3     ;
	
	@Column(name="ADDRESS4")
    private String     address4     ;
	
	@Column(name="CITY")
    private String     city         ;
	
	@Column(name="STATEDESC")
    private String     statedesc    ;
	
	@Column(name="PHONE1")
    private String     phone1       ;
	
	@Column(name="MOBILE")
    private String     mobile       ;
	
	@Column(name="PHONE2")
    private String     phone2       ;
	
	@Column(name="CREDIT_SHIELD")
    private BigDecimal creditShield ;
	
	@Column(name="APP_FORMNO")
    private String     appFormno    ;
	
	@Column(name="APPLID")
    private int applid       ;
	
	@Column(name="STATUS")
    private String     status       ;
	
	@Column(name="MATURITYDATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date       maturitydate ;
	
	@Column(name="SEX")
    private String     sex          ;
	
	@Column(name="DOB")
	@Temporal(TemporalType.TIMESTAMP)
    private Date       dob          ;
	
	@Column(name="SUPPLIER_NAME")
    private String     supplierName ;
	
	@Column(name="SOURCE_STATE")
    private String     sourceState  ;
	
	@Column(name="APPDATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date       appdate      ;
	
	@Column(name="LAST_RO")
    private String     lastRo       ;
	
	@Column(name="LAST_RO_NAME")
    private String     lastRoName   ;
	
	@Column(name="STAGE")
    private String     stage        ;
	
	@Column(name="PO_BILL_NO")
    private String     poBillNo        ;
	
	@Column(name="STAGE_2")
    private String     stage2        ;
	
	@Column(name="BOX_LABEL")
    private String     boxLabel        ;
	
	@Column(name="BOX_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date     boxDate        ;
	
	@Column(name="SEND_TO_BRANCH")
    private String     sendToBranch ;
	
	@Column(name="SALE_TEAM")
    private String     saleTeam     ;
	
	@Column(name="SALE_AGENT")
    private String     saleAgent    ;
	
	@Column(name="SEQUENCE_ID")
    private String     sequenceId   ;
	
	@Transient
    private String cusName   ;
	
	@Column(name="AREA")
    private String     area   ;
	
	@Column(name="CS_ARRIVED")
	private String csArrived;
    
	@Column(name="APP_ID_C")
	private String appIdC;
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : ID ( NUMBER ) 
    /**
     * Set the "id" field value
     * This field is mapped on the database column "ID" ( type "NUMBER", NotNull : false ) 
     * @param id
     */
    public void setId( int id )
    {
        this.id = id;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "ID" ( type "NUMBER", NotNull : false ) 
     * @return the field value
     */
    public int getId()
    {
        return this.id;
    }

    //--- DATABASE MAPPING : CT ( DATE ) 
    /**
     * Set the "ct" field value
     * This field is mapped on the database column "CT" ( type "DATE", NotNull : false ) 
     * @param ct
     */
    public void setCt( Date ct )
    {
        this.ct = ct;
    }
    /**
     * Get the "ct" field value
     * This field is mapped on the database column "CT" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getCt()
    {
        return this.ct;
    }

    //--- DATABASE MAPPING : MT ( DATE ) 
    /**
     * Set the "mt" field value
     * This field is mapped on the database column "MT" ( type "DATE", NotNull : false ) 
     * @param mt
     */
    public void setMt( Date mt )
    {
        this.mt = mt;
    }
    /**
     * Get the "mt" field value
     * This field is mapped on the database column "MT" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getMt()
    {
        return this.mt;
    }

    //--- DATABASE MAPPING : AGREEMENTNO ( VARCHAR2 ) 
    /**
     * Set the "agreementno" field value
     * This field is mapped on the database column "AGREEMENTNO" ( type "VARCHAR2", NotNull : false ) 
     * @param agreementno
     */
    public void setAgreementno( String agreementno )
    {
        this.agreementno = agreementno;
    }
    /**
     * Get the "agreementno" field value
     * This field is mapped on the database column "AGREEMENTNO" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getAgreementno()
    {
        return this.agreementno;
    }

    //--- DATABASE MAPPING : DISBURSALDATE ( DATE ) 
    /**
     * Set the "disbursaldate" field value
     * This field is mapped on the database column "DISBURSALDATE" ( type "DATE", NotNull : false ) 
     * @param disbursaldate
     */
    public void setDisbursaldate( Date disbursaldate )
    {
        this.disbursaldate = disbursaldate;
    }
    /**
     * Get the "disbursaldate" field value
     * This field is mapped on the database column "DISBURSALDATE" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getDisbursaldate()
    {
        return this.disbursaldate;
    }

    //--- DATABASE MAPPING : LESSEEID ( NUMBER ) 
    /**
     * Set the "lesseeid" field value
     * This field is mapped on the database column "LESSEEID" ( type "NUMBER", NotNull : false ) 
     * @param lesseeid
     */
    public void setLesseeid( int lesseeid )
    {
        this.lesseeid = lesseeid;
    }
    /**
     * Get the "lesseeid" field value
     * This field is mapped on the database column "LESSEEID" ( type "NUMBER", NotNull : false ) 
     * @return the field value
     */
    public int getLesseeid()
    {
        return this.lesseeid;
    }

    //--- DATABASE MAPPING : TENURE ( NUMBER ) 
    /**
     * Set the "tenure" field value
     * This field is mapped on the database column "TENURE" ( type "NUMBER", NotNull : true ) 
     * @param tenure
     */
    public void setTenure( int tenure )
    {
        this.tenure = tenure;
    }
    /**
     * Get the "tenure" field value
     * This field is mapped on the database column "TENURE" ( type "NUMBER", NotNull : true ) 
     * @return the field value
     */
    public int getTenure()
    {
        return this.tenure;
    }

    //--- DATABASE MAPPING : AMT_REQUESTED ( NUMBER ) 
    /**
     * Set the "amtRequested" field value
     * This field is mapped on the database column "AMT_REQUESTED" ( type "NUMBER", NotNull : false ) 
     * @param amtRequested
     */
    public void setAmtRequested( int amtRequested )
    {
        this.amtRequested = amtRequested;
    }
    /**
     * Get the "amtRequested" field value
     * This field is mapped on the database column "AMT_REQUESTED" ( type "NUMBER", NotNull : false ) 
     * @return the field value
     */
    public int getAmtRequested()
    {
        return this.amtRequested;
    }

    //--- DATABASE MAPPING : EMI ( NUMBER ) 
    /**
     * Set the "emi" field value
     * This field is mapped on the database column "EMI" ( type "NUMBER", NotNull : false ) 
     * @param emi
     */
    public void setEmi( BigDecimal emi )
    {
        this.emi = emi;
    }
    /**
     * Get the "emi" field value
     * This field is mapped on the database column "EMI" ( type "NUMBER", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getEmi()
    {
        return this.emi;
    }

    //--- DATABASE MAPPING : DUEDAY ( VARCHAR2 ) 
    /**
     * Set the "dueday" field value
     * This field is mapped on the database column "DUEDAY" ( type "VARCHAR2", NotNull : false ) 
     * @param dueday
     */
    public void setDueday( String dueday )
    {
        this.dueday = dueday;
    }
    /**
     * Get the "dueday" field value
     * This field is mapped on the database column "DUEDAY" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getDueday()
    {
        return this.dueday;
    }

    //--- DATABASE MAPPING : PRODUCTFLAG ( VARCHAR2 ) 
    /**
     * Set the "productflag" field value
     * This field is mapped on the database column "PRODUCTFLAG" ( type "VARCHAR2", NotNull : false ) 
     * @param productflag
     */
    public void setProductflag( String productflag )
    {
        this.productflag = productflag;
    }
    /**
     * Get the "productflag" field value
     * This field is mapped on the database column "PRODUCTFLAG" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getProductflag()
    {
        return this.productflag;
    }

    //--- DATABASE MAPPING : BRANCHDESC ( VARCHAR2 ) 
    /**
     * Set the "branchdesc" field value
     * This field is mapped on the database column "BRANCHDESC" ( type "VARCHAR2", NotNull : false ) 
     * @param branchdesc
     */
    public void setBranchdesc( String branchdesc )
    {
        this.branchdesc = branchdesc;
    }
    /**
     * Get the "branchdesc" field value
     * This field is mapped on the database column "BRANCHDESC" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getBranchdesc()
    {
        return this.branchdesc;
    }

    //--- DATABASE MAPPING : CUSTOMERNAME ( NVARCHAR2 ) 
    /**
     * Set the "customername" field value
     * This field is mapped on the database column "CUSTOMERNAME" ( type "NVARCHAR2", NotNull : false ) 
     * @param customername
     */
    public void setCustomername( String customername )
    {
        this.customername = customername;
    }
    /**
     * Get the "customername" field value
     * This field is mapped on the database column "CUSTOMERNAME" ( type "NVARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getCustomername()
    {
        return this.customername;
    }

    //--- DATABASE MAPPING : PAN_NO ( VARCHAR2 ) 
    /**
     * Set the "panNo" field value
     * This field is mapped on the database column "PAN_NO" ( type "VARCHAR2", NotNull : false ) 
     * @param panNo
     */
    public void setPanNo( String panNo )
    {
        this.panNo = panNo;
    }
    /**
     * Get the "panNo" field value
     * This field is mapped on the database column "PAN_NO" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getPanNo()
    {
        return this.panNo;
    }

    //--- DATABASE MAPPING : ADDRESS1 ( VARCHAR2 ) 
    /**
     * Set the "address1" field value
     * This field is mapped on the database column "ADDRESS1" ( type "VARCHAR2", NotNull : false ) 
     * @param address1
     */
    public void setAddress1( String address1 )
    {
        this.address1 = address1;
    }
    /**
     * Get the "address1" field value
     * This field is mapped on the database column "ADDRESS1" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getAddress1()
    {
        return this.address1;
    }

    //--- DATABASE MAPPING : ADDRESS2 ( VARCHAR2 ) 
    /**
     * Set the "address2" field value
     * This field is mapped on the database column "ADDRESS2" ( type "VARCHAR2", NotNull : false ) 
     * @param address2
     */
    public void setAddress2( String address2 )
    {
        this.address2 = address2;
    }
    /**
     * Get the "address2" field value
     * This field is mapped on the database column "ADDRESS2" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getAddress2()
    {
        return this.address2;
    }

    //--- DATABASE MAPPING : ADDRESS3 ( VARCHAR2 ) 
    /**
     * Set the "address3" field value
     * This field is mapped on the database column "ADDRESS3" ( type "VARCHAR2", NotNull : false ) 
     * @param address3
     */
    public void setAddress3( String address3 )
    {
        this.address3 = address3;
    }
    /**
     * Get the "address3" field value
     * This field is mapped on the database column "ADDRESS3" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getAddress3()
    {
        return this.address3;
    }

    //--- DATABASE MAPPING : ADDRESS4 ( VARCHAR2 ) 
    /**
     * Set the "address4" field value
     * This field is mapped on the database column "ADDRESS4" ( type "VARCHAR2", NotNull : false ) 
     * @param address4
     */
    public void setAddress4( String address4 )
    {
        this.address4 = address4;
    }
    /**
     * Get the "address4" field value
     * This field is mapped on the database column "ADDRESS4" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getAddress4()
    {
        return this.address4;
    }

    //--- DATABASE MAPPING : CITY ( VARCHAR2 ) 
    /**
     * Set the "city" field value
     * This field is mapped on the database column "CITY" ( type "VARCHAR2", NotNull : false ) 
     * @param city
     */
    public void setCity( String city )
    {
        this.city = city;
    }
    /**
     * Get the "city" field value
     * This field is mapped on the database column "CITY" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getCity()
    {
        return this.city;
    }

    //--- DATABASE MAPPING : STATEDESC ( VARCHAR2 ) 
    /**
     * Set the "statedesc" field value
     * This field is mapped on the database column "STATEDESC" ( type "VARCHAR2", NotNull : false ) 
     * @param statedesc
     */
    public void setStatedesc( String statedesc )
    {
        this.statedesc = statedesc;
    }
    /**
     * Get the "statedesc" field value
     * This field is mapped on the database column "STATEDESC" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getStatedesc()
    {
        return this.statedesc;
    }

    //--- DATABASE MAPPING : PHONE1 ( VARCHAR2 ) 
    /**
     * Set the "phone1" field value
     * This field is mapped on the database column "PHONE1" ( type "VARCHAR2", NotNull : false ) 
     * @param phone1
     */
    public void setPhone1( String phone1 )
    {
        this.phone1 = phone1;
    }
    /**
     * Get the "phone1" field value
     * This field is mapped on the database column "PHONE1" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getPhone1()
    {
        return this.phone1;
    }

    //--- DATABASE MAPPING : MOBILE ( VARCHAR2 ) 
    /**
     * Set the "mobile" field value
     * This field is mapped on the database column "MOBILE" ( type "VARCHAR2", NotNull : false ) 
     * @param mobile
     */
    public void setMobile( String mobile )
    {
        this.mobile = mobile;
    }
    /**
     * Get the "mobile" field value
     * This field is mapped on the database column "MOBILE" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getMobile()
    {
        return this.mobile;
    }

    //--- DATABASE MAPPING : PHONE2 ( VARCHAR2 ) 
    /**
     * Set the "phone2" field value
     * This field is mapped on the database column "PHONE2" ( type "VARCHAR2", NotNull : false ) 
     * @param phone2
     */
    public void setPhone2( String phone2 )
    {
        this.phone2 = phone2;
    }
    /**
     * Get the "phone2" field value
     * This field is mapped on the database column "PHONE2" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getPhone2()
    {
        return this.phone2;
    }

    //--- DATABASE MAPPING : CREDIT_SHIELD ( NUMBER ) 
    /**
     * Set the "creditShield" field value
     * This field is mapped on the database column "CREDIT_SHIELD" ( type "NUMBER", NotNull : false ) 
     * @param creditShield
     */
    public void setCreditShield( BigDecimal creditShield )
    {
        this.creditShield = creditShield;
    }
    /**
     * Get the "creditShield" field value
     * This field is mapped on the database column "CREDIT_SHIELD" ( type "NUMBER", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getCreditShield()
    {
        return this.creditShield;
    }

    //--- DATABASE MAPPING : APP_FORMNO ( VARCHAR2 ) 
    /**
     * Set the "appFormno" field value
     * This field is mapped on the database column "APP_FORMNO" ( type "VARCHAR2", NotNull : false ) 
     * @param appFormno
     */
    public void setAppFormno( String appFormno )
    {
        this.appFormno = appFormno;
    }
    /**
     * Get the "appFormno" field value
     * This field is mapped on the database column "APP_FORMNO" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getAppFormno()
    {
        return this.appFormno;
    }

    //--- DATABASE MAPPING : APPLID ( NUMBER ) 
    /**
     * Set the "applid" field value
     * This field is mapped on the database column "APPLID" ( type "NUMBER", NotNull : true ) 
     * @param applid
     */
    public void setApplid( int applid )
    {
        this.applid = applid;
    }
    /**
     * Get the "applid" field value
     * This field is mapped on the database column "APPLID" ( type "NUMBER", NotNull : true ) 
     * @return the field value
     */
    public int getApplid()
    {
        return this.applid;
    }

    //--- DATABASE MAPPING : STATUS ( VARCHAR2 ) 
    /**
     * Set the "status" field value
     * This field is mapped on the database column "STATUS" ( type "VARCHAR2", NotNull : false ) 
     * @param status
     */
    public void setStatus( String status )
    {
        this.status = status;
    }
    /**
     * Get the "status" field value
     * This field is mapped on the database column "STATUS" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getStatus()
    {
        return this.status;
    }

    //--- DATABASE MAPPING : MATURITYDATE ( DATE ) 
    /**
     * Set the "maturitydate" field value
     * This field is mapped on the database column "MATURITYDATE" ( type "DATE", NotNull : false ) 
     * @param maturitydate
     */
    public void setMaturitydate( Date maturitydate )
    {
        this.maturitydate = maturitydate;
    }
    /**
     * Get the "maturitydate" field value
     * This field is mapped on the database column "MATURITYDATE" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getMaturitydate()
    {
        return this.maturitydate;
    }

    //--- DATABASE MAPPING : SEX ( VARCHAR2 ) 
    /**
     * Set the "sex" field value
     * This field is mapped on the database column "SEX" ( type "VARCHAR2", NotNull : false ) 
     * @param sex
     */
    public void setSex( String sex )
    {
        this.sex = sex;
    }
    /**
     * Get the "sex" field value
     * This field is mapped on the database column "SEX" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSex()
    {
        return this.sex;
    }

    //--- DATABASE MAPPING : DOB ( DATE ) 
    /**
     * Set the "dob" field value
     * This field is mapped on the database column "DOB" ( type "DATE", NotNull : false ) 
     * @param dob
     */
    public void setDob( Date dob )
    {
        this.dob = dob;
    }
    /**
     * Get the "dob" field value
     * This field is mapped on the database column "DOB" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getDob()
    {
        return this.dob;
    }

    //--- DATABASE MAPPING : SUPPLIER_NAME ( VARCHAR2 ) 
    /**
     * Set the "supplierName" field value
     * This field is mapped on the database column "SUPPLIER_NAME" ( type "VARCHAR2", NotNull : false ) 
     * @param supplierName
     */
    public void setSupplierName( String supplierName )
    {
        this.supplierName = supplierName;
    }
    /**
     * Get the "supplierName" field value
     * This field is mapped on the database column "SUPPLIER_NAME" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSupplierName()
    {
        return this.supplierName;
    }

    //--- DATABASE MAPPING : SOURCE_STATE ( VARCHAR2 ) 
    /**
     * Set the "sourceState" field value
     * This field is mapped on the database column "SOURCE_STATE" ( type "VARCHAR2", NotNull : false ) 
     * @param sourceState
     */
    public void setSourceState( String sourceState )
    {
        this.sourceState = sourceState;
    }
    /**
     * Get the "sourceState" field value
     * This field is mapped on the database column "SOURCE_STATE" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSourceState()
    {
        return this.sourceState;
    }

    //--- DATABASE MAPPING : APPDATE ( DATE ) 
    /**
     * Set the "appdate" field value
     * This field is mapped on the database column "APPDATE" ( type "DATE", NotNull : false ) 
     * @param appdate
     */
    public void setAppdate( Date appdate )
    {
        this.appdate = appdate;
    }
    /**
     * Get the "appdate" field value
     * This field is mapped on the database column "APPDATE" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getAppdate()
    {
        return this.appdate;
    }

    //--- DATABASE MAPPING : LAST_RO ( VARCHAR2 ) 
    /**
     * Set the "lastRo" field value
     * This field is mapped on the database column "LAST_RO" ( type "VARCHAR2", NotNull : false ) 
     * @param lastRo
     */
    public void setLastRo( String lastRo )
    {
        this.lastRo = lastRo;
    }
    /**
     * Get the "lastRo" field value
     * This field is mapped on the database column "LAST_RO" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getLastRo()
    {
        return this.lastRo;
    }

    //--- DATABASE MAPPING : LAST_RO_NAME ( VARCHAR2 ) 
    /**
     * Set the "lastRoName" field value
     * This field is mapped on the database column "LAST_RO_NAME" ( type "VARCHAR2", NotNull : false ) 
     * @param lastRoName
     */
    public void setLastRoName( String lastRoName )
    {
        this.lastRoName = lastRoName;
    }
    /**
     * Get the "lastRoName" field value
     * This field is mapped on the database column "LAST_RO_NAME" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getLastRoName()
    {
        return this.lastRoName;
    }

    //--- DATABASE MAPPING : STAGE ( CHAR ) 
    /**
     * Set the "stage" field value
     * This field is mapped on the database column "STAGE" ( type "CHAR", NotNull : false ) 
     * @param stage
     */
    public void setStage( String stage )
    {
        this.stage = stage;
    }
    /**
     * Get the "stage" field value
     * This field is mapped on the database column "STAGE" ( type "CHAR", NotNull : false ) 
     * @return the field value
     */
    public String getStage()
    {
        return this.stage;
    }
    
    public String getPoBillNo() {
		return poBillNo;
	}
    
	public void setPoBillNo(String poBillNo) {
		this.poBillNo = poBillNo;
	}
	
  //--- DATABASE MAPPING : STAGE_@ ( CHAR ) 
    /**
     * Set the "stage" field value
     * This field is mapped on the database column "STAGE_2" ( type "CHAR", NotNull : false ) 
     * @param stage
     */
    public void setStage2( String stage2 )
    {
        this.stage2 = stage2;
    }
    /**
     * Get the "stage2" field value
     * This field is mapped on the database column "STAGE" ( type "CHAR", NotNull : false ) 
     * @return the field value
     */
    public String getStage2()
    {
        return this.stage2;
    }
    
  //--- DATABASE MAPPING : BOX_LABEL ( CHAR ) 
    /**
     * Set the "stage" field value
     * This field is mapped on the database column "BOX_LABEL" ( type "CHAR", NotNull : false ) 
     * @param stage
     */
    public void setBoxLabel( String boxLabel )
    {
        this.boxLabel = boxLabel;
    }
    /**
     * Get the "boxLabel" field value
     * This field is mapped on the database column "BOX_LABEL" ( type "CHAR", NotNull : false ) 
     * @return the field value
     */
    public String getBoxLabel()
    {
        return this.boxLabel;
    }
    
  //--- DATABASE MAPPING : BOX_DATE ( DATE ) 
    /**
     * Set the "stage" field value
     * This field is mapped on the database column "BOX_DATE" ( type "DATE", NotNull : false ) 
     * @param stage
     */
    public void setBoxDate( Date boxDate )
    {
        this.boxDate = boxDate;
    }
    /**
     * Get the "boxDate" field value
     * This field is mapped on the database column "BOX_DATE" ( type "DATE", NotNull : false ) 
     * @return the field value
     */
    public Date getBoxDate()
    {
        return this.boxDate;
    }
    
  //--- DATABASE MAPPING : SEND_TO_BRANCH ( VARCHAR2 ) 
    /**
     * Set the "sendToBranch" field value
     * This field is mapped on the database column "SEND_TO_BRANCH" ( type "VARCHAR2", NotNull : false ) 
     * @param sendToBranch
     */
    public void setSendToBranch( String sendToBranch )
    {
        this.sendToBranch = sendToBranch;
    }
    /**
     * Get the "sendToBranch" field value
     * This field is mapped on the database column "SEND_TO_BRANCH" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSendToBranch()
    {
        return this.sendToBranch;
    }

    //--- DATABASE MAPPING : SALE_TEAM ( VARCHAR2 ) 
    /**
     * Set the "saleTeam" field value
     * This field is mapped on the database column "SALE_TEAM" ( type "VARCHAR2", NotNull : false ) 
     * @param saleTeam
     */
    public void setSaleTeam( String saleTeam )
    {
        this.saleTeam = saleTeam;
    }
    /**
     * Get the "saleTeam" field value
     * This field is mapped on the database column "SALE_TEAM" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSaleTeam()
    {
        return this.saleTeam;
    }

    //--- DATABASE MAPPING : SALE_AGENT ( VARCHAR2 ) 
    /**
     * Set the "saleAgent" field value
     * This field is mapped on the database column "SALE_AGENT" ( type "VARCHAR2", NotNull : false ) 
     * @param saleAgent
     */
    public void setSaleAgent( String saleAgent )
    {
        this.saleAgent = saleAgent;
    }
    /**
     * Get the "saleAgent" field value
     * This field is mapped on the database column "SALE_AGENT" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSaleAgent()
    {
        return this.saleAgent;
    }

    //--- DATABASE MAPPING : SEQUENCE_ID ( VARCHAR2 ) 
    /**
     * Set the "sequenceId" field value
     * This field is mapped on the database column "SEQUENCE_ID" ( type "VARCHAR2", NotNull : false ) 
     * @param sequenceId
     */
    public void setSequenceId( String sequenceId )
    {
        this.sequenceId = sequenceId;
    }
    /**
     * Get the "sequenceId" field value
     * This field is mapped on the database column "SEQUENCE_ID" ( type "VARCHAR2", NotNull : false ) 
     * @return the field value
     */
    public String getSequenceId()
    {
        return this.sequenceId;
    }
    
    public String getCusName() {
		return  CommonUtils.UnicodeToCompound(customername);
	}
    
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}	
	
	public String getCsArrived() {
		return csArrived;
	}
	public void setCsArrived(String csArrived) {
		this.csArrived = csArrived;
	}
	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(id); 
        sb.append( "|" ); 
        sb.append(ct); 
        sb.append( "|" ); 
        sb.append(mt); 
        sb.append( "|" ); 
        sb.append(agreementno); 
        sb.append( "|" ); 
        sb.append(disbursaldate); 
        sb.append( "|" ); 
        sb.append(lesseeid); 
        sb.append( "|" ); 
        sb.append(tenure); 
        sb.append( "|" ); 
        sb.append(amtRequested); 
        sb.append( "|" ); 
        sb.append(emi); 
        sb.append( "|" ); 
        sb.append(dueday); 
        sb.append( "|" ); 
        sb.append(productflag); 
        sb.append( "|" ); 
        sb.append(branchdesc); 
        sb.append( "|" ); 
        sb.append(customername); 
        sb.append( "|" ); 
        sb.append(panNo); 
        sb.append( "|" ); 
        sb.append(address1); 
        sb.append( "|" ); 
        sb.append(address2); 
        sb.append( "|" ); 
        sb.append(address3); 
        sb.append( "|" ); 
        sb.append(address4); 
        sb.append( "|" ); 
        sb.append(city); 
        sb.append( "|" ); 
        sb.append(statedesc); 
        sb.append( "|" ); 
        sb.append(phone1); 
        sb.append( "|" ); 
        sb.append(mobile); 
        sb.append( "|" ); 
        sb.append(phone2); 
        sb.append( "|" ); 
        sb.append(creditShield); 
        sb.append( "|" ); 
        sb.append(appFormno); 
        sb.append( "|" ); 
        sb.append(applid); 
        sb.append( "|" ); 
        sb.append(status); 
        sb.append( "|" ); 
        sb.append(maturitydate); 
        sb.append( "|" ); 
        sb.append(sex); 
        sb.append( "|" ); 
        sb.append(dob); 
        sb.append( "|" ); 
        sb.append(supplierName); 
        sb.append( "|" ); 
        sb.append(sourceState); 
        sb.append( "|" ); 
        sb.append(appdate); 
        sb.append( "|" ); 
        sb.append(lastRo); 
        sb.append( "|" ); 
        sb.append(lastRoName); 
        sb.append( "|" ); 
        sb.append(stage); 
        return sb.toString();
    }
	public String getAppIdC() {
		return appIdC;
	}
	public void setAppIdC(String appIdC) {
		this.appIdC = appIdC;
	}


}