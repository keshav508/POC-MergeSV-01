--------------------------------------------------------
--  File created - Saturday-May-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type RENTAL_LINES_DTLS_OBJECT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."RENTAL_LINES_DTLS_OBJECT" as object(
ORDER_HEADER_ID varchar2(20), 
ORDER_NUMBER varchar2(20),
RENTAL_LINE_ID varchar2(20)
,RENTAL_HEADER_ID varchar2(20)
,ITEM_ID varchar2(20) 
,INSTALL_BASE_ID varchar2(20)
, ITEM_SKU varchar2(200)
,ITEM_DESCRIPTION varchar2(2000)
,SERIAL_NUMBER varchar2(20)
);

/
