--------------------------------------------------------
--  File created - Wednesday-November-18-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table AUTHOR
--------------------------------------------------------

  CREATE TABLE "AUTHOR" 
   (	"AUTHOR_ID" NUMBER(20,0), 
	"AUTHOR_NAME" NVARCHAR2(30), 
	"EXPIRED" TIMESTAMP (6)
   ) 
--------------------------------------------------------
--  DDL for Table COMMENTS
--------------------------------------------------------

  CREATE TABLE "COMMENTS" 
   (	"COMMENT_ID" NUMBER(20,0), 
	"NEWS_ID" NUMBER(20,0), 
	"COMMENT_TEXT" NVARCHAR2(30), 
	"CREATION_DATE" TIMESTAMP (6)
   ) 
--------------------------------------------------------
--  DDL for Table NEWS
--------------------------------------------------------

  CREATE TABLE "NEWS" 
   (	"NEWS_ID" NUMBER(20,0), 
	"TITLE" NVARCHAR2(30), 
	"SHORT_TEXT" NVARCHAR2(100), 
	"FULL_TEXT" NVARCHAR2(2000), 
	"CREATION_DATE" TIMESTAMP (6), 
	"MODIFICATION_DATE" DATE
   )
--------------------------------------------------------
--  DDL for Table NEWS_AUTHOR
--------------------------------------------------------

  CREATE TABLE "NEWS_AUTHOR" 
   (	"NEWS_ID" NUMBER(20,0), 
	"AUTHOR_ID" NUMBER(20,0)
   ) 
--------------------------------------------------------
--  DDL for Table NEWS_TAG
--------------------------------------------------------

  CREATE TABLE "NEWS_TAG" 
   (	"NEWS_ID" NUMBER(20,0), 
	"TAG_ID" NUMBER(20,0)
   ) 
--------------------------------------------------------
--  DDL for Table NEWS_USER
--------------------------------------------------------

  CREATE TABLE "USER" 
   (	"USER_ID" NUMBER(20,0), 
	"USER_NAME" NVARCHAR2(50), 
	"LOGIN" VARCHAR2(30 BYTE), 
	"PASSWORD" VARCHAR2(30 BYTE)
   ) 
--------------------------------------------------------
--  DDL for Table ROLES
--------------------------------------------------------

  CREATE TABLE "ROLES" 
   (	"USER_ID" NUMBER(20,0), 
	"ROLE_NAME" VARCHAR2(50 BYTE)
   ) 
  
--------------------------------------------------------
--  DDL for Table TAG
--------------------------------------------------------

  CREATE TABLE "TAG" 
   (	"TAG_ID" NUMBER(20,0), 
	"TAG_NAME" NVARCHAR2(30)
   ) 
   
--------------------------------------------------------
--  DDL for SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "AUTHOR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE; 
   CREATE SEQUENCE  "NEWS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
   CREATE SEQUENCE  "COMMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
   CREATE SEQUENCE  "TAG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
   
--------------------------------------------------------
--  DDL for USER
--------------------------------------------------------

  CREATE USER NEWS_MANAGEMENT identified BY NEWS_MANAGEMENT;
  GRANT CREATE SESSION, ALTER SESSION, CREATE DATABASE LINK, CREATE MATERIALIZED VIEW, CREATE PROCEDURE, CREATE PUBLIC SYNONYM, CREATE ROLE, CREATE SEQUENCE, CREATE SYNONYM, CREATE TABLE, CREATE TRIGGER, CREATE TYPE, CREATE VIEW, UNLIMITED TABLESPACE to NEWS_MANAGEMENT;


 