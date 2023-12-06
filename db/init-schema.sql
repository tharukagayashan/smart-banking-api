CREATE SEQUENCE IF NOT EXISTS BN_M_ACC_CARD_DETAIL_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_M_ACCOUNT_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_M_BENEFICIARY_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_M_CARD_DETAIL_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_M_CUSTOMER_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_M_LOAN_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_ACCOUNT_TYPE_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_BANK_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_BRANCH_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_CHARGE_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_CURRENCY_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_FEE_TYPE_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_INT_RATE_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_LOAN_PERIOD_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_LOAN_PRODUCT_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_LOAN_TYPE_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_STATUS_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_R_TRAN_TYPE_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS BN_T_TRAN_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE BN_M_ACC_CARD_DETAILS
(
    ACC_CARD_ID BIGINT NOT NULL,
    CREATED_BY  VARCHAR(255),
    UPDATED_BY  VARCHAR(255),
    CREATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    ACCOUNT_ID  BIGINT NOT NULL,
    CARD_ID     BIGINT NOT NULL,
    CONSTRAINT PK_BN_M_ACC_CARD_DETAILS PRIMARY KEY (ACC_CARD_ID)
);

CREATE TABLE BN_M_ACCOUNT
(
    ACCOUNT_ID      BIGINT NOT NULL,
    CREATED_BY      VARCHAR(255),
    UPDATED_BY      VARCHAR(255),
    CREATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    ACCOUNT_NO      VARCHAR(25),
    CURRENT_BAL     FLOAT  NOT NULL,
    AVAILABLE_BAL   FLOAT  NOT NULL,
    HOLD_BAL        FLOAT  NOT NULL,
    OPEN_DATE       DATE,
    IS_ACTIVE       BOOLEAN,
    CUSTOMER_ID     BIGINT NOT NULL,
    ACCOUNT_TYPE_ID BIGINT NOT NULL,
    STATUS_ID       BIGINT NOT NULL,
    CURRENCY_ID     BIGINT NOT NULL,
    BRANCH_ID       BIGINT NOT NULL,
    CONSTRAINT PK_BN_M_ACCOUNT PRIMARY KEY (ACCOUNT_ID)
);

CREATE TABLE BN_M_BENEFICIARY
(
    BENEFICIARY_ID BIGINT NOT NULL,
    CREATED_BY     VARCHAR(255),
    UPDATED_BY     VARCHAR(255),
    CREATED_ON     TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON     TIMESTAMP WITHOUT TIME ZONE,
    FULL_NAME      VARCHAR(100),
    ACCOUNT_NO     VARCHAR(25),
    NIC            VARCHAR(12),
    MOBILE_NO      VARCHAR(10),
    EMAIL          VARCHAR(60),
    ACCOUNT_ID     BIGINT NOT NULL,
    BRANCH_ID      BIGINT NOT NULL,
    CONSTRAINT PK_BN_M_BENEFICIARY PRIMARY KEY (BENEFICIARY_ID)
);

CREATE TABLE BN_M_CARD_DETAIL
(
    CARD_ID     BIGINT NOT NULL,
    CREATED_BY  VARCHAR(255),
    UPDATED_BY  VARCHAR(255),
    CREATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    CARD_TYPE   VARCHAR(20),
    CARD_NO     VARCHAR(16),
    EXPIRY_DATE VARCHAR(10),
    CVV         VARCHAR(3),
    CONSTRAINT PK_BN_M_CARD_DETAIL PRIMARY KEY (CARD_ID)
);

CREATE TABLE BN_M_CUSTOMER
(
    CUSTOMER_ID BIGINT NOT NULL,
    CREATED_BY  VARCHAR(255),
    UPDATED_BY  VARCHAR(255),
    CREATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    FIRST_NAME  VARCHAR(50),
    LAST_NAME   VARCHAR(50),
    DOB         DATE,
    ADDRESS     VARCHAR(200),
    EMAIL       VARCHAR(60),
    MOBILE_NO   VARCHAR(10),
    NIC         VARCHAR(12),
    GENDER      VARCHAR(1),
    IS_ACTIVE   BOOLEAN,
    CONSTRAINT PK_BN_M_CUSTOMER PRIMARY KEY (CUSTOMER_ID)
);

