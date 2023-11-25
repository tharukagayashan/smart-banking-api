CREATE SEQUENCE IF NOT EXISTS bn_m_acc_card_detail_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_m_account_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS bn_m_beneficiary_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS bn_m_card_detail_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_m_customer_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_m_loan_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS bn_r_account_type_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_bank_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_branch_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS bn_r_charge_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_currency_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_fee_type_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_int_rate_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_loan_period_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_loan_product_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_loan_type_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_status_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS bn_r_tran_type_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE bn_m_acc_card_details
(
    acc_card_id BIGINT NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_on  TIMESTAMP WITHOUT TIME ZONE,
    updated_on  TIMESTAMP WITHOUT TIME ZONE,
    account_id  BIGINT NOT NULL,
    card_id     BIGINT NOT NULL,
    CONSTRAINT pk_bn_m_acc_card_details PRIMARY KEY (acc_card_id)
);

CREATE TABLE bn_m_account
(
    account_id      BIGINT NOT NULL,
    created_by      VARCHAR(255),
    updated_by      VARCHAR(255),
    created_on      TIMESTAMP WITHOUT TIME ZONE,
    updated_on      TIMESTAMP WITHOUT TIME ZONE,
    account_no      VARCHAR(25),
    current_bal     FLOAT  NOT NULL,
    available_bal   FLOAT  NOT NULL,
    open_date       date,
    customer_id     BIGINT NOT NULL,
    account_type_id BIGINT NOT NULL,
    status_id       BIGINT NOT NULL,
    currency_id     BIGINT NOT NULL,
    branch_id       BIGINT NOT NULL,
    CONSTRAINT pk_bn_m_account PRIMARY KEY (account_id)
);

CREATE TABLE bn_m_beneficiary
(
    beneficiary_id BIGINT NOT NULL,
    created_by     VARCHAR(255),
    updated_by     VARCHAR(255),
    created_on     TIMESTAMP WITHOUT TIME ZONE,
    updated_on     TIMESTAMP WITHOUT TIME ZONE,
    full_name      VARCHAR(100),
    account_no     VARCHAR(25),
    nic            VARCHAR(12),
    mobile_no      VARCHAR(10),
    email          VARCHAR(60),
    account_id     BIGINT NOT NULL,
    branch_id      BIGINT NOT NULL,
    CONSTRAINT pk_bn_m_beneficiary PRIMARY KEY (beneficiary_id)
);

CREATE TABLE bn_m_card_detail
(
    card_id     BIGINT NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_on  TIMESTAMP WITHOUT TIME ZONE,
    updated_on  TIMESTAMP WITHOUT TIME ZONE,
    card_type   VARCHAR(20),
    card_no     VARCHAR(16),
    expiry_date VARCHAR(10),
    cvv         VARCHAR(3),
    CONSTRAINT pk_bn_m_card_detail PRIMARY KEY (card_id)
);

CREATE TABLE bn_m_customer
(
    customer_id BIGINT NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_on  TIMESTAMP WITHOUT TIME ZONE,
    updated_on  TIMESTAMP WITHOUT TIME ZONE,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50),
    dob         date,
    address     VARCHAR(200),
    email       VARCHAR(60),
    mobile_no   VARCHAR(10),
    nic         VARCHAR(12),
    gender      VARCHAR(1),
    is_active   BOOLEAN,
    CONSTRAINT pk_bn_m_customer PRIMARY KEY (customer_id)
);

CREATE TABLE bn_m_loan
(
    loan_id               BIGINT NOT NULL,
    created_by            VARCHAR(255),
    updated_by            VARCHAR(255),
    created_on            TIMESTAMP WITHOUT TIME ZONE,
    updated_on            TIMESTAMP WITHOUT TIME ZONE,
    amount                FLOAT  NOT NULL,
    interest              FLOAT  NOT NULL,
    tot_installments      INTEGER,
    next_installment_date date,
    tot_arrears_amt       FLOAT  NOT NULL,
    rem_installments      INTEGER,
    next_installment_amt  FLOAT  NOT NULL,
    distributed_amt       FLOAT  NOT NULL,
    tot_settled_amt       FLOAT  NOT NULL,
    tot_interest_paid     FLOAT  NOT NULL,
    tot_paid              FLOAT  NOT NULL,
    status_id             BIGINT NOT NULL,
    customer_id           BIGINT NOT NULL,
    loan_product_id       BIGINT NOT NULL,
    CONSTRAINT pk_bn_m_loan PRIMARY KEY (loan_id)
);

CREATE TABLE bn_r_account_type
(
    account_type_id   BIGINT NOT NULL,
    created_by        VARCHAR(255),
    updated_by        VARCHAR(255),
    created_on        TIMESTAMP WITHOUT TIME ZONE,
    updated_on        TIMESTAMP WITHOUT TIME ZONE,
    account_type_name VARCHAR(50),
    account_type_code VARCHAR(5),
    CONSTRAINT pk_bn_r_account_type PRIMARY KEY (account_type_id)
);

