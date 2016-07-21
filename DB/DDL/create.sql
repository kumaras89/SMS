CREATE TABLE SMS_MA_BRANCH (ID  BIGSERIAL NOT NULL, BR_CODE VARCHAR(255) NOT NULL, BR_ACTIVE INT4 NOT NULL, BR_NAME VARCHAR(255) NOT NULL, BR_ADDRESS_ID INT8 NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SMS_MA_COURSE (ID  BIGSERIAL NOT NULL, CO_CODE VARCHAR(255), CO_DESCRIPTION VARCHAR(255), CO_NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE SMS_MA_FEES_PARTICULAR (ID  BIGSERIAL NOT NULL, FP_CODE VARCHAR(255) NOT NULL, FP_NAME VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SMS_MA_ROLE_OPERATION_LINK (ID  BIGSERIAL NOT NULL, ROL_SO_ID INT8, ROL_UR_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_MA_SCHEME (ID  BIGSERIAL NOT NULL, SC_CODE VARCHAR(255), SC_DESCRIPTION VARCHAR(255), SC_FEES_AMOUNT NUMERIC(19, 2), SC_NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE SMS_MA_SECURED_OPERATION (ID  BIGSERIAL NOT NULL, SO_OPERATION VARCHAR(255) NOT NULL, SO_OPERATION_DESC VARCHAR(255) NOT NULL, SO_TYPE VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SMS_MA_USER (ID  BIGSERIAL NOT NULL, US_BRANCH VARCHAR(255), US_FIRST_NAME VARCHAR(255), US_LAST_NAME VARCHAR(255), US_NAME VARCHAR(255), US_PASSWORD VARCHAR(255), US_UR_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_MA_USER_ROLE (ID  BIGSERIAL NOT NULL, UR_DESC VARCHAR(255) NOT NULL, UR_NAME VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_ADDRESS (ID  BIGSERIAL NOT NULL, AD_ADDRESS_1 VARCHAR(255) NOT NULL, AD_ADDRESS_2 VARCHAR(255), AD_ADDRESS_3 VARCHAR(255), AD_DISTRICT VARCHAR(255) NOT NULL, AD_POSTAL_CODE INT8 CHECK (AD_POSTAL_CODE<=999999 AND AD_POSTAL_CODE>=100000), AD_TALUK VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_EDUCATION_DETAIL (ID  BIGSERIAL NOT NULL, ED_EXAM_PASSED VARCHAR(255) NOT NULL, ED_GROUP_NAME VARCHAR(255) NOT NULL, ED_INSTITUTE_NAME VARCHAR(255) NOT NULL, ED_PASSING_YEAR INT8 CHECK (ED_PASSING_YEAR>=2000 AND ED_PASSING_YEAR<=2200), ED_PERCENTAGE_OBTAINED INT4 CHECK (ED_PERCENTAGE_OBTAINED>=1 AND ED_PERCENTAGE_OBTAINED<=100), ED_REMARK VARCHAR(255), STS_ED_STUDENT_ID INT8, ED_STUDENT_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_GUARDIAN (ID  BIGSERIAL NOT NULL, GU_NAME VARCHAR(255) NOT NULL, GU_OCCUPATION VARCHAR(255) NOT NULL, GU_PHONE_NUMBER VARCHAR(13) NOT NULL, GU_RELATION INT4 NOT NULL, GU_STUDENT_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_ID_CARD (ID  BIGSERIAL NOT NULL, IC_BRANCH_NAME VARCHAR(255), IC_CREATED_DATE DATE, IC_FMS_ID INT8, IC_IDENTITY_CODE VARCHAR(255), IC_LAST_MODIFIED_DATE DATE, IC_NAME VARCHAR(255), IC_STATUS VARCHAR(255), IC_VALIDITY DATE, IC_ADDRESS_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_MARKETING_EMPLOYEE (ID  BIGSERIAL NOT NULL, ME_CODE VARCHAR(255), ME_DESIGNATION INT4, ME_NAME VARCHAR(255), ME_PHONE_NUMBER VARCHAR(255), ME_ADDRESS_ID INT8, ME_USER_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_MARK_DETAILS (CLASS VARCHAR(31) NOT NULL, MD_ID INT8 NOT NULL, MS_BOARD_OF_EXAMINATION VARCHAR(255) NOT NULL, MS_PLACE_SCHOOL VARCHAR(255) NOT NULL, MS_REG_NUMBER VARCHAR(255) NOT NULL, MS_SCHOOL_NAME VARCHAR(255) NOT NULL, MS_YEAR_PASSING INT4 CHECK (MS_YEAR_PASSING>=2000 AND MS_YEAR_PASSING<=2200), MD_PERCENTAGE INT4 CHECK (MD_PERCENTAGE>=1 AND MD_PERCENTAGE<=100), MD_TOTAL_MARKS INT8 CHECK (MD_TOTAL_MARKS>=1), PRIMARY KEY (MD_ID));
CREATE TABLE SMS_TR_OTHER_LANGUAGE (ID INT8 NOT NULL, OL_READ BOOLEAN, OL_SPEAK BOOLEAN, OL_WRITE BOOLEAN, OL_NAME VARCHAR(255) NOT NULL, OL_STUDENT_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_PAYMENT (ID INT8 NOT NULL, SP_ADMITTED_BY VARCHAR(255), SP_ADMITTED_REJECTED_BY VARCHAR(255), SP_AMOUNT NUMERIC(19, 2), SP_APPROVED_BY VARCHAR(255), SP_BANK_BRANCH_NAME VARCHAR(255), SP_BANK_NAME VARCHAR(255), SP_DD_NUMBER VARCHAR(255), SP_PAID_DATE TIMESTAMP, SP_STUDENT_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_PAYMENT_FEES (ID  BIGSERIAL NOT NULL, FC_AMOUNT NUMERIC(19, 2), FC_FEES_PARTICULAR_ID INT8, SP_PAYMENT_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_SCHEME_TO_FEES (ID  BIGSERIAL NOT NULL, FC_AMOUNT NUMERIC(19, 2), FC_FEES_PARTICULAR_ID INT8, FC_SCHEME_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_STUDENT (ID  BIGSERIAL NOT NULL, ST_AGE INT4, ST_ALTERNATE_PHONE_NUMBER VARCHAR(255), ST_CASTE VARCHAR(255), ST_CODE VARCHAR(255), ST_CREATION_DATE TIMESTAMP, ST_DATE_OF_BIRTH TIMESTAMP, ST_ENGLISH_FLUENCY VARCHAR(255), ST_FATHER_OR_MOTHER_NAME VARCHAR(255), ST_FMS_PHOTO_ID INT8, ST_GENDER VARCHAR(255), ST_LAST_MODIFIED_DATE TIMESTAMP, ST_MAIL_ID VARCHAR(255), ST_MARITAL_STATUS VARCHAR(255), ST_NAME VARCHAR(255), ST_NATIONALITY VARCHAR(255), ST_PHONE_NUMBER VARCHAR(255), ST_RELIGION VARCHAR(255), ST_SCHOLAR_APP_NO VARCHAR(255), ST_STATUS VARCHAR(255), ST_ADDRESS_ID INT8, ST_BRANCH_ID INT8, ST_COURSE_ID INT8, ST_MD_HSC_ID INT8, ST_MARKETING_EMPLOYEE_ID INT8, ST_SCHEME_ID INT8, ST_MD_SSLC_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_STUDENT_FEES (ID  BIGSERIAL NOT NULL, FC_AMOUNT NUMERIC(19, 2), FC_FEES_PARTICULAR_ID INT8, SF_STUDENT_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_STUDENT_SCHOLARSHIP (ID  BIGSERIAL NOT NULL, STS_AGE INT4, STS_ANNUAL_INCOME VARCHAR(255), STS_APPLICATION_NUMBER VARCHAR(255), STS_CASTE VARCHAR(255), STS_CASTEDESCRIPTION VARCHAR(255), STS_CREATION_DATE TIMESTAMP, STS_DATE_OF_BIRTH TIMESTAMP, STS_EMAILID VARCHAR(255), STS_PARENT_NAME VARCHAR(255), STS_GENDER VARCHAR(255), STS_LAST_MODIFIED_DATE TIMESTAMP, STS_MARITAL_STATUS VARCHAR(255), STS_NAME VARCHAR(255), STS_NATIONALITY VARCHAR(255), STS_PARENT_PHONE_NUMBER VARCHAR(255), STS_RELIGION VARCHAR(255), STS_STATUS VARCHAR(255), STS_PHONE_NUMBER VARCHAR(255), STS_ADDRESS_ID INT8, STS_BRANCH_ID INT8, STS_MARKETING_EMPLOYEE_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_SUBJECT (ID  BIGSERIAL NOT NULL, SU_NAME VARCHAR(255) NOT NULL, SU_SECURED_MARK INT8 CHECK (SU_SECURED_MARK>=1), SU_TOTAL_MARK INT8 CHECK (SU_TOTAL_MARK>=1), SU_MD_ID INT8, PRIMARY KEY (ID));
CREATE TABLE SMS_TR_HOTEL (ID  BIGSERIAL NOT NULL, HL_CODE VARCHAR(255), HL_NAME VARCHAR(255),HL_CREATEDDATE VARCHAR(255),HL_STATUS VARCHAR(255),HL_BRANCH_ID bigint,Hl_PHONE_NUMBER VARCHAR(255),HL_ADDRESS_ID bigint,PRIMARY KEY (ID));
CREATE TABLE SMS_TR_HOTEL_HR (ID  BIGSERIAL NOT NULL, HR_NAME VARCHAR(255), HR_PHONE_NUMBER VARCHAR(255),HR_ADDRESS_ID bigint,HR_HOTEL_ID bigint,PRIMARY KEY (ID));
CREATE TABLE SMS_TR_MESSAGING_SERVICE (ID BIGSERIAL NOT NULL,MS_RECEIVER VARCHAR(255) NOT NULL,MS_SENDING_DATE DATE,MS_MESSAGE VARCHAR(255) NOT NULL,MS_STATUS VARCHAR(255) NOT NULL, MS_PHONENUMBER VARCHAR(255),PRIMARY KEY (ID))
CREATE TABLE SMS_TR_HOTEL_TRACKER (ID BIGSERIAL NOT NULL,HT_BRANCH_ID bigint,HT_HOTEL_ID bigint,HT_HOTEL_HR_ID bigint,HT_STUDENT_ID bigint, HT_DURATION_TO Date,HT_DURATION_FROM Date,HT_CREATED_DATE Date,HT_MODIFIED_DATE Date,HT_STATUS VARCHAR(255),HT_REMARKS VARCHAR(255),PRIMARY KEY (ID));

ALTER TABLE SMS_TR_HOTEL_HR ADD CONSTRAINT FK_HOTELHR_001 FOREIGN KEY (HR_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_HOTEL_HR ADD CONSTRAINT FK_HOTELHR_002 FOREIGN KEY (HR_HOTEL_ID) REFERENCES SMS_TR_HOTEL;
ALTER TABLE SMS_TR_HOTEL ADD CONSTRAINT UK_HL_001 UNIQUE (HL_CODE);
ALTER TABLE SMS_TR_HOTEL ADD CONSTRAINT FK_HOTEL_001 FOREIGN KEY (HL_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_HOTEL ADD CONSTRAINT FK_HOTEL_002 FOREIGN KEY (HL_BRANCH_ID) REFERENCES SMS_MA_BRANCH
ALTER TABLE SMS_TR_HOTEL_TRACKER ADD CONSTRAINT FK_HOTEL_TRACKER001 FOREIGN KEY (HT_BRANCH_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_HOTEL_TRACKER ADD CONSTRAINT FK_HOTEL_TRACKER002 FOREIGN KEY (HT_HOTEL_ID) REFERENCES SMS_TR_HOTEL;
ALTER TABLE SMS_TR_HOTEL_TRACKER ADD CONSTRAINT FK_HOTEL_TRACKER003 FOREIGN KEY (HT_HOTEL_HR_ID) REFERENCES SMS_TR_HOTEL;
ALTER TABLE SMS_TR_HOTEL_TRACKER ADD CONSTRAINT FK_HOTEL_TRACKER004 FOREIGN KEY (HT_STUDENT_ID) REFERENCES SMS_TR_HOTEL;
ALTER TABLE SMS_MA_BRANCH ADD CONSTRAINT UK_BR_001 UNIQUE (BR_CODE);
ALTER TABLE SMS_MA_COURSE ADD CONSTRAINT UK_CR_001 UNIQUE (CO_CODE);
ALTER TABLE SMS_MA_FEES_PARTICULAR ADD CONSTRAINT UK_FP_001 UNIQUE (FP_CODE);
ALTER TABLE SMS_MA_SCHEME ADD CONSTRAINT UK_SC_001 UNIQUE (SC_CODE);
ALTER TABLE SMS_MA_USER ADD CONSTRAINT UK_US_001 UNIQUE (US_NAME);
ALTER TABLE SMS_MA_USER_ROLE ADD CONSTRAINT UK_UR_001 UNIQUE (UR_NAME);
ALTER TABLE SMS_TR_MARKETING_EMPLOYEE ADD CONSTRAINT UK_ME_001 UNIQUE (ME_CODE);
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT UK_ST_001 UNIQUE (ST_CODE);
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT UK_ST_002 UNIQUE (ST_MAIL_ID);
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT UK_ST_003 UNIQUE (ST_MD_HSC_ID);
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT UK_ST_004 UNIQUE (ST_MD_SSLC_ID);
ALTER TABLE SMS_TR_STUDENT_SCHOLARSHIP ADD CONSTRAINT UK_STS_001 UNIQUE (STS_APPLICATION_NUMBER);
ALTER TABLE SMS_TR_STUDENT_SCHOLARSHIP ADD CONSTRAINT UK_STS_002 UNIQUE (STS_EMAILID);
ALTER TABLE SMS_MA_BRANCH ADD CONSTRAINT FK_BR_001 FOREIGN KEY (BR_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_MA_USER ADD CONSTRAINT FK_US_001 FOREIGN KEY (US_UR_ID) REFERENCES SMS_MA_USER_ROLE;
ALTER TABLE SMS_TR_EDUCATION_DETAIL ADD CONSTRAINT FK_ED_001 FOREIGN KEY (STS_ED_STUDENT_ID) REFERENCES SMS_TR_STUDENT_SCHOLARSHIP;
ALTER TABLE SMS_TR_EDUCATION_DETAIL ADD CONSTRAINT FK_ED_002 FOREIGN KEY (ED_STUDENT_ID) REFERENCES SMS_TR_STUDENT;
ALTER TABLE SMS_TR_GUARDIAN ADD CONSTRAINT FK_GD_001 FOREIGN KEY (GU_STUDENT_ID) REFERENCES SMS_TR_STUDENT;
ALTER TABLE SMS_TR_ID_CARD ADD CONSTRAINT FK_IC_001 FOREIGN KEY (IC_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_MARKETING_EMPLOYEE ADD CONSTRAINT FK_ME_001 FOREIGN KEY (ME_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_MARKETING_EMPLOYEE ADD CONSTRAINT FK_ME_002 FOREIGN KEY (ME_USER_ID) REFERENCES SMS_MA_USER;
ALTER TABLE SMS_TR_OTHER_LANGUAGE ADD CONSTRAINT FK_OL_001 FOREIGN KEY (OL_STUDENT_ID) REFERENCES SMS_TR_STUDENT;
ALTER TABLE SMS_TR_PAYMENT ADD CONSTRAINT FK_PA_001 FOREIGN KEY (SP_STUDENT_ID) REFERENCES SMS_TR_STUDENT;
ALTER TABLE SMS_TR_PAYMENT_FEES ADD CONSTRAINT FK_PF_001 FOREIGN KEY (FC_FEES_PARTICULAR_ID) REFERENCES SMS_MA_FEES_PARTICULAR;
ALTER TABLE SMS_TR_PAYMENT_FEES ADD CONSTRAINT FK_PF_002 FOREIGN KEY (SP_PAYMENT_ID) REFERENCES SMS_TR_PAYMENT;
ALTER TABLE SMS_TR_SCHEME_TO_FEES ADD CONSTRAINT FK_SF_001 FOREIGN KEY (FC_FEES_PARTICULAR_ID) REFERENCES SMS_MA_FEES_PARTICULAR;
ALTER TABLE SMS_TR_SCHEME_TO_FEES ADD CONSTRAINT FK_SF_002 FOREIGN KEY (FC_SCHEME_ID) REFERENCES SMS_MA_SCHEME;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_001 FOREIGN KEY (ST_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_002 FOREIGN KEY (ST_BRANCH_ID) REFERENCES SMS_MA_BRANCH;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_003 FOREIGN KEY (ST_COURSE_ID) REFERENCES SMS_MA_COURSE;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_004 FOREIGN KEY (ST_MD_HSC_ID) REFERENCES SMS_TR_MARK_DETAILS;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_005 FOREIGN KEY (ST_MARKETING_EMPLOYEE_ID) REFERENCES SMS_TR_MARKETING_EMPLOYEE;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_006 FOREIGN KEY (ST_SCHEME_ID) REFERENCES SMS_MA_SCHEME;
ALTER TABLE SMS_TR_STUDENT ADD CONSTRAINT FK_ST_007 FOREIGN KEY (ST_MD_SSLC_ID) REFERENCES SMS_TR_MARK_DETAILS;
ALTER TABLE SMS_TR_STUDENT_FEES ADD CONSTRAINT FK_STF_001 FOREIGN KEY (FC_FEES_PARTICULAR_ID) REFERENCES SMS_MA_FEES_PARTICULAR;
ALTER TABLE SMS_TR_STUDENT_FEES ADD CONSTRAINT FK_STF_002 FOREIGN KEY (SF_STUDENT_ID) REFERENCES SMS_TR_STUDENT;
ALTER TABLE SMS_TR_STUDENT_SCHOLARSHIP ADD CONSTRAINT FK_STS_001 FOREIGN KEY (STS_ADDRESS_ID) REFERENCES SMS_TR_ADDRESS;
ALTER TABLE SMS_TR_STUDENT_SCHOLARSHIP ADD CONSTRAINT FK_STS_002 FOREIGN KEY (STS_BRANCH_ID) REFERENCES SMS_MA_BRANCH;
ALTER TABLE SMS_TR_STUDENT_SCHOLARSHIP ADD CONSTRAINT FK_STS_003 FOREIGN KEY (STS_MARKETING_EMPLOYEE_ID) REFERENCES SMS_TR_MARKETING_EMPLOYEE;
ALTER TABLE SMS_TR_SUBJECT ADD CONSTRAINT FK_STS_004 FOREIGN KEY (SU_MD_ID) REFERENCES SMS_TR_MARK_DETAILS;

CREATE SEQUENCE SMS_SQ_BR INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_CO INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_FP INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_ROL INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_SC INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_SO INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_US INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_UR INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_AD INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_ED INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_GU INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_IC INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_ME INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_MD INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_OL INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_PY INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_PF INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_SF INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_ST INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_STF INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_STS INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_SU INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_MS INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_HL INCREMENT by 1 start with 1;
CREATE SEQUENCE SMS_SQ_HR INCREMENT by 1 start with 1;