CREATE TABLE BN_M_LOAN
(
    LOAN_ID               BIGINT NOT NULL,
    CREATED_BY            VARCHAR(255),
    UPDATED_BY            VARCHAR(255),
    CREATED_ON            TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON            TIMESTAMP WITHOUT TIME ZONE,
    AMOUNT                FLOAT  NOT NULL,
    INTEREST              FLOAT  NOT NULL,
    TOT_INSTALLMENTS      INTEGER,
    NEXT_INSTALLMENT_DATE DATE,
    TOT_ARREARS_AMT       FLOAT  NOT NULL,
    REM_INSTALLMENTS      INTEGER,
    NEXT_INSTALLMENT_AMT  FLOAT  NOT NULL,
    DISTRIBUTED_AMT       FLOAT  NOT NULL,
    TOT_SETTLED_AMT       FLOAT  NOT NULL,
    TOT_INTEREST_PAID     FLOAT  NOT NULL,
    TOT_PAID              FLOAT  NOT NULL,
    STATUS_ID             BIGINT NOT NULL,
    CUSTOMER_ID           BIGINT NOT NULL,
    LOAN_PRODUCT_ID       BIGINT NOT NULL,
    CONSTRAINT PK_BN_M_LOAN PRIMARY KEY (LOAN_ID)
);

CREATE TABLE BN_R_ACCOUNT_TYPE
(
    ACCOUNT_TYPE_ID   BIGINT NOT NULL,
    CREATED_BY        VARCHAR(255),
    UPDATED_BY        VARCHAR(255),
    CREATED_ON        TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON        TIMESTAMP WITHOUT TIME ZONE,
    ACCOUNT_TYPE_NAME VARCHAR(50),
    ACCOUNT_TYPE_CODE VARCHAR(5),
    CONSTRAINT PK_BN_R_ACCOUNT_TYPE PRIMARY KEY (ACCOUNT_TYPE_ID)
);

CREATE TABLE BN_R_BANK
(
    BANK_ID    BIGINT NOT NULL,
    CREATED_BY VARCHAR(255),
    UPDATED_BY VARCHAR(255),
    CREATED_ON TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON TIMESTAMP WITHOUT TIME ZONE,
    BANK_NAME  VARCHAR(45),
    BANK_CODE  VARCHAR(5),
    IS_ACTIVE  BOOLEAN,
    CONSTRAINT PK_BN_R_BANK PRIMARY KEY (BANK_ID)
);

CREATE TABLE BN_R_BRANCH
(
    BRANCH_ID   BIGINT NOT NULL,
    CREATED_BY  VARCHAR(255),
    UPDATED_BY  VARCHAR(255),
    CREATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    BRANCH_NAME VARCHAR(50),
    BRANCH_CODE VARCHAR(5),
    IS_ACTIVE   BOOLEAN,
    BANK_ID     BIGINT NOT NULL,
    CONSTRAINT PK_BN_R_BRANCH PRIMARY KEY (BRANCH_ID)
);

CREATE TABLE BN_R_CHARGE
(
    CHARGE_ID       BIGINT NOT NULL,
    CREATED_BY      VARCHAR(255),
    UPDATED_BY      VARCHAR(255),
    CREATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    CHARGE_DESC     VARCHAR(100),
    AMOUNT          FLOAT  NOT NULL,
    EFFECTIVE_DATE  DATE,
    EXPIRATION_DATE DATE,
    FEE_TYPE_ID     BIGINT NOT NULL,
    CURRENCY_ID     BIGINT NOT NULL,
    CONSTRAINT PK_BN_R_CHARGE PRIMARY KEY (CHARGE_ID)
);

CREATE TABLE BN_R_CURRENCY
(
    CURRENCY_ID   BIGINT NOT NULL,
    CREATED_BY    VARCHAR(255),
    UPDATED_BY    VARCHAR(255),
    CREATED_ON    TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON    TIMESTAMP WITHOUT TIME ZONE,
    CURRENCY_NAME VARCHAR(45),
    CURRENCY_CODE VARCHAR(5),
    CONSTRAINT PK_BN_R_CURRENCY PRIMARY KEY (CURRENCY_ID)
);

