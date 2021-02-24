DROP TABLE IF EXISTS `cfclongdev`.`auditlog`;
CREATE TABLE  `cfclongdev`.`auditlog` (
  `AUDIT_LOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ACTION` varchar(100) DEFAULT NULL,
  `DETAIL` text,
  `CREATED_DATE` datetime DEFAULT NULL,
  `ENTITY_ID` int(10) unsigned DEFAULT NULL,
  `ENTITY_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AUDIT_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- add column detail_out
ALTER TABLE `cfclongdev`.`auditlog` ADD COLUMN `DETAIL_OUT` TEXT AFTER `ENTITY_NAME`;
-- add column sattus
ALTER TABLE `cfclongdev`.`docmaster` ADD COLUMN `STATUS` VARCHAR(45) DEFAULT 'ACTIVE' AFTER `datasource_id`;
