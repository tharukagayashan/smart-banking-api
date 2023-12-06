package com.projects.smartbankingapi;

import com.projects.smartbankingapi.config.PasswordUtils;
import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.master.BnMCustomerRepository;
import com.projects.smartbankingapi.dao.master.BnMStaffRepository;
import com.projects.smartbankingapi.dao.reference.*;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.master.BnMCustomer;
import com.projects.smartbankingapi.model.master.BnMStaff;
import com.projects.smartbankingapi.model.reference.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Transactional
@SpringBootApplication
public class SmartBankingApiApplication {

    private static BnMCustomerRepository bnMCustomerRepository = null;
    private static BnMAccountRepository bnMAccountRepository = null;
    private static BnRAccountTypeRepository bnRAccountTypeRepository = null;
    private static BnRBranchRepository bnRBranchRepository = null;
    private static BnRCurrencyRepository bnRCurrencyRepository = null;
    private static BnRStatusRepository bnRStatusRepository = null;
    private static BnRBankRepository bnRBankRepository = null;
    private static BnRRoleRepository bnRRoleRepository = null;
    private static BnMStaffRepository bnMStaffRepository = null;

    public SmartBankingApiApplication(BnMCustomerRepository bnMCustomerRepository, BnMAccountRepository bnMAccountRepository,
                                      BnRAccountTypeRepository bnRAccountTypeRepository,
                                      BnRBranchRepository bnRBranchRepository,
                                      BnRCurrencyRepository bnRCurrencyRepository,
                                      BnRStatusRepository bnRStatusRepository,
                                      BnRBankRepository bnRBankRepository,
                                      BnRRoleRepository bnRRoleRepository, BnMStaffRepository bnMStaffRepository) {
        this.bnMCustomerRepository = bnMCustomerRepository;
        this.bnMAccountRepository = bnMAccountRepository;
        this.bnRAccountTypeRepository = bnRAccountTypeRepository;
        this.bnRBranchRepository = bnRBranchRepository;
        this.bnRCurrencyRepository = bnRCurrencyRepository;
        this.bnRStatusRepository = bnRStatusRepository;
        this.bnRBankRepository = bnRBankRepository;
        this.bnRRoleRepository = bnRRoleRepository;
        this.bnMStaffRepository = bnMStaffRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartBankingApiApplication.class, args);

        insertRecordsFromCommandLine();

    }

    public static void insertRecordsFromCommandLine() {

        Optional<BnRCurrency> optCurrency = bnRCurrencyRepository.findById(new Long(1));
        if (optCurrency.isPresent()) {
            log.info("Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Sri Lankan Rupee")
                    .code("LKR")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("Currency is saved");
        }

        Optional<BnRRole> optRole = bnRRoleRepository.findById(new Long(1));
        if (optRole.isPresent()) {
            log.info("Role is present");
        } else {
            BnRRole role;
            role = BnRRole.builder()
                    .roleName("Admin")
                    .description("Administrator")
                    .isActive(true)
                    .build();

            bnRRoleRepository.save(role);
            log.info("Role is saved");
        }

        Optional<BnRStatus> optStatus = bnRStatusRepository.findById(new Long(1));
        if (optStatus.isPresent()) {
            log.info("Status is present");
        } else {
            BnRStatus status;
            status = BnRStatus.builder()
                    .name("Active")
                    .code("ACT")
                    .type("A")
                    .build();

            bnRStatusRepository.save(status);
            log.info("Status is saved");
        }

        Optional<BnRAccountType> optAccountType = bnRAccountTypeRepository.findById(new Long(1));
        if (optAccountType.isPresent()) {
            log.info("Account Type is present");
        } else {
            BnRAccountType accountType;
            accountType = BnRAccountType.builder()
                    .name("Savings")
                    .code("SAV")
                    .build();

            bnRAccountTypeRepository.save(accountType);
            log.info("Account Type is saved");
        }

        Optional<BnRBank> optBank = bnRBankRepository.findById(new Long(1));
        if (optBank.isPresent()) {
            log.info("Bank is present");
        } else {
            BnRBank bank;
            bank = BnRBank.builder()
                    .name("Bank of Ceylon")
                    .code("BOC")
                    .isActive(true)
                    .build();

            bnRBankRepository.save(bank);
            log.info("Bank is saved");
        }


        Optional<BnRBranch> optBranch = bnRBranchRepository.findById(new Long(1));
        if (optBranch.isPresent()) {
            log.info("Branch is present");
        } else {

            Optional<BnRBank> optionalBank = bnRBankRepository.findById(new Long(1));
            if (optionalBank.isPresent()) {
                BnRBranch branch;
                branch = BnRBranch.builder()
                        .name("Head Office")
                        .code("HDO")
                        .isActive(true)
                        .bnRBank(optionalBank.get())
                        .build();
                bnRBranchRepository.save(branch);
                log.info("Branch is saved");
            } else {
                System.out.println("Bank is not present");
            }
        }

        Optional<BnMCustomer> optCustomer = bnMCustomerRepository.findById(new Long(1));
        if (optCustomer.isPresent()) {
            log.info("Customer is present");
        } else {
            BnMCustomer customer;
            customer = BnMCustomer.builder()
                    .firstName("Head Office")
                    .lastName("Account")
                    .nic("888888888V")
                    .address("Colombo, Sri Lanka")
                    .dob(LocalDate.of(1999, 01, 01))
                    .email("head-office@info.com")
                    .gender("")
                    .mobileNo("047XXXXXXX")
                    .isActive(true)
                    .build();
            bnMCustomerRepository.save(customer);
            log.info("Customer is saved");
        }

        Optional<BnMAccount> optAccount = bnMAccountRepository.findById(new Long(1));
        if (optAccount.isPresent()) {
            log.info("Account is present");
        } else {
            BnMAccount account;
            account = BnMAccount.builder()
                    .availableBalance(new Float(100000000))
                    .currentBalance(new Float(100000000))
                    .holdBalance(new Float(0))
                    .isActive(true)
                    .openedDate(LocalDate.now())
                    .bnMCustomer(bnMCustomerRepository.findById(new Long(1)).get())
                    .bnRAccountType(bnRAccountTypeRepository.findById(new Long(1)).get())
                    .bnRBranch(bnRBranchRepository.findById(new Long(1)).get())
                    .bnRCurrency(bnRCurrencyRepository.findById(new Long(1)).get())
                    .bnRStatus(bnRStatusRepository.findById(new Long(1)).get())
                    .build();
            bnMAccountRepository.save(account);
            log.info("Account is saved");
        }

        Optional<BnMStaff> optStaff = bnMStaffRepository.findById(new Long(1));
        if (optStaff.isPresent()) {
            log.info("Staff is present");
        } else {
            BnMStaff staff;

            String password = PasswordUtils.encodePassword("sysowner");

            staff = BnMStaff.builder()
                    .firstName("System")
                    .lastName("Owner")
                    .address("Colombo, Sri Lanka")
                    .email("sysowner@info.com")
                    .password(password)
                    .username("sysowner")
                    .isActive(true)
                    .mobileNo("047XXXXXXX")
                    .bnRRole(bnRRoleRepository.findById(new Long(1)).get())
                    .bnRBranch(bnRBranchRepository.findById(new Long(1)).get())
                    .build();

            bnMStaffRepository.save(staff);
            log.info("Staff is saved : username = sysowner && password = sysowner");
        }
    }

}