CREATE TABLE BN_R_FEE_TYPE
(
    FEE_TYPE_ID   BIGINT NOT NULL,
    CREATED_BY    VARCHAR(255),
    UPDATED_BY    VARCHAR(255),
    CREATED_ON    TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON    TIMESTAMP WITHOUT TIME ZONE,
    FEE_TYPE_NAME VARCHAR(45),
    FEE_TYPE_DESC VARCHAR(100),
    CONSTRAINT PK_BN_R_FEE_TYPE PRIMARY KEY (FEE_TYPE_ID)
);

CREATE TABLE BN_R_INT_RATE
(
    INT_RATE_ID   BIGINT NOT NULL,
    CREATED_BY    VARCHAR(255),
    UPDATED_BY    VARCHAR(255),
    CREATED_ON    TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON    TIMESTAMP WITHOUT TIME ZONE,
    INT_RATE_NAME VARCHAR(45),
    INT_RATE_DESC VARCHAR(100),
    RATE          FLOAT  NOT NULL,
    CONSTRAINT PK_BN_R_INT_RATE PRIMARY KEY (INT_RATE_ID)
);

CREATE TABLE BN_R_LOAN_PERIOD
(
    LOAN_PERIOD_ID   BIGINT NOT NULL,
    CREATED_BY       VARCHAR(255),
    UPDATED_BY       VARCHAR(255),
    CREATED_ON       TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON       TIMESTAMP WITHOUT TIME ZONE,
    LOAN_PERIOD_NAME VARCHAR(45),
    LOAN_PERIOD_DESC VARCHAR(100),
    MONTH            INTEGER,
    CONSTRAINT PK_BN_R_LOAN_PERIOD PRIMARY KEY (LOAN_PERIOD_ID)
);

CREATE TABLE BN_R_LOAN_PRODUCT
(
    LOAN_PRODUCT_ID BIGINT NOT NULL,
    CREATED_BY      VARCHAR(255),
    UPDATED_BY      VARCHAR(255),
    CREATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    LOAN_TYPE_ID    BIGINT NOT NULL,
    INT_RATE_ID     BIGINT NOT NULL,
    LOAN_PERIOD_ID  BIGINT NOT NULL,
    CONSTRAINT PK_BN_R_LOAN_PRODUCT PRIMARY KEY (LOAN_PRODUCT_ID)
);

CREATE TABLE BN_R_LOAN_TYPE
(
    LOAN_TYPE_ID   BIGINT NOT NULL,
    CREATED_BY     VARCHAR(255),
    UPDATED_BY     VARCHAR(255),
    CREATED_ON     TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON     TIMESTAMP WITHOUT TIME ZONE,
    LOAN_TYPE_NAME VARCHAR(50),
    LOAN_TYPE_CODE VARCHAR(5),
    LOAN_TYPE_DESC VARCHAR(100),
    CONSTRAINT PK_BN_R_LOAN_TYPE PRIMARY KEY (LOAN_TYPE_ID)
);

CREATE TABLE BN_R_STATUS
(
    STATUS_ID   BIGINT NOT NULL,
    CREATED_BY  VARCHAR(255),
    UPDATED_BY  VARCHAR(255),
    CREATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON  TIMESTAMP WITHOUT TIME ZONE,
    STATUS_NAME VARCHAR(45),
    STATUS_CODE VARCHAR(5),
    STATUS_TYPE VARCHAR(45),
    CONSTRAINT PK_BN_R_STATUS PRIMARY KEY (STATUS_ID)
);

CREATE TABLE BN_R_TRAN_TYPE
(
    TRAN_TYPE_ID   BIGINT NOT NULL,
    CREATED_BY     VARCHAR(255),
    UPDATED_BY     VARCHAR(255),
    CREATED_ON     TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON     TIMESTAMP WITHOUT TIME ZONE,
    TRAN_TYPE_NAME VARCHAR(45),
    TRAN_TYPE_CODE VARCHAR(5),
    CONSTRAINT PK_BN_R_TRAN_TYPE PRIMARY KEY (TRAN_TYPE_ID)
);

