INSERT INTO SMS_MA_COURSE(ID, CO_CODE, CO_NAME, CO_DESCRIPTION) VALUES (NULL, 'C001', 'Hotel Management', 'Hotel Management');

-- user insertion
INSERT INTO SMS_MA_USER_ROLE(ID, UR_NAME, UR_DESC) VALUES (NULL, 'SUPER_ADMIN', 'Admin role');

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'branch', 'OPERATION' ,'branch pages related operations');
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 1);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/branch/**', 'URL' ,'branch pages related operations');
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 2);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'feesparticular', 'OPERATION' ,'feesparticular master pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 3);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/feesparticular/**', 'URL' ,'feesparticular master pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 4);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'user','OPERATION' , 'user pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 5);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/user/**','URL' , 'user pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 6);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'course','OPERATION' , 'course pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 7);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/course/**','URL' , 'course pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 8);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'home','OPERATION' , 'home page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 9);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/role/**','URL' , 'role pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 10);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'role','OPERATION' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 11);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'student','OPERATION' , 'student page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 12);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/student/**','URL' , 'student pages related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 13);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'marketingemployee','OPERATION' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 14);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/marketingemployee/**','URL' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 15);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'course','OPERATION' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 16);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/course/**','URL' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 17);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'scheme','OPERATION' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 18);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/scheme/**','URL' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 19);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'securedoperation','OPERATION' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 20);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/securedoperation/**','URL' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 21);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'roleoperationlink','OPERATION' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 22);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/roleoperationlink/**','URL' , 'marketing Employee page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 23);

INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, 'idcard','OPERATION' , 'idcard page related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 24);
INSERT INTO SMS_MA_SECURED_OPERATION(ID, SO_OPERATION, SO_TYPE, SO_OPERATION_DESC) VALUES(NULL, '/idcard/**','URL' , 'idcard url related operations')
INSERT INTO SMS_MA_ROLE_OPERATION_LINK(ID, ROL_UR_ID, ROL_SO_ID) VALUES(NULL, 1, 25);


INSERT INTO SMS_MA_USER (ID, US_FIRST_NAME, US_LAST_NAME, US_BRANCH, US_NAME, US_UR_ID, US_PASSWORD) VALUES (NULL, 'ADMIN', 'ADMIN', 'MMT01', 'ADMIN', 1, '$2a$10$IhHsRrddT9qVXke96pQqSO94hCzSlPolLHSElYFbKUaouVisr6g4W');

-- FEES_PARTICULAR INSERTION
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP001', 'Admission Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP002', 'Tution Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP003', 'Registration Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP004', 'Entrance Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP005', 'Uniform Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP006', 'Tamil/English Typing Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP007', 'Pratical/Lab Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP008', 'Book/Record Note Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP009', 'Exam Note Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP010', 'Hostel/Mess Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP011', 'Bus Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP012', 'Breakage Fees');
INSERT INTO SMS_MA_FEES_PARTICULAR (ID, FP_CODE, FP_NAME) VALUES (NULL, 'FP013', 'Other Fees');

--1.MELMARUVATHUR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES(NULL, 'MELMARUVATHUR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 1, 'MMT01', 1, 'MELMARUVATHUR BRANCH');

--2.MADURANTHAGAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'MADURANTHAGAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 2, 'MDT01', 1, 'MADURANTHAGAM BRANCH');

--3.CHENGALPATTU BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'CHENGALPATTU', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 3, 'CGP01', 1, 'CHENGALPATTU BRANCH');

--4.KANCHIPURAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'KANCHIPURAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 4, 'KCP01', 1, 'KANCHIPURAM BRANCH');

--5.CHEYYAR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'CHEYYAR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 5, 'CHY01', 1, 'CHEYYAR BRANCH');

--6.ARANI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'ARANI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 6, 'ARI01', 1, 'ARANI BRANCH');

--7.VELLORE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'VELLORE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 7, 'VLR01', 1, 'VELLORE BRANCH');

--8.CHETPATTU BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'CHETPATTU', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 8, 'CPT01', 1, 'CHETPATTU BRANCH');

--9.POLURE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'POLURE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 9, 'PLR01', 1, 'POLURE BRANCH');

--10.THIRUVANNAMALAI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'THIRUVANNAMALAI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 10, 'TVM01', 1, 'THIRUVANNAMALAI BRANCH');

--11.GINGEE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'GINGEE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 11, 'GNE01', 1, 'GINGEE BRANCH');

--12.CHENGAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'CHENGAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 12, 'CGM01', 1, 'CHENGAM BRANCH');

--13.THIRUVALLURE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'THIRUVALLURE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 13, 'TVR01', 1, 'THIRUVALLURE BRANCH');

--14.THIRUTHANI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'THIRUTHANI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 14, 'TTN01', 1, 'THIRUTHANI BRANCH');

--15.ARAKKONAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'ARAKKONAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 15, 'AKN01', 1, 'ARAKKONAM BRANCH');

--16.KALLAKURICHI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'KALLAKURICHI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 16, 'KKI01', 1, 'KALLAKURICHI BRANCH');

--17.SANGARAPURAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'SANGARAPURAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 17, 'SRP01', 1, 'SANGARAPURAM BRANCH');

--18.ULUNTHURPETTAI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'ULUNTHURPETTAI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 18, 'UDP01', 1, 'ULUNTHURPETTAI BRANCH');

--19.VILLUPURAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'VILLUPURAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 19, 'VPM01', 1, 'VILLUPURAM BRANCH');

--20.TINDIVANAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'TINDIVANAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 20, 'TDM01', 1, 'TINDIVANAM BRANCH');

--21.CUDALURE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'CUDALURE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 21, 'CDL01', 1, 'CUDALURE BRANCH');

--22.CHIDAMBARAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'CHIDAMBARAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 22, 'CDM01', 1, 'CHIDAMBARAM BRANCH');

--23.VANDAVASI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'VANDAVASI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 23, 'VDV01', 1, 'VANDAVASI BRANCH');

--24.UTHIRAMERUR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'UTHIRAMERUR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 24, 'UMR01', 1, 'UTHIRAMERUR BRANCH');

--25.PRAMBALURE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'PRAMBALURE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 25, 'PBL01', 1, 'PRAMBALURE BRANCH');

--26.ARIYALURE BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'ARIYALURE', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 26, 'AYL01', 1, 'ARIYALURE BRANCH');

--27.TRICHY BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'TRICHY', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 27, 'TRY01', 1, 'TRICHY BRANCH');

--28.THANJAVUR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'THANJAVUR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 28, 'TJV01', 1, 'THANJAVUR BRANCH');

--29.PATTUKOTTAI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'PATTUKOTTAI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 29, 'PTI01', 1, 'PATTUKOTTAI BRANCH');

--30.KUMBAKONAM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'KUMBAKONAM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 30, 'KBM01', 1, 'KUMBAKONAM BRANCH');

--31.PUTHUKOTTAI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'PUTHUKOTTAI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 31, 'PDI01', 1, 'PUTHUKOTTAI BRANCH');

--32.THURAIYUR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'THURAIYUR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 32, 'TRI01', 1, 'THURAIYUR BRANCH');

--33.MADURAI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'MADURAI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 33, 'MDI01', 1, 'MADURAI BRANCH');

--34.NAMAKKAL BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'NAMAKKAL', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 34, 'NMK01', 1, 'NAMAKKAL BRANCH');

--35.VIRUTHUNAGAR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'VIRUTHUNAGAR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 35, 'VDN01', 1, 'VIRUTHUNAGAR BRANCH');

--36.SALEM BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'SALEM', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 36, 'SLM01', 1, 'SALEM BRANCH');

--37.DINDUKAL BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'DINDUKAL', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 37, 'DDK01', 1, 'DINDUKAL BRANCH');

--38.DHARMAPURI BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'DHARMAPURI', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 38, 'DMP01', 1, 'DHARMAPURI BRANCH');

--39.HARUR BRANCH
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) VALUES (NULL, 'HARUR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_MA_BRANCH (ID, BR_ADDRESS_ID, BR_CODE, BR_ACTIVE, BR_NAME) VALUES (NULL, 39, 'HRR01', 1, 'HARUR BRANCH');


--TEST DATE NEED TO REMOVE AFTER TESTING
--1.STUDENT-SCHOLARSHIP
INSERT INTO SMS_TR_ADDRESS (ID, AD_TALUK,  AD_ADDRESS_1, AD_POSTAL_CODE, AD_DISTRICT, AD_ADDRESS_2) values (NULL, 'HARUR', '1', '600063', 'TAMIL NADU', 'GANDHI NAGAR');
INSERT INTO SMS_TR_EDUCATION_DETAIL (id, ED_EXAM_PASSED, ED_GROUP_NAME, ED_INSTITUTE_NAME, ED_PASSING_YEAR, ED_PERCENTAGE_OBTAINED, ED_REMARK) values (null, 'B.Sc', 'A.M.J.C', 'Computer Science', 2008, 75, 'Good');
INSERT INTO SMS_TR_EDUCATION_DETAIL (id, ED_EXAM_PASSED, ED_GROUP_NAME, ED_INSTITUTE_NAME, ED_PASSING_YEAR, ED_PERCENTAGE_OBTAINED, ED_REMARK) values (null, 'H.S.C', 'M.M.V', 'Geography', 2005, 68, 'Good');
INSERT INTO SMS_TR_EDUCATION_DETAIL (id, ED_EXAM_PASSED, ED_GROUP_NAME, ED_INSTITUTE_NAME, ED_PASSING_YEAR, ED_PERCENTAGE_OBTAINED, ED_REMARK) values (null, 'S.S.L.C', 'A.S.C.H.S', '', 2008, 75, 'Good');
INSERT INTO SMS_TR_GUARDIAN (id, GU_ANNUAL_INCOME, GU_GENDER, GU_IS_EMPLOYED, GU_MONTHLY_INCOME, GU_NAME, GU_OCCUPATION, GU_PHONE_NUMBER, GU_RELATION) values (null, 12000, 1, '1', 1000, 'Dad Name', 'Branch Manager','9776584689', 0);
INSERT INTO SMS_TR_GUARDIAN (id, GU_ANNUAL_INCOME, GU_GENDER, GU_IS_EMPLOYED, GU_MONTHLY_INCOME, GU_NAME, GU_OCCUPATION, GU_PHONE_NUMBER, GU_RELATION) values (null, 12000, 1, '1', 1000, 'Mom Name', 'Teacher','9861287946', 1);
INSERT INTO SMS_STD_SCHR(id, ST_ADDRESS_ID, ST_AGE, ST_CASTE, ST_CASTEE_OTHER, ST_CODE, ST_DATE_OF_BIRTH, ST_PARENT_NAME, ST_GENDER, ST_MARITAL_STATUS, ST_NAME, ST_NATIONALITY, ST_PARENT_PHONE_NUMBER, ST_RELIGION, ST_PHONE_NUMBER) values (null, 1, 24,'SC', 'SC', 'CH0116000001', '2014-07-02 06:14:00.742000000','Father Of KRISHNA','MALE', 'SINGLE', 'SRI KRISHNA','INDIAN','9861289603','HINDU', '9791884334');
--2.STUDENT-SCHOLARSHIP
INSERT INTO SMS_STD_SCHR(id, ST_ADDRESS_ID, ST_AGE, ST_CASTE, ST_CASTEE_OTHER, ST_CODE, ST_DATE_OF_BIRTH, ST_PARENT_NAME, ST_GENDER, ST_MARITAL_STATUS, ST_NAME, ST_NATIONALITY, ST_PARENT_PHONE_NUMBER, ST_RELIGION, ST_PHONE_NUMBER) values (null, 1, 24,'SC', 'SC', 'CH0116000002', '2014-07-02 06:14:00.742000000','Father Of KRISHNA','MALE', 'SINGLE', 'SRI KRISHNA','INDIAN','9861289603','HINDU', '9791884334');

INSERT INTO SMS_TR_ID_CARD(id, IC_UPLOADER_ID, IC_UPLOADER_CATEGORY, IC_FMS_ID, IC_VALIDITY, IC_STATUS, IC_CREATED_DATE, IC_LAST_MODIFIED_DATE) values (NULL, 'CH0116000002', 'STUDENT', 21, '2018-07-02 06:14:00.742000000', 'IN_PROGRESS', '2016-06-02 06:14:00.742000000', NULL );