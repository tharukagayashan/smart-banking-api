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
import com.projects.smartbankingapi.other.CustomMethods;
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
    private static BnRTranTypeRepository bnRTranTypeRepository = null;

    public SmartBankingApiApplication(BnMCustomerRepository bnMCustomerRepository, BnMAccountRepository bnMAccountRepository,
                                      BnRAccountTypeRepository bnRAccountTypeRepository,
                                      BnRBranchRepository bnRBranchRepository,
                                      BnRCurrencyRepository bnRCurrencyRepository,
                                      BnRStatusRepository bnRStatusRepository,
                                      BnRBankRepository bnRBankRepository,
                                      BnRRoleRepository bnRRoleRepository, BnMStaffRepository bnMStaffRepository, BnRTranTypeRepository bnRTranTypeRepository) {
        this.bnMCustomerRepository = bnMCustomerRepository;
        this.bnMAccountRepository = bnMAccountRepository;
        this.bnRAccountTypeRepository = bnRAccountTypeRepository;
        this.bnRBranchRepository = bnRBranchRepository;
        this.bnRCurrencyRepository = bnRCurrencyRepository;
        this.bnRStatusRepository = bnRStatusRepository;
        this.bnRBankRepository = bnRBankRepository;
        this.bnRRoleRepository = bnRRoleRepository;
        this.bnMStaffRepository = bnMStaffRepository;
        this.bnRTranTypeRepository = bnRTranTypeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartBankingApiApplication.class, args);

        insertRecordsFromCommandLine();

    }

    public static void insertRecordsFromCommandLine() {

        Optional<BnRTranType> optTranType1 = bnRTranTypeRepository.findById(new Long(1));
        if (optTranType1.isPresent()) {
            log.info("Debit Tran Type is present");
        } else {
            BnRTranType tranType;
            tranType = BnRTranType.builder()
                    .name("Debit")
                    .code("DEB")
                    .build();

            bnRTranTypeRepository.save(tranType);
            log.info("Debit Tran Type is saved");
        }

        Optional<BnRTranType> optTranType2 = bnRTranTypeRepository.findById(new Long(2));
        if (optTranType2.isPresent()) {
            log.info("Credit Tran Type is present");
        } else {
            BnRTranType tranType;
            tranType = BnRTranType.builder()
                    .name("Credit")
                    .code("CRE")
                    .build();

            bnRTranTypeRepository.save(tranType);
            log.info("Credit Tran Type is saved");
        }

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

        Optional<BnRStatus> optStatus1 = bnRStatusRepository.findById(new Long(1));
        if (optStatus1.isPresent()) {
            log.info("New Status is present");
        } else {
            BnRStatus status;
            status = BnRStatus.builder()
                    .name("New")
                    .code("NEW")
                    .type("A")
                    .build();

            bnRStatusRepository.save(status);
            log.info("New Status is saved");
        }

        Optional<BnRStatus> optStatus2 = bnRStatusRepository.findById(new Long(2));
        if (optStatus2.isPresent()) {
            log.info("Pending Status is present");
        } else {
            BnRStatus status;
            status = BnRStatus.builder()
                    .name("Pending")
                    .code("PND")
                    .type("A")
                    .build();

            bnRStatusRepository.save(status);
            log.info("Pending Status is saved");
        }

        Optional<BnRStatus> optStatus3 = bnRStatusRepository.findById(new Long(3));
        if (optStatus3.isPresent()) {
            log.info("Approve Status is present");
        } else {
            BnRStatus status;
            status = BnRStatus.builder()
                    .name("Approve")
                    .code("APR")
                    .type("A")
                    .build();

            bnRStatusRepository.save(status);
            log.info("Approve Status is saved");
        }

        String accountTypeCode = "";
        Optional<BnRAccountType> optAccountType1 = bnRAccountTypeRepository.findById(new Long(1));
        if (optAccountType1.isPresent()) {
            log.info("Saving Account Type is present");
        } else {
            BnRAccountType accountType;
            accountType = BnRAccountType.builder()
                    .name("Savings")
                    .code("SAV")
                    .build();

            bnRAccountTypeRepository.save(accountType);
            log.info("Saving Account Type is saved");
        }

        Optional<BnRAccountType> optAccountType2 = bnRAccountTypeRepository.findById(new Long(2));
        if (optAccountType2.isPresent()) {
            log.info("Check Account Type is present");
        } else {
            BnRAccountType accountType;
            accountType = BnRAccountType.builder()
                    .name("Check")
                    .code("CHK")
                    .build();

            bnRAccountTypeRepository.save(accountType);
            log.info("Check account Type is saved");
        }

        Optional<BnRAccountType> optAccountType3 = bnRAccountTypeRepository.findById(new Long(3));
        if (optAccountType3.isPresent()) {
            log.info("Fixed Deposit Account Type is present");
        } else {
            BnRAccountType accountType;
            accountType = BnRAccountType.builder()
                    .name("Fixed Deposit")
                    .code("FIX")
                    .build();

            bnRAccountTypeRepository.save(accountType);
            log.info("Fixed Deposit Account Type is saved");
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

        String branchCode = "";
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
                    .isFirstDepositDone(true)
                    .build();
            account = bnMAccountRepository.save(account);
            CustomMethods customMethods = new CustomMethods();
            String accountNo = "HDOSAV23120001";
            account.setAccountNo(accountNo);
            bnMAccountRepository.save(account);

            log.info("Account is saved. Account No : " + accountNo);
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
