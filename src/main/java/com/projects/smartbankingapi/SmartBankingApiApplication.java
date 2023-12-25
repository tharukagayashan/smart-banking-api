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
    private static BnRLoanTypeRepository bnRLoanTypeRepository = null;
    private static BnRLoanPeriodRepository bnRLoanPeriodRepository = null;
    private static BnRLoanProductRepository bnRLoanProductRepository = null;
    private static BnRIntRateRepository bnRIntRateRepository = null;
    private static BnRCurrencyRateRepository bnRCurrencyRateRepository = null;
    private static BnRLoanPayTypeRepository bnRLoanPayTypeRepository = null;

    public SmartBankingApiApplication(BnMCustomerRepository bnMCustomerRepository, BnMAccountRepository bnMAccountRepository,
                                      BnRAccountTypeRepository bnRAccountTypeRepository,
                                      BnRBranchRepository bnRBranchRepository,
                                      BnRCurrencyRepository bnRCurrencyRepository,
                                      BnRStatusRepository bnRStatusRepository,
                                      BnRBankRepository bnRBankRepository,
                                      BnRRoleRepository bnRRoleRepository,
                                      BnMStaffRepository bnMStaffRepository,
                                      BnRTranTypeRepository bnRTranTypeRepository,
                                      BnRLoanTypeRepository bnRLoanTypeRepository,
                                      BnRLoanPeriodRepository bnRLoanPeriodRepository,
                                      BnRLoanProductRepository bnRLoanProductRepository,
                                      BnRIntRateRepository bnRIntRateRepository,
                                      BnRCurrencyRateRepository bnRCurrencyRateRepository,
                                      BnRLoanPayTypeRepository bnRLoanPayTypeRepository) {
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
        this.bnRLoanTypeRepository = bnRLoanTypeRepository;
        this.bnRLoanPeriodRepository = bnRLoanPeriodRepository;
        this.bnRLoanProductRepository = bnRLoanProductRepository;
        this.bnRIntRateRepository = bnRIntRateRepository;
        this.bnRCurrencyRateRepository = bnRCurrencyRateRepository;
        this.bnRLoanPayTypeRepository = bnRLoanPayTypeRepository;
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

        Optional<BnRCurrency> optCurrency1 = bnRCurrencyRepository.findById(new Long(1));
        if (optCurrency1.isPresent()) {
            log.info("LKR Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Sri Lankan Rupee")
                    .code("LKR")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("LKR Currency is saved");
        }

        Optional<BnRCurrency> optCurrency2 = bnRCurrencyRepository.findById(new Long(2));
        if (optCurrency2.isPresent()) {
            log.info("USD Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("United States Dollar")
                    .code("USD")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("USD Currency is saved");
        }

        Optional<BnRCurrency> optCurrency3 = bnRCurrencyRepository.findById(new Long(3));
        if (optCurrency3.isPresent()) {
            log.info("EUR Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Euro")
                    .code("EUR")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("EUR Currency is saved");
        }

        Optional<BnRCurrency> optCurrency4 = bnRCurrencyRepository.findById(new Long(4));
        if (optCurrency4.isPresent()) {
            log.info("GBP Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Pound Sterling")
                    .code("GBP")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("GBP Currency is saved");
        }

        Optional<BnRCurrency> optCurrency5 = bnRCurrencyRepository.findById(new Long(5));
        if (optCurrency5.isPresent()) {
            log.info("AUD Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Australian Dollar")
                    .code("AUD")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("AUD Currency is saved");
        }

        Optional<BnRCurrency> optCurrency6 = bnRCurrencyRepository.findById(new Long(6));
        if (optCurrency6.isPresent()) {
            log.info("CAD Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Canadian Dollar")
                    .code("CAD")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("CAD Currency is saved");
        }

        Optional<BnRCurrency> optCurrency7 = bnRCurrencyRepository.findById(new Long(7));
        if (optCurrency7.isPresent()) {
            log.info("SGD Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Singapore Dollar")
                    .code("SGD")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("SGD Currency is saved");
        }

        Optional<BnRCurrency> optCurrency8 = bnRCurrencyRepository.findById(new Long(8));
        if (optCurrency8.isPresent()) {
            log.info("JPY Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Japanese Yen")
                    .code("JPY")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("JPY Currency is saved");
        }

        Optional<BnRCurrency> optCurrency9 = bnRCurrencyRepository.findById(new Long(9));
        if (optCurrency9.isPresent()) {
            log.info("CNY Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Chinese Yuan")
                    .code("CNY")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("CNY Currency is saved");
        }

        Optional<BnRCurrency> optCurrency10 = bnRCurrencyRepository.findById(new Long(10));
        if (optCurrency10.isPresent()) {
            log.info("INR Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Indian Rupee")
                    .code("INR")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("INR Currency is saved");
        }

        Optional<BnRCurrency> optCurrency11 = bnRCurrencyRepository.findById(new Long(11));
        if (optCurrency11.isPresent()) {
            log.info("MYR Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Malaysian Ringgit")
                    .code("MYR")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("MYR Currency is saved");
        }

        Optional<BnRCurrency> optCurrency12 = bnRCurrencyRepository.findById(new Long(12));
        if (optCurrency12.isPresent()) {
            log.info("NZD Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("New Zealand Dollar")
                    .code("NZD")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("NZD Currency is saved");
        }

        Optional<BnRCurrency> optCurrency13 = bnRCurrencyRepository.findById(new Long(13));
        if (optCurrency13.isPresent()) {
            log.info("CHF Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Swiss Franc")
                    .code("CHF")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("CHF Currency is saved");
        }

        Optional<BnRCurrency> optCurrency14 = bnRCurrencyRepository.findById(new Long(14));
        if (optCurrency14.isPresent()) {
            log.info("RBL Currency is present");
        } else {
            BnRCurrency currency;
            currency = BnRCurrency.builder()
                    .name("Russian Ruble")
                    .code("RBL")
                    .build();

            bnRCurrencyRepository.save(currency);
            log.info("RBL Currency is saved");
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

        Optional<BnRStatus> optStatus4 = bnRStatusRepository.findById(new Long(4));
        if (optStatus4.isPresent()) {
            log.info("Disburse Status is present");
        } else {
            BnRStatus status;
            status = BnRStatus.builder()
                    .name("Disburse")
                    .code("DIS")
                    .type("A")
                    .build();

            bnRStatusRepository.save(status);
            log.info("Disburse Status is saved");
        }

        Optional<BnRStatus> optStatus5 = bnRStatusRepository.findById(new Long(5));
        if (optStatus5.isPresent()) {
            log.info("Settle Status is present");
        } else {
            BnRStatus status;
            status = BnRStatus.builder()
                    .name("Settle")
                    .code("SET")
                    .type("A")
                    .build();

            bnRStatusRepository.save(status);
            log.info("Settle Status is saved");
        }

        String accountTypeCode = "";
        Optional<BnRAccountType> optAccountType1 = bnRAccountTypeRepository.findById(new Long(1));
        if (optAccountType1.isPresent()) {
            log.info("Saving Account Type is present");
        } else {
            BnRAccountType accountType;
            accountType = BnRAccountType.builder()
                    .name("Savings")
                    .code("001")
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
                    .code("002")
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
                    .code("003")
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
                    .code("001")
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
                        .code("001")
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
            String accountNo = customMethods.generateAccountNumber(bnRBranchRepository.findById(1L).get().getCode(), bnRAccountTypeRepository.findById(1L).get().getCode(), account.getAccountId());
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

        //Loan Type Create
        Optional<BnRLoanType> optLoanType1 = bnRLoanTypeRepository.findById(new Long(1));
        if (optLoanType1.isPresent()) {
            log.info("Loan Type is present");
        } else {
            BnRLoanType loanType;
            loanType = BnRLoanType.builder()
                    .name("Flat Rate")
                    .code("FR")
                    .description("Flat Rate Loan")
                    .build();
            bnRLoanTypeRepository.save(loanType);
            log.info("Loan Type Flat Rate is saved");
        }

        Optional<BnRLoanType> optLoanType2 = bnRLoanTypeRepository.findById(new Long(2));
        if (optLoanType2.isPresent()) {
            log.info("Loan Type Reducing is present");
        } else {
            BnRLoanType loanType;
            loanType = BnRLoanType.builder()
                    .name("Reducing Balance")
                    .code("RB")
                    .description("Reducing Balance Loan")
                    .build();
            bnRLoanTypeRepository.save(loanType);
            log.info("Loan Type is saved");
        }

        //Loan Period Create
        Optional<BnRLoanPeriod> optLoanPeriod1 = bnRLoanPeriodRepository.findById(new Long(1));
        if (optLoanPeriod1.isPresent()) {
            log.info("Loan Period 1 year is present");
        } else {
            BnRLoanPeriod loanPeriod;
            loanPeriod = BnRLoanPeriod.builder()
                    .name("1 Year")
                    .description("1 Year Loan Period")
                    .month(12)
                    .build();
            bnRLoanPeriodRepository.save(loanPeriod);
            log.info("Loan Period 1 year is saved");
        }

        Optional<BnRLoanPeriod> optLoanPeriod2 = bnRLoanPeriodRepository.findById(new Long(2));
        if (optLoanPeriod2.isPresent()) {
            log.info("Loan Period 2 year is present");
        } else {
            BnRLoanPeriod loanPeriod;
            loanPeriod = BnRLoanPeriod.builder()
                    .name("2 Year")
                    .description("2 Year Loan Period")
                    .month(24)
                    .build();
            bnRLoanPeriodRepository.save(loanPeriod);
            log.info("Loan Period 2 year is saved");
        }

        Optional<BnRLoanPeriod> optLoanPeriod3 = bnRLoanPeriodRepository.findById(new Long(3));
        if (optLoanPeriod3.isPresent()) {
            log.info("Loan Period 3 year is present");
        } else {
            BnRLoanPeriod loanPeriod;
            loanPeriod = BnRLoanPeriod.builder()
                    .name("3 Year")
                    .description("3 Year Loan Period")
                    .month(36)
                    .build();
            bnRLoanPeriodRepository.save(loanPeriod);
            log.info("Loan Period 3 year is saved");
        }

        Optional<BnRLoanPeriod> optLoanPeriod4 = bnRLoanPeriodRepository.findById(new Long(4));
        if (optLoanPeriod4.isPresent()) {
            log.info("Loan Period 4 year is present");
        } else {
            BnRLoanPeriod loanPeriod;
            loanPeriod = BnRLoanPeriod.builder()
                    .name("4 Year")
                    .description("4 Year Loan Period")
                    .month(48)
                    .build();
            bnRLoanPeriodRepository.save(loanPeriod);
            log.info("Loan Period 4 year is saved");
        }

        Optional<BnRLoanPeriod> optLoanPeriod5 = bnRLoanPeriodRepository.findById(new Long(5));
        if (optLoanPeriod5.isPresent()) {
            log.info("Loan Period 5 year is present");
        } else {
            BnRLoanPeriod loanPeriod;
            loanPeriod = BnRLoanPeriod.builder()
                    .name("5 Year")
                    .description("5 Year Loan Period")
                    .month(60)
                    .build();
            bnRLoanPeriodRepository.save(loanPeriod);
            log.info("Loan Period 5 year is saved");
        }

        //Interest Rate Create
        Optional<BnRIntRate> optIntRate1 = bnRIntRateRepository.findById(new Long(1));
        if (optIntRate1.isPresent()) {
            log.info("Interest Rate 6% is present");
        } else {
            BnRIntRate intRate;
            intRate = BnRIntRate.builder()
                    .name("6%")
                    .description("6% Interest Rate")
                    .rate(new Float(6))
                    .build();
            bnRIntRateRepository.save(intRate);
            log.info("Interest Rate 6% is saved");
        }

        Optional<BnRIntRate> optIntRate2 = bnRIntRateRepository.findById(new Long(2));
        if (optIntRate2.isPresent()) {
            log.info("Interest Rate 9.5% is present");
        } else {
            BnRIntRate intRate;
            intRate = BnRIntRate.builder()
                    .name("9.5%")
                    .description("9.5% Interest Rate")
                    .rate(new Float(9.5))
                    .build();
            bnRIntRateRepository.save(intRate);
            log.info("Interest Rate 9.5% is saved");
        }

        Optional<BnRIntRate> optIntRate3 = bnRIntRateRepository.findById(new Long(3));
        if (optIntRate3.isPresent()) {
            log.info("Interest Rate 12% is present");
        } else {
            BnRIntRate intRate;
            intRate = BnRIntRate.builder()
                    .name("12%")
                    .description("12% Interest Rate")
                    .rate(new Float(12))
                    .build();
            bnRIntRateRepository.save(intRate);
            log.info("Interest Rate 12% is saved");
        }

        Optional<BnRIntRate> optIntRate4 = bnRIntRateRepository.findById(new Long(4));
        if (optIntRate4.isPresent()) {
            log.info("Interest Rate 15% is present");
        } else {
            BnRIntRate intRate;
            intRate = BnRIntRate.builder()
                    .name("15%")
                    .description("15% Interest Rate")
                    .rate(new Float(15))
                    .build();
            bnRIntRateRepository.save(intRate);
            log.info("Interest Rate 15% is saved");
        }

        //Loan Product Create
        Optional<BnRLoanProduct> optLoanProduct1 = bnRLoanProductRepository.findById(new Long(1));
        if (optLoanProduct1.isPresent()) {
            log.info("Personal Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(1));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(1));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(1));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Personal Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct2 = bnRLoanProductRepository.findById(new Long(2));
        if (optLoanProduct2.isPresent()) {
            log.info("Housing Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(1));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(2));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(2));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Housing Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct3 = bnRLoanProductRepository.findById(new Long(3));
        if (optLoanProduct3.isPresent()) {
            log.info("Vehicle Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(1));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(3));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(3));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Vehicle Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct4 = bnRLoanProductRepository.findById(new Long(4));
        if (optLoanProduct4.isPresent()) {
            log.info("Business Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(1));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(4));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(4));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Business Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct5 = bnRLoanProductRepository.findById(new Long(5));
        if (optLoanProduct5.isPresent()) {
            log.info("Education Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(1));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(1));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(5));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Education Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct6 = bnRLoanProductRepository.findById(new Long(6));
        if (optLoanProduct6.isPresent()) {
            log.info("Personal Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(2));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(1));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(1));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Personal Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct7 = bnRLoanProductRepository.findById(new Long(7));
        if (optLoanProduct7.isPresent()) {
            log.info("Housing Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(2));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(2));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(2));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Housing Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct8 = bnRLoanProductRepository.findById(new Long(8));
        if (optLoanProduct8.isPresent()) {
            log.info("Vehicle Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(2));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(3));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(3));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Vehicle Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct9 = bnRLoanProductRepository.findById(new Long(9));
        if (optLoanProduct9.isPresent()) {
            log.info("Business Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(2));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(4));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(4));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Business Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRLoanProduct> optLoanProduct10 = bnRLoanProductRepository.findById(new Long(10));
        if (optLoanProduct10.isPresent()) {
            log.info("Education Loan Product is present");
        } else {
            Optional<BnRLoanType> optLoanType = bnRLoanTypeRepository.findById(new Long(2));
            Optional<BnRIntRate> optIntRate = bnRIntRateRepository.findById(new Long(1));
            Optional<BnRLoanPeriod> optLoanPeriod = bnRLoanPeriodRepository.findById(new Long(5));
            if (optLoanType.isPresent() && optIntRate.isPresent() && optLoanPeriod.isPresent()) {
                BnRLoanProduct loanProduct;
                loanProduct = BnRLoanProduct.builder()
                        .bnRLoanType(optLoanType.get())
                        .bnRIntRate(optIntRate.get())
                        .bnRLoanPeriod(optLoanPeriod.get())
                        .build();
                bnRLoanProductRepository.save(loanProduct);
                log.info("Education Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate1 = bnRCurrencyRateRepository.findById(new Long(1));
        if (optCurrencyRate1.isPresent()) {
            log.info("LKR Currency Rate is present");
        } else {

            Optional<BnRCurrency> optLKRCurrency = bnRCurrencyRepository.findById(new Long(1));
            if (!optLKRCurrency.isPresent()) {
                System.out.println("LKR Currency is not present");
            } else {
                BnRCurrency currency = optLKRCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(1))
                        .sellingRate(new Float(1))
                        .middleRate(new Float(1))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("LKR Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate2 = bnRCurrencyRateRepository.findById(new Long(2));
        if (optCurrencyRate2.isPresent()) {
            log.info("USD Currency Rate is present");
        } else {

            Optional<BnRCurrency> optUSDCurrency = bnRCurrencyRepository.findById(new Long(2));
            if (!optUSDCurrency.isPresent()) {
                System.out.println("USD Currency is not present");
            } else {
                BnRCurrency currency = optUSDCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(330))
                        .sellingRate(new Float(340))
                        .middleRate(new Float(335))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("USD Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate3 = bnRCurrencyRateRepository.findById(new Long(3));
        if (optCurrencyRate3.isPresent()) {
            log.info("EUR Currency Rate is present");
        } else {

            Optional<BnRCurrency> optEURCurrency = bnRCurrencyRepository.findById(new Long(3));
            if (!optEURCurrency.isPresent()) {
                System.out.println("EUR Currency is not present");
            } else {
                BnRCurrency currency = optEURCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(380))
                        .sellingRate(new Float(390))
                        .middleRate(new Float(385))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("EUR Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate4 = bnRCurrencyRateRepository.findById(new Long(4));
        if (optCurrencyRate4.isPresent()) {
            log.info("GBP Currency Rate is present");
        } else {

            Optional<BnRCurrency> optGBPCurrency = bnRCurrencyRepository.findById(new Long(4));
            if (!optGBPCurrency.isPresent()) {
                System.out.println("GBP Currency is not present");
            } else {
                BnRCurrency currency = optGBPCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(420))
                        .sellingRate(new Float(430))
                        .middleRate(new Float(425))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("GBP Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate5 = bnRCurrencyRateRepository.findById(new Long(5));
        if (optCurrencyRate5.isPresent()) {
            log.info("AUD Currency Rate is present");
        } else {

            Optional<BnRCurrency> optAUDCurrency = bnRCurrencyRepository.findById(new Long(5));
            if (!optAUDCurrency.isPresent()) {
                System.out.println("AUD Currency is not present");
            } else {
                BnRCurrency currency = optAUDCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(240))
                        .sellingRate(new Float(250))
                        .middleRate(new Float(245))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("AUD Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate6 = bnRCurrencyRateRepository.findById(new Long(6));
        if (optCurrencyRate6.isPresent()) {
            log.info("CAD Currency Rate is present");
        } else {

            Optional<BnRCurrency> optCADCurrency = bnRCurrencyRepository.findById(new Long(6));
            if (!optCADCurrency.isPresent()) {
                System.out.println("CAD Currency is not present");
            } else {
                BnRCurrency currency = optCADCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(240))
                        .sellingRate(new Float(250))
                        .middleRate(new Float(245))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("CAD Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate7 = bnRCurrencyRateRepository.findById(new Long(7));
        if (optCurrencyRate7.isPresent()) {
            log.info("SGD Currency Rate is present");
        } else {

            Optional<BnRCurrency> optSGDCurrency = bnRCurrencyRepository.findById(new Long(7));
            if (!optSGDCurrency.isPresent()) {
                System.out.println("SGD Currency is not present");
            } else {
                BnRCurrency currency = optSGDCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(240))
                        .sellingRate(new Float(250))
                        .middleRate(new Float(245))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("SGD Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate8 = bnRCurrencyRateRepository.findById(new Long(8));
        if (optCurrencyRate8.isPresent()) {
            log.info("JPY Currency Rate is present");
        } else {

            Optional<BnRCurrency> optJPYCurrency = bnRCurrencyRepository.findById(new Long(8));
            if (!optJPYCurrency.isPresent()) {
                System.out.println("JPY Currency is not present");
            } else {
                BnRCurrency currency = optJPYCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(2.30))
                        .sellingRate(new Float(2.50))
                        .middleRate(new Float(2.40))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("JPY Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate9 = bnRCurrencyRateRepository.findById(new Long(9));
        if (optCurrencyRate9.isPresent()) {
            log.info("CNY Currency Rate is present");
        } else {

            Optional<BnRCurrency> optCNYCurrency = bnRCurrencyRepository.findById(new Long(9));
            if (!optCNYCurrency.isPresent()) {
                System.out.println("CNY Currency is not present");
            } else {
                BnRCurrency currency = optCNYCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(45.98))
                        .sellingRate(new Float(50.50))
                        .middleRate(new Float(47.50))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("CNY Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate10 = bnRCurrencyRateRepository.findById(new Long(10));
        if (optCurrencyRate10.isPresent()) {
            log.info("INR Currency Rate is present");
        } else {

            Optional<BnRCurrency> optINRCurrency = bnRCurrencyRepository.findById(new Long(10));
            if (!optINRCurrency.isPresent()) {
                System.out.println("INR Currency is not present");
            } else {
                BnRCurrency currency = optINRCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(4.98))
                        .sellingRate(new Float(5.50))
                        .middleRate(new Float(5.50))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("INR Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate11 = bnRCurrencyRateRepository.findById(new Long(11));
        if (optCurrencyRate11.isPresent()) {
            log.info("MYR Currency Rate is present");
        } else {

            Optional<BnRCurrency> optMYRCurrency = bnRCurrencyRepository.findById(new Long(11));
            if (!optMYRCurrency.isPresent()) {
                System.out.println("MYR Currency is not present");
            } else {
                BnRCurrency currency = optMYRCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(80))
                        .sellingRate(new Float(90))
                        .middleRate(new Float(85))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("MYR Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate12 = bnRCurrencyRateRepository.findById(new Long(12));
        if (optCurrencyRate12.isPresent()) {
            log.info("NZD Currency Rate is present");
        } else {

            Optional<BnRCurrency> optNZDCurrency = bnRCurrencyRepository.findById(new Long(12));
            if (!optNZDCurrency.isPresent()) {
                System.out.println("NZD Currency is not present");
            } else {
                BnRCurrency currency = optNZDCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(200))
                        .sellingRate(new Float(202))
                        .middleRate(new Float(201))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("NZD Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate13 = bnRCurrencyRateRepository.findById(new Long(13));
        if (optCurrencyRate13.isPresent()) {
            log.info("CHF Currency Rate is present");
        } else {

            Optional<BnRCurrency> optCHFCurrency = bnRCurrencyRepository.findById(new Long(13));
            if (!optCHFCurrency.isPresent()) {
                System.out.println("CHF Currency is not present");
            } else {
                BnRCurrency currency = optCHFCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(375))
                        .sellingRate(new Float(390))
                        .middleRate(new Float(384))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("CHF Currency Rate is saved");
            }
        }

        Optional<BnRCurrencyRate> optCurrencyRate14 = bnRCurrencyRateRepository.findById(new Long(14));
        if (optCurrencyRate14.isPresent()) {
            log.info("RBL Currency Rate is present");
        } else {
            Optional<BnRCurrency> optRLBCurrency = bnRCurrencyRepository.findById(new Long(14));
            if (!optRLBCurrency.isPresent()) {
                System.out.println("RBL Currency is not present");
            } else {
                BnRCurrency currency = optRLBCurrency.get();
                BnRCurrencyRate currencyRate;
                currencyRate = BnRCurrencyRate.builder()
                        .buyingRate(new Float(3.62))
                        .sellingRate(new Float(4.20))
                        .middleRate(new Float(3.90))
                        .publicationDate(LocalDate.now())
                        .bnRCurrency(currency)
                        .build();
                bnRCurrencyRateRepository.save(currencyRate);
                log.info("RBL Currency Rate is saved");
            }
        }

        Optional<BnRLoanPayType> optLoanPayType1 = bnRLoanPayTypeRepository.findById(new Long(1));
        if (optLoanPayType1.isPresent()) {
            log.info("Disbursement Loan Pay Type is present");
        } else {
            BnRLoanPayType loanPayType;
            loanPayType = BnRLoanPayType.builder()
                    .payType("Disbursement")
                    .payTypeCode("DISB")
                    .build();
            bnRLoanPayTypeRepository.save(loanPayType);
            log.info("Disbursement Loan Type is saved");
        }

        Optional<BnRLoanPayType> optLoanPayType2 = bnRLoanPayTypeRepository.findById(new Long(2));
        if (optLoanPayType2.isPresent()) {
            log.info("Repayment Loan Pay Type is present");
        } else {
            BnRLoanPayType loanPayType;
            loanPayType = BnRLoanPayType.builder()
                    .payType("Repayment")
                    .payTypeCode("REPAY")
                    .build();
            bnRLoanPayTypeRepository.save(loanPayType);
            log.info("Repayment Loan Type is saved");
        }

        Optional<BnRLoanPayType> optLoanPayType3 = bnRLoanPayTypeRepository.findById(new Long(3));
        if (optLoanPayType3.isPresent()) {
            log.info("Interest Loan Pay Type is present");
        } else {
            BnRLoanPayType loanPayType;
            loanPayType = BnRLoanPayType.builder()
                    .payType("Interest")
                    .payTypeCode("INT")
                    .build();
            bnRLoanPayTypeRepository.save(loanPayType);
            log.info("Interest Loan Type is saved");
        }

        Optional<BnRLoanPayType> optLoanPayType4 = bnRLoanPayTypeRepository.findById(new Long(4));
        if (optLoanPayType4.isPresent()) {
            log.info("Penalty Loan Pay Type is present");
        } else {
            BnRLoanPayType loanPayType;
            loanPayType = BnRLoanPayType.builder()
                    .payType("Penalty")
                    .payTypeCode("PEN")
                    .build();
            bnRLoanPayTypeRepository.save(loanPayType);
            log.info("Penalty Loan Type is saved");
        }

        Optional<BnRLoanPayType> optLoanPayType5 = bnRLoanPayTypeRepository.findById(new Long(5));
        if (optLoanPayType5.isPresent()) {
            log.info("Installment Pay Type is present");
        } else {
            BnRLoanPayType loanPayType;
            loanPayType = BnRLoanPayType.builder()
                    .payType("Installment")
                    .payTypeCode("INST")
                    .build();
            bnRLoanPayTypeRepository.save(loanPayType);
            log.info("Installment Loan Type is saved");
        }

        Optional<BnRLoanPayType> optLoanPayType6 = bnRLoanPayTypeRepository.findById(new Long(6));
        if (optLoanPayType6.isPresent()) {
            log.info("Installment + Interest Type is present");
        } else {
            BnRLoanPayType loanPayType;
            loanPayType = BnRLoanPayType.builder()
                    .payType("Installment and Interest")
                    .payTypeCode("INSTINT")
                    .build();
            bnRLoanPayTypeRepository.save(loanPayType);
            log.info("Installment and Interest Loan Type is saved");
        }

    }

}
