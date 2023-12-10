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
                                      BnRIntRateRepository bnRIntRateRepository) {
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

        Optional<BnRLoanProduct> optLoanProduct11 = bnRLoanProductRepository.findById(new Long(11));
        if (optLoanProduct11.isPresent()) {
            log.info("Student Loan Product is present");
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
                log.info("Student Loan Product is saved");
            } else {
                System.out.println("Loan Type or Interest Rate or Loan Period is not present");
            }
        }

    }

}