CREATE TABLE BN_T_TRAN
(
    TRAN_ID         BIGINT       NOT NULL,
    CREATED_BY      VARCHAR(255),
    UPDATED_BY      VARCHAR(255),
    CREATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    UPDATED_ON      TIMESTAMP WITHOUT TIME ZONE,
    AMOUNT          FLOAT        NOT NULL,
    TRAN_DATE       DATE         NOT NULL,
    TRAN_TIME       TIME WITHOUT TIME ZONE NOT NULL,
    TRAN_TYPE_ID    BIGINT       NOT NULL,
    DESCRIPTION     VARCHAR(255) NOT NULL,
    FROM_ACCOUNT_NO VARCHAR(255) NOT NULL,
    TO_ACCOUNT_NO   VARCHAR(255) NOT NULL,
    STATUS_ID       BIGINT       NOT NULL,
    CONSTRAINT PK_BN_T_TRAN PRIMARY KEY (TRAN_ID)
);

ALTER TABLE BN_M_ACCOUNT
    ADD CONSTRAINT UC_BN_M_ACCOUNT_ACCOUNT_NO UNIQUE (ACCOUNT_NO);

ALTER TABLE BN_M_CARD_DETAIL
    ADD CONSTRAINT UC_BN_M_CARD_DETAIL_CARD_NO UNIQUE (CARD_NO);

ALTER TABLE BN_M_CUSTOMER
    ADD CONSTRAINT UC_BN_M_CUSTOMER_NIC UNIQUE (NIC);

ALTER TABLE BN_R_ACCOUNT_TYPE
    ADD CONSTRAINT UC_BN_R_ACCOUNT_TYPE_ACCOUNT_TYPE_CODE UNIQUE (ACCOUNT_TYPE_CODE);

ALTER TABLE BN_R_BANK
    ADD CONSTRAINT UC_BN_R_BANK_BANK_CODE UNIQUE (BANK_CODE);

ALTER TABLE BN_R_BRANCH
    ADD CONSTRAINT UC_BN_R_BRANCH_BRANCH_CODE UNIQUE (BRANCH_CODE);

ALTER TABLE BN_R_CURRENCY
    ADD CONSTRAINT UC_BN_R_CURRENCY_CURRENCY_CODE UNIQUE (CURRENCY_CODE);

ALTER TABLE BN_R_LOAN_TYPE
    ADD CONSTRAINT UC_BN_R_LOAN_TYPE_LOAN_TYPE_CODE UNIQUE (LOAN_TYPE_CODE);

ALTER TABLE BN_R_STATUS
    ADD CONSTRAINT UC_BN_R_STATUS_STATUS_CODE UNIQUE (STATUS_CODE);

ALTER TABLE BN_R_TRAN_TYPE
    ADD CONSTRAINT UC_BN_R_TRAN_TYPE_TRAN_TYPE_CODE UNIQUE (TRAN_TYPE_CODE);

CREATE UNIQUE INDEX BN_M_ACCOUNT_ACCOUNT_NO_UNIQUE_IDX ON BN_M_ACCOUNT (ACCOUNT_NO);

CREATE UNIQUE INDEX BN_M_CARD_DETAIL_CARD_NO_UNIQUE_IDX ON BN_M_CARD_DETAIL (CARD_NO);

CREATE UNIQUE INDEX BN_M_CUSTOMER_DOB_UNIQUE_IDX ON BN_M_CUSTOMER (NIC);

CREATE UNIQUE INDEX BN_R_ACCOUNT_TYPE_ACCOUNT_TYPE_CODE_IDX ON BN_R_ACCOUNT_TYPE (ACCOUNT_TYPE_CODE);

CREATE UNIQUE INDEX BN_R_BANK_BANK_CODE_IDX ON BN_R_BANK (BANK_CODE);

CREATE UNIQUE INDEX BN_R_BRANCH_BRANCH_CODE_IDX ON BN_R_BRANCH (BRANCH_CODE);

CREATE UNIQUE INDEX BN_R_CURRENCY_CURRENCY_CODE_IDX ON BN_R_CURRENCY (CURRENCY_CODE);

CREATE UNIQUE INDEX BN_R_LOAN_TYPE_LOAN_TYPE_CODE_IDX ON BN_R_LOAN_TYPE (LOAN_TYPE_CODE);

