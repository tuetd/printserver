--------------------------------------------------------
--  File created - Monday-October-06-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table AD_HISTORY
--------------------------------------------------------

  CREATE TABLE "UAT_RMS_OWNER"."AD_HISTORY" 
   (	"ID" NUMBER, 
	"USERACTION" VARCHAR2(200 BYTE), 
	"DATEACTION" TIMESTAMP (6), 
	"ACTION" VARCHAR2(200 BYTE), 
	"IDRECORDACTION" NUMBER
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "DATATBS1" ;
--------------------------------------------------------
--  Constraints for Table AD_HISTORY
--------------------------------------------------------

  ALTER TABLE "UAT_RMS_OWNER"."AD_HISTORY" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  DDL for Trigger AD_HISTORY_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "UAT_RMS_OWNER"."AD_HISTORY_TRG" 
BEFORE INSERT ON AD_HISTORY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT AD_HISTORY_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "UAT_RMS_OWNER"."AD_HISTORY_TRG" ENABLE;

 CREATE SEQUENCE  "UAT_RMS_OWNER"."AD_HISTORY_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
