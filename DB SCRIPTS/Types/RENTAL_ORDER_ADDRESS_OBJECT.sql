--------------------------------------------------------
--  File created - Saturday-May-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type RENTAL_ORDER_ADDRESS_OBJECT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."RENTAL_ORDER_ADDRESS_OBJECT" as OBJECT
(ADDRESS_LINE1 varchar2(2000),
ADDRESS_LINE2 varchar2(2000),
CITY varchar2(50),
STATE varchar2(50),
ZIP varchar2(20),
COUNTRY varchar2(50),
USAGE_TYPE VARCHAR2(20)
);

/