CREATE UNIQUE INDEX BN_R_STATUS_STATUS_CODE_IDX ON BN_R_STATUS (STATUS_CODE);

CREATE UNIQUE INDEX BN_R_TRAN_TYPE_TRAN_TYPE_CODE_IDX ON BN_R_TRAN_TYPE (TRAN_TYPE_CODE);

ALTER TABLE BN_M_ACCOUNT
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_ACCOUNT_TYPE FOREIGN KEY (ACCOUNT_TYPE_ID) REFERENCES BN_R_ACCOUNT_TYPE (ACCOUNT_TYPE_ID);

CREATE INDEX BN_M_ACCOUNT_ACCOUNT_TYPE_ID_IDX ON BN_M_ACCOUNT (ACCOUNT_TYPE_ID);

ALTER TABLE BN_M_ACCOUNT
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_BRANCH FOREIGN KEY (BRANCH_ID) REFERENCES BN_R_BRANCH (BRANCH_ID);

CREATE INDEX BN_M_ACCOUNT_BRANCH_ID_IDX ON BN_M_ACCOUNT (BRANCH_ID);

ALTER TABLE BN_M_ACCOUNT
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_CURRENCY FOREIGN KEY (CURRENCY_ID) REFERENCES BN_R_CURRENCY (CURRENCY_ID);

CREATE INDEX BN_M_ACCOUNT_CURRENCY_ID_IDX ON BN_M_ACCOUNT (CURRENCY_ID);

ALTER TABLE BN_M_ACCOUNT
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES BN_M_CUSTOMER (CUSTOMER_ID);

CREATE INDEX BN_M_ACCOUNT_CUSTOMER_ID_IDX ON BN_M_ACCOUNT (CUSTOMER_ID);

ALTER TABLE BN_M_ACCOUNT
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_STATUS FOREIGN KEY (STATUS_ID) REFERENCES BN_R_STATUS (STATUS_ID);

CREATE INDEX BN_M_ACCOUNT_STATUS_ID_IDX ON BN_M_ACCOUNT (STATUS_ID);

ALTER TABLE BN_M_ACC_CARD_DETAILS
    ADD CONSTRAINT FK_BN_M_ACC_CARD_DETAILS_ON_ACCOUNT FOREIGN KEY (ACCOUNT_ID) REFERENCES BN_M_ACCOUNT (ACCOUNT_ID);

CREATE INDEX BN_M_ACC_CARD_DETAILS_ACCOUNT_ID ON BN_M_ACC_CARD_DETAILS (ACCOUNT_ID);

ALTER TABLE BN_M_ACC_CARD_DETAILS
    ADD CONSTRAINT FK_BN_M_ACC_CARD_DETAILS_ON_CARD FOREIGN KEY (CARD_ID) REFERENCES BN_M_CARD_DETAIL (CARD_ID);

CREATE INDEX BN_M_ACC_CARD_DETAILS_CARD_ID ON BN_M_ACC_CARD_DETAILS (CARD_ID);

ALTER TABLE BN_M_BENEFICIARY
    ADD CONSTRAINT FK_BN_M_BENEFICIARY_ON_ACCOUNT FOREIGN KEY (ACCOUNT_ID) REFERENCES BN_M_ACCOUNT (ACCOUNT_ID);

CREATE INDEX BN_R_BENEFICIARY_ACCOUNT_ID_IDX ON BN_M_BENEFICIARY (ACCOUNT_ID);

ALTER TABLE BN_M_BENEFICIARY
    ADD CONSTRAINT FK_BN_M_BENEFICIARY_ON_BRANCH FOREIGN KEY (BRANCH_ID) REFERENCES BN_R_BRANCH (BRANCH_ID);

CREATE INDEX BN_R_BENEFICIARY_BRANCH_ID_IDX ON BN_M_BENEFICIARY (BRANCH_ID);

ALTER TABLE BN_M_LOAN
    ADD CONSTRAINT FK_BN_M_LOAN_ON_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES BN_M_CUSTOMER (CUSTOMER_ID);

CREATE INDEX BN_M_LOAN_CUSTOMER_ID_IDX ON BN_M_LOAN (CUSTOMER_ID);

