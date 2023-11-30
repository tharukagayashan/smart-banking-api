package com.projects.smartbankingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@Transactional
@SpringBootApplication
public class SmartBankingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartBankingApiApplication.class, args);
    }

}
