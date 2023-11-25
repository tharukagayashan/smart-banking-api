package com.projects.smartbankingapi.other;

import org.springframework.context.annotation.Configuration;

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
}
