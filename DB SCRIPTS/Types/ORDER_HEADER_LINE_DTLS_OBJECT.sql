--------------------------------------------------------
--  File created - Saturday-May-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type ORDER_HEADER_LINE_DTLS_OBJECT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."ORDER_HEADER_LINE_DTLS_OBJECT" as object(
ORDER_HEADER_ID varchar2(20), 
ORDER_NUMBER varchar2(20),
--ITEM_DAILY_RATE varchar2(200),
ORDER_LINE_ID varchar2(20),
ORDER_LINE_FREIGHT varchar2(20),
ITEM_ID varchar2(20)
);

/
