package com.projects.smartbankingapi.constant;

public class HardCodeConstant {
    public static final Integer STATUS_NEW_ID = 1;
    public static final Integer STATUS_PENDING_ID = 2;
    public static final Integer STATUS_APPROVED_ID = 3;

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


    public static final float CURRENCY_USD_BUYING_RATE = 325;
    public static final float CURRENCY_USD_SELLING_RATE = 330;
    public static final float CURRENCY_EUR_BUYING_RATE = 375;
    public static final float CURRENCY_EUR_SELLING_RATE = 380;
    public static final float CURRENCY_AUD_BUYING_RATE = 225;
    public static final float CURRENCY_AUD_SELLING_RATE = 230;
    public static final float CURRENCY_LKR_BUYING_RATE = 1;
    public static final float CURRENCY_LKR_SELLING_RATE = 1;

}