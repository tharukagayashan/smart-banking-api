package com.projects.smartbankingapi.other;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class CustomMethods {

    public String validateNIC(String nic) {
        String msg = "";
        if (nic.length() == 10) {
            if (nic.charAt(9) == 'V' || nic.charAt(9) == 'v') {
                msg = "Valid NIC";
            } else {
                msg = "Invalid NIC";
            }
        } else if (nic.length() == 12) {
            msg = "Valid NIC";
        } else {
            msg = "Invalid NIC";
        }
        return msg;
    }

    public String generateAccountNumber(String bankCode, String branchCode, String accountTypeCode, Long accountId) {
        String accountNumber = "";
        Integer year = LocalDate.now().getYear();
        Integer month = LocalDate.now().getMonthValue();

        String accountNumberPrefix = bankCode + branchCode + accountTypeCode;
        String accountNumberSuffix = String.format("%06d", accountId);
        accountNumber = accountNumberPrefix + year + month + accountNumberSuffix;
        return accountNumber;
    }
}
