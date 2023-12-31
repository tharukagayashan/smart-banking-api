package com.projects.smartbankingapi.constant;

public class HardCodeConstant {
    public static final Integer STATUS_NEW_ID = 1;
    public static final Integer STATUS_PENDING_ID = 2;
    public static final Integer STATUS_APPROVED_ID = 3;
    public static final Integer STATUS_DISBURSED_ID = 4;
    public static final Integer STATUS_SETTLED_ID = 5;

    public static final Boolean ACTIVE = true;
    public static final Boolean INACTIVE = false;

    public static final Integer TRAN_TYPE_DEBIT_ID = 1;
    public static final Integer TRAN_TYPE_CREDIT_ID = 2;

    public static final String HEAD_OFFICE_ACCOUNT_NO = "00100123120001";

    public static final Integer LOAN_TYPE_FLAT_ID = 1;
    public static final Integer LOAN_TYPE_REDUCING_ID = 2;

    public static final Integer SAVING_ACCOUNT_TYPE_ID = 1;
    public static final Integer CHECK_ACCOUNT_TYPE_ID = 2;
    public static final Integer FIXED_ACCOUNT_TYPE_ID = 3;

    public static final Integer CURRENCY_LKR_ID = 1;

    public static final Integer BRANCH_HEAD_OFFICE_ID = 1;

    public static final float SAVING_MIN_BALANCE = 1000;
    public static final float CHECK_MIN_BALANCE = 10000;
    public static final float FIXED_MIN_BALANCE = 25000;

    public static final String CARD_TYPE_VISA = "VISA";
    public static final String CARD_TYPE_MASTER = "MASTER";

    public static final String CARD_NO_REGEX = "^[0-9]{16}$";

    public static final Integer LOAN_PAY_TYPE_DISBURSE_ID = 1;
    public static final Integer LOAN_PAY_TYPE_REPAYMENT_ID = 2;
    public static final Integer LOAN_PAY_TYPE_INTEREST_ID = 3;
    public static final Integer LOAN_PAY_TYPE_PENALTY_ID = 4;
    public static final Integer LOAN_PAY_TYPE_INSTALLMENT_ID = 5;
    public static final Integer LOAN_PAY_TYPE_INS_AND_INT_ID = 6;
}