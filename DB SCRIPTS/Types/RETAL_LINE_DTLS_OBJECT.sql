--------------------------------------------------------
--  File created - Saturday-May-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type RENTAL_LINE_DTLS_OBJECT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."RENTAL_LINE_DTLS_OBJECT" as object(
RENTAL_LINE_ID varchar2(20), 
rental_start_Date varchar2(20),
rental_return_Date varchar2(20),
rental_unbillied_FREIGHT varchar2(20),
rental_damage varchar2(20),
rental_status varchar2(20)
);

/