CREATE TABLE bn_r_bank
(
    bank_id    BIGINT NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_on TIMESTAMP WITHOUT TIME ZONE,
    updated_on TIMESTAMP WITHOUT TIME ZONE,
    bank_name  VARCHAR(45),
    bank_code  VARCHAR(5),
    is_active  BOOLEAN,
    CONSTRAINT pk_bn_r_bank PRIMARY KEY (bank_id)
);

CREATE TABLE bn_r_branch
(
    branch_id   BIGINT NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_on  TIMESTAMP WITHOUT TIME ZONE,
    updated_on  TIMESTAMP WITHOUT TIME ZONE,
    branch_name VARCHAR(50),
    branch_code VARCHAR(5),
    is_active   BOOLEAN,
    bank_id     BIGINT NOT NULL,
    CONSTRAINT pk_bn_r_branch PRIMARY KEY (branch_id)
);

CREATE TABLE bn_r_charge
(
    charge_id       BIGINT NOT NULL,
    created_by      VARCHAR(255),
    updated_by      VARCHAR(255),
    created_on      TIMESTAMP WITHOUT TIME ZONE,
    updated_on      TIMESTAMP WITHOUT TIME ZONE,
    charge_desc     VARCHAR(100),
    amount          FLOAT  NOT NULL,
    effective_date  date,
    expiration_date date,
    fee_type_id     BIGINT NOT NULL,
    currency_id     BIGINT NOT NULL,
    CONSTRAINT pk_bn_r_charge PRIMARY KEY (charge_id)
);

CREATE TABLE bn_r_currency
(
    currency_id   BIGINT NOT NULL,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255),
    created_on    TIMESTAMP WITHOUT TIME ZONE,
    updated_on    TIMESTAMP WITHOUT TIME ZONE,
    currency_name VARCHAR(45),
    currency_code VARCHAR(5),
    CONSTRAINT pk_bn_r_currency PRIMARY KEY (currency_id)
);

CREATE TABLE bn_r_fee_type
(
    fee_type_id   BIGINT NOT NULL,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255),
    created_on    TIMESTAMP WITHOUT TIME ZONE,
    updated_on    TIMESTAMP WITHOUT TIME ZONE,
    fee_type_name VARCHAR(45),
    fee_type_desc VARCHAR(100),
    CONSTRAINT pk_bn_r_fee_type PRIMARY KEY (fee_type_id)
);

CREATE TABLE bn_r_int_rate
(
    int_rate_id   BIGINT NOT NULL,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255),
    created_on    TIMESTAMP WITHOUT TIME ZONE,
    updated_on    TIMESTAMP WITHOUT TIME ZONE,
    int_rate_name VARCHAR(45),
    int_rate_desc VARCHAR(100),
    rate          FLOAT  NOT NULL,
    CONSTRAINT pk_bn_r_int_rate PRIMARY KEY (int_rate_id)
);

CREATE TABLE bn_r_loan_period
(
    loan_period_id   BIGINT NOT NULL,
    created_by       VARCHAR(255),
    updated_by       VARCHAR(255),
    created_on       TIMESTAMP WITHOUT TIME ZONE,
    updated_on       TIMESTAMP WITHOUT TIME ZONE,
    loan_period_name VARCHAR(45),
    loan_period_desc VARCHAR(100),
    month            INTEGER,
    CONSTRAINT pk_bn_r_loan_period PRIMARY KEY (loan_period_id)
);

CREATE TABLE bn_r_loan_product
(
    loan_product_id BIGINT NOT NULL,
    created_by      VARCHAR(255),
    updated_by      VARCHAR(255),
    created_on      TIMESTAMP WITHOUT TIME ZONE,
    updated_on      TIMESTAMP WITHOUT TIME ZONE,
    loan_type_id    BIGINT NOT NULL,
    int_rate_id     BIGINT NOT NULL,
    loan_period_id  BIGINT NOT NULL,
    CONSTRAINT pk_bn_r_loan_product PRIMARY KEY (loan_product_id)
);

CREATE TABLE bn_r_loan_type
(
    loan_type_id   BIGINT NOT NULL,
    created_by     VARCHAR(255),
    updated_by     VARCHAR(255),
    created_on     TIMESTAMP WITHOUT TIME ZONE,
    updated_on     TIMESTAMP WITHOUT TIME ZONE,
    loan_type_name VARCHAR(50),
    loan_type_code VARCHAR(5),
    loan_type_desc VARCHAR(100),
    CONSTRAINT pk_bn_r_loan_type PRIMARY KEY (loan_type_id)
);