ALTER TABLE BN_M_LOAN
    ADD CONSTRAINT FK_BN_M_LOAN_ON_LOAN_PRODUCT FOREIGN KEY (LOAN_PRODUCT_ID) REFERENCES BN_R_LOAN_PRODUCT (LOAN_PRODUCT_ID);

CREATE INDEX BN_M_LOAN_LOAN_PRODUCT_ID_IDX ON BN_M_LOAN (LOAN_PRODUCT_ID);

ALTER TABLE BN_M_LOAN
    ADD CONSTRAINT FK_BN_M_LOAN_ON_STATUS FOREIGN KEY (STATUS_ID) REFERENCES BN_R_STATUS (STATUS_ID);

CREATE INDEX BN_M_LOAN_STATUS_ID_IDX ON BN_M_LOAN (STATUS_ID);

ALTER TABLE BN_R_BRANCH
    ADD CONSTRAINT FK_BN_R_BRANCH_ON_BANK FOREIGN KEY (BANK_ID) REFERENCES BN_R_BANK (BANK_ID);

CREATE INDEX BN_R_BRANCH_BANK_ID_IDX ON BN_R_BRANCH (BANK_ID);

ALTER TABLE BN_R_CHARGE
    ADD CONSTRAINT FK_BN_R_CHARGE_ON_CURRENCY FOREIGN KEY (CURRENCY_ID) REFERENCES BN_R_CURRENCY (CURRENCY_ID);

CREATE INDEX BN_R_CHARGE_CURRENCY_ID_IDX ON BN_R_CHARGE (CURRENCY_ID);

ALTER TABLE BN_R_CHARGE
    ADD CONSTRAINT FK_BN_R_CHARGE_ON_FEE_TYPE FOREIGN KEY (FEE_TYPE_ID) REFERENCES BN_R_FEE_TYPE (FEE_TYPE_ID);

CREATE INDEX BN_R_CHARGE_FEE_TYPE_ID_IDX ON BN_R_CHARGE (FEE_TYPE_ID);

ALTER TABLE BN_R_LOAN_PRODUCT
    ADD CONSTRAINT FK_BN_R_LOAN_PRODUCT_ON_INT_RATE FOREIGN KEY (INT_RATE_ID) REFERENCES BN_R_INT_RATE (INT_RATE_ID);

CREATE INDEX BN_R_LOAN_PRODUCT_INT_RATE_ID_IDX ON BN_R_LOAN_PRODUCT (INT_RATE_ID);

ALTER TABLE BN_R_LOAN_PRODUCT
    ADD CONSTRAINT FK_BN_R_LOAN_PRODUCT_ON_LOAN_PERIOD FOREIGN KEY (LOAN_PERIOD_ID) REFERENCES BN_R_LOAN_PERIOD (LOAN_PERIOD_ID);

CREATE INDEX BN_R_LOAN_PRODUCT_LOAN_PERIOD_ID_IDX ON BN_R_LOAN_PRODUCT (LOAN_PERIOD_ID);

ALTER TABLE BN_R_LOAN_PRODUCT
    ADD CONSTRAINT FK_BN_R_LOAN_PRODUCT_ON_LOAN_TYPE FOREIGN KEY (LOAN_TYPE_ID) REFERENCES BN_R_LOAN_TYPE (LOAN_TYPE_ID);

CREATE INDEX BN_R_LOAN_PRODUCT_LOAN_TYPE_ID_IDX ON BN_R_LOAN_PRODUCT (LOAN_TYPE_ID);

ALTER TABLE BN_T_TRAN
    ADD CONSTRAINT FK_BN_T_TRAN_ON_STATUS FOREIGN KEY (STATUS_ID) REFERENCES BN_R_STATUS (STATUS_ID);

ALTER TABLE BN_T_TRAN
    ADD CONSTRAINT FK_BN_T_TRAN_ON_TRAN_TYPE FOREIGN KEY (TRAN_TYPE_ID) REFERENCES BN_R_TRAN_TYPE (TRAN_TYPE_ID);

CREATE INDEX BN_T_TRAN_TRAN_TYPE_ID_IDX ON BN_T_TRAN (TRAN_TYPE_ID);