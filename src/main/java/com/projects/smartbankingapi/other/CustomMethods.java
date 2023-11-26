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

    public String generateAccountNumber(String branchCode, String accountTypeCode, Long accountId) {
        String accountNumber = "";
        Integer year = LocalDate.now().getYear();
        Integer month = LocalDate.now().getMonthValue();
        String accountNumberPrefix = branchCode + accountTypeCode + year.toString().substring(2) + month;
        String accountNumberSuffix = String.format("%04d", accountId);
        accountNumber = accountNumberPrefix + accountNumberSuffix;
        return accountNumber;
    }
}