CREATE TABLE bn_r_status
(
    status_id   BIGINT NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_on  TIMESTAMP WITHOUT TIME ZONE,
    updated_on  TIMESTAMP WITHOUT TIME ZONE,
    status_name VARCHAR(45),
    status_code VARCHAR(5),
    status_type VARCHAR(45),
    CONSTRAINT pk_bn_r_status PRIMARY KEY (status_id)
);

CREATE TABLE bn_r_tran_type
(
    tran_type_id   BIGINT NOT NULL,
    created_by     VARCHAR(255),
    updated_by     VARCHAR(255),
    created_on     TIMESTAMP WITHOUT TIME ZONE,
    updated_on     TIMESTAMP WITHOUT TIME ZONE,
    tran_type_name VARCHAR(45),
    tran_type_code VARCHAR(5),
    CONSTRAINT pk_bn_r_tran_type PRIMARY KEY (tran_type_id)
);

ALTER TABLE bn_m_account
    ADD CONSTRAINT uc_bn_m_account_account_no UNIQUE (account_no);

ALTER TABLE bn_m_card_detail
    ADD CONSTRAINT uc_bn_m_card_detail_card_no UNIQUE (card_no);

ALTER TABLE bn_m_customer
    ADD CONSTRAINT uc_bn_m_customer_nic UNIQUE (nic);

ALTER TABLE bn_r_account_type
    ADD CONSTRAINT uc_bn_r_account_type_account_type_code UNIQUE (account_type_code);

ALTER TABLE bn_r_bank
    ADD CONSTRAINT uc_bn_r_bank_bank_code UNIQUE (bank_code);

ALTER TABLE bn_r_branch
    ADD CONSTRAINT uc_bn_r_branch_branch_code UNIQUE (branch_code);

ALTER TABLE bn_r_currency
    ADD CONSTRAINT uc_bn_r_currency_currency_code UNIQUE (currency_code);

ALTER TABLE bn_r_loan_type
    ADD CONSTRAINT uc_bn_r_loan_type_loan_type_code UNIQUE (loan_type_code);

ALTER TABLE bn_r_status
    ADD CONSTRAINT uc_bn_r_status_status_code UNIQUE (status_code);

ALTER TABLE bn_r_tran_type
    ADD CONSTRAINT uc_bn_r_tran_type_tran_type_code UNIQUE (tran_type_code);

CREATE UNIQUE INDEX BN_M_ACCOUNT_ACCOUNT_NO_UNIQUE_IDX ON bn_m_account (account_no);

CREATE UNIQUE INDEX BN_M_CARD_DETAIL_CARD_NO_UNIQUE_IDX ON bn_m_card_detail (card_no);

CREATE UNIQUE INDEX BN_M_CUSTOMER_DOB_UNIQUE_IDX ON bn_m_customer (nic);

CREATE UNIQUE INDEX BN_R_ACCOUNT_TYPE_ACCOUNT_TYPE_CODE_IDX ON bn_r_account_type (account_type_code);

CREATE UNIQUE INDEX BN_R_BANK_BANK_CODE_IDX ON bn_r_bank (bank_code);

CREATE UNIQUE INDEX BN_R_BRANCH_BRANCH_CODE_IDX ON bn_r_branch (branch_code);

CREATE UNIQUE INDEX BN_R_CURRENCY_CURRENCY_CODE_IDX ON bn_r_currency (currency_code);

CREATE UNIQUE INDEX BN_R_LOAN_TYPE_LOAN_TYPE_CODE_IDX ON bn_r_loan_type (loan_type_code);

CREATE UNIQUE INDEX BN_R_STATUS_STATUS_CODE_IDX ON bn_r_status (status_code);

CREATE UNIQUE INDEX BN_R_TRAN_TYPE_TRAN_TYPE_CODE_IDX ON bn_r_tran_type (tran_type_code);

ALTER TABLE bn_m_account
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_ACCOUNT_TYPE FOREIGN KEY (account_type_id) REFERENCES bn_r_account_type (account_type_id);

CREATE INDEX BN_M_ACCOUNT_ACCOUNT_TYPE_ID_IDX ON bn_m_account (account_type_id);

ALTER TABLE bn_m_account
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES bn_r_branch (branch_id);

CREATE INDEX BN_M_ACCOUNT_BRANCH_ID_IDX ON bn_m_account (branch_id);

ALTER TABLE bn_m_account
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_CURRENCY FOREIGN KEY (currency_id) REFERENCES bn_r_currency (currency_id);

CREATE INDEX BN_M_ACCOUNT_CURRENCY_ID_IDX ON bn_m_account (currency_id);

ALTER TABLE bn_m_account
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES bn_m_customer (customer_id);

CREATE INDEX BN_M_ACCOUNT_CUSTOMER_ID_IDX ON bn_m_account (customer_id);

