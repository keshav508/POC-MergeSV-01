--------------------------------------------------------
--  File created - Saturday-May-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type RENTAL_ORDER_LINES_DTLS_OBJECT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."RENTAL_ORDER_LINES_DTLS_OBJECT" 
AS
  OBJECT
  (
    ORDER_HEADER_ID varchar2(20), 
    ORDER_NUMBER varchar2(20),
    ORDER_LINE_ID varchar2(20),
    WAREHOUSE_ID varchar2(20),
    LINE_ITEM_ID varchar2(20),
    REPLACEMENT_ITEM_ID varchar2(20),
    SKU varchar2(200),
    PART_DESCRIPTON varchar2(2000),
    QTY varchar2(200)
    );

/
