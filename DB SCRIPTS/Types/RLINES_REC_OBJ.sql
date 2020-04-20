--------------------------------------------------------
--  File created - Saturday-May-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type RLINES_REC_OBJ
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."RLINES_REC_OBJ" 
as object
( id Number,
sku varchar2(255),
description varchar2(255),
org_name varchar2(255),
quantity number,
needbydate date,
estimate_Date date,
freight  NUMBER
);

/