ALTER TABLE bn_m_account
    ADD CONSTRAINT FK_BN_M_ACCOUNT_ON_STATUS FOREIGN KEY (status_id) REFERENCES bn_r_status (status_id);

CREATE INDEX BN_M_ACCOUNT_STATUS_ID_IDX ON bn_m_account (status_id);

ALTER TABLE bn_m_acc_card_details
    ADD CONSTRAINT FK_BN_M_ACC_CARD_DETAILS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES bn_m_account (account_id);

CREATE INDEX BN_M_ACC_CARD_DETAILS_ACCOUNT_ID ON bn_m_acc_card_details (account_id);

ALTER TABLE bn_m_acc_card_details
    ADD CONSTRAINT FK_BN_M_ACC_CARD_DETAILS_ON_CARD FOREIGN KEY (card_id) REFERENCES bn_m_card_detail (card_id);

CREATE INDEX BN_M_ACC_CARD_DETAILS_CARD_ID ON bn_m_acc_card_details (card_id);

ALTER TABLE bn_m_beneficiary
    ADD CONSTRAINT FK_BN_M_BENEFICIARY_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES bn_m_account (account_id);

CREATE INDEX BN_R_BENEFICIARY_ACCOUNT_ID_IDX ON bn_m_beneficiary (account_id);

ALTER TABLE bn_m_beneficiary
    ADD CONSTRAINT FK_BN_M_BENEFICIARY_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES bn_r_branch (branch_id);

CREATE INDEX BN_R_BENEFICIARY_BRANCH_ID_IDX ON bn_m_beneficiary (branch_id);

ALTER TABLE bn_m_loan
    ADD CONSTRAINT FK_BN_M_LOAN_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES bn_m_customer (customer_id);

CREATE INDEX BN_M_LOAN_CUSTOMER_ID_IDX ON bn_m_loan (customer_id);

ALTER TABLE bn_m_loan
    ADD CONSTRAINT FK_BN_M_LOAN_ON_LOAN_PRODUCT FOREIGN KEY (loan_product_id) REFERENCES bn_r_loan_product (loan_product_id);

CREATE INDEX BN_M_LOAN_LOAN_PRODUCT_ID_IDX ON bn_m_loan (loan_product_id);

ALTER TABLE bn_m_loan
    ADD CONSTRAINT FK_BN_M_LOAN_ON_STATUS FOREIGN KEY (status_id) REFERENCES bn_r_status (status_id);

CREATE INDEX BN_M_LOAN_STATUS_ID_IDX ON bn_m_loan (status_id);

ALTER TABLE bn_r_branch
    ADD CONSTRAINT FK_BN_R_BRANCH_ON_BANK FOREIGN KEY (bank_id) REFERENCES bn_r_bank (bank_id);

CREATE INDEX BN_R_BRANCH_BANK_ID_IDX ON bn_r_branch (bank_id);

ALTER TABLE bn_r_charge
    ADD CONSTRAINT FK_BN_R_CHARGE_ON_CURRENCY FOREIGN KEY (currency_id) REFERENCES bn_r_currency (currency_id);

CREATE INDEX BN_R_CHARGE_CURRENCY_ID_IDX ON bn_r_charge (currency_id);

ALTER TABLE bn_r_charge
    ADD CONSTRAINT FK_BN_R_CHARGE_ON_FEE_TYPE FOREIGN KEY (fee_type_id) REFERENCES bn_r_fee_type (fee_type_id);

CREATE INDEX BN_R_CHARGE_FEE_TYPE_ID_IDX ON bn_r_charge (fee_type_id);

ALTER TABLE bn_r_loan_product
    ADD CONSTRAINT FK_BN_R_LOAN_PRODUCT_ON_INT_RATE FOREIGN KEY (int_rate_id) REFERENCES bn_r_int_rate (int_rate_id);

CREATE INDEX BN_R_LOAN_PRODUCT_INT_RATE_ID_IDX ON bn_r_loan_product (int_rate_id);

ALTER TABLE bn_r_loan_product
    ADD CONSTRAINT FK_BN_R_LOAN_PRODUCT_ON_LOAN_PERIOD FOREIGN KEY (loan_period_id) REFERENCES bn_r_loan_period (loan_period_id);

CREATE INDEX BN_R_LOAN_PRODUCT_LOAN_PERIOD_ID_IDX ON bn_r_loan_product (loan_period_id);

ALTER TABLE bn_r_loan_product
    ADD CONSTRAINT FK_BN_R_LOAN_PRODUCT_ON_LOAN_TYPE FOREIGN KEY (loan_type_id) REFERENCES bn_r_loan_type (loan_type_id);

CREATE INDEX BN_R_LOAN_PRODUCT_LOAN_TYPE_ID_IDX ON bn_r_loan_product (loan_type_id);