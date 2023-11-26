package com.projects.smartbankingapi.other;

import com.projects.smartbankingapi.constant.HardCodeConstant;
import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.reference.BnRStatusRepository;
import com.projects.smartbankingapi.dao.reference.BnRTranTypeRepository;
import com.projects.smartbankingapi.dao.transaction.BnTTranRepository;
import com.projects.smartbankingapi.dto.other.BankDepositTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.DebitTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.TranCreateReqDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.reference.BnRStatus;
import com.projects.smartbankingapi.model.reference.BnRTranType;
import com.projects.smartbankingapi.model.transaction.BnTTran;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Slf4j
@Configuration
public class CustomMethods {

    private final BnMAccountRepository accountRepo;
    private final BnTTranRepository tranRepo;
    private final BnRStatusRepository statusRepo;
    private final BnRTranTypeRepository tranTypeRepo;

    public CustomMethods(BnMAccountRepository accountRepo, BnTTranRepository tranRepo, BnRStatusRepository statusRepo, BnRTranTypeRepository tranTypeRepo) {
        this.accountRepo = accountRepo;
        this.tranRepo = tranRepo;
        this.statusRepo = statusRepo;
        this.tranTypeRepo = tranTypeRepo;
    }

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

    public BnTTran createDebitFundTransaction(DebitTranCreateReqDto debitTranCreateReqDto) {
        try {
            Optional<BnMAccount> optFromAccount = accountRepo.findByAccountNo(debitTranCreateReqDto.getFromAccountNo());
            Optional<BnMAccount> optToAccount = accountRepo.findByAccountNo(debitTranCreateReqDto.getToAccountNo());

            BnMAccount fromAccount;
            BnMAccount toAccount;
            if (!optFromAccount.isPresent()) {
                throw new BadRequestAlertException("From account not found", "Transaction", "from_account_not_found");
            } else if (!optToAccount.isPresent()) {
                throw new BadRequestAlertException("To account not found", "Transaction", "to_account_not_found");
            } else {
                fromAccount = optFromAccount.get();
                toAccount = optToAccount.get();

                if (fromAccount.getAvailableBalance() < debitTranCreateReqDto.getAmount()) {
                    throw new BadRequestAlertException("Insufficient balance", "Transaction", "insufficient_balance");
                } else {
                    Float fromAvailableBalance = fromAccount.getAvailableBalance() - debitTranCreateReqDto.getAmount();
                    Float fromHoldBalance = fromAccount.getHoldBalance() + debitTranCreateReqDto.getAmount();
                    Float fromCurrentBalance = fromAccount.getCurrentBalance() - debitTranCreateReqDto.getAmount();
                    fromAccount.setAvailableBalance(fromAvailableBalance);
                    fromAccount.setHoldBalance(fromHoldBalance);
                    fromAccount.setCurrentBalance(fromCurrentBalance);
                    accountRepo.save(fromAccount);

                    BnTTran savedTran = createTranRecord(new TranCreateReqDto(
                            debitTranCreateReqDto.getAmount(),
                            "Debit fund",
                            debitTranCreateReqDto.getFromAccountNo(),
                            debitTranCreateReqDto.getToAccountNo(),
                            HardCodeConstant.TRAN_TYPE_DEBIT.longValue(), // debit transaction type hard coded
                            HardCodeConstant.STATUS_PENDING.longValue() // debit transaction status hard coded
                    ));
                    log.info("Debit transaction created successfully");

                    /** Auto approve transaction */
                    savedTran = approveDebitFundTransaction(savedTran.getTranId());
                    log.info("Debit transaction auto approved successfully");

                    return savedTran;
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating transaction", e);
            throw new BadRequestAlertException("Error occurred while creating transaction", "Transaction", "error");
        }
    }

    public BnTTran approveDebitFundTransaction(Long tranId) {
        try {
            Optional<BnTTran> optTran = tranRepo.findById(tranId);
            if (!optTran.isPresent()) {
                throw new BadRequestAlertException("Transaction not found", "Transaction", "transaction_not_found");
            } else {

                Optional<BnRStatus> optStatus = statusRepo.findById(HardCodeConstant.STATUS_APPROVED.longValue());
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found", "Transaction", "status_not_found");
                }

                BnTTran tran = optTran.get();
                String toAccountNo = tran.getToAccountNo();
                String fromAccountNo = tran.getFromAccountNo();

                Optional<BnMAccount> optToAccount = accountRepo.findByAccountNo(toAccountNo);
                Optional<BnMAccount> optFromAccount = accountRepo.findByAccountNo(fromAccountNo);

                if (!optToAccount.isPresent()) {
                    throw new BadRequestAlertException("To account not found", "Transaction", "to_account_not_found");
                } else if (!optFromAccount.isPresent()) {
                    throw new BadRequestAlertException("From account not found", "Transaction", "from_account_not_found");
                } else {
                    BnMAccount toAccount = optToAccount.get();
                    BnMAccount fromAccount = optFromAccount.get();

                    Float toAvailableBalance = toAccount.getAvailableBalance() + tran.getAmount();
                    Float toCurrentBalance = toAccount.getCurrentBalance() + tran.getAmount();
                    log.info("To account available balance: " + toAvailableBalance);
                    log.info("To account current balance: " + toCurrentBalance);
                    toAccount.setAvailableBalance(toAvailableBalance);
                    toAccount.setCurrentBalance(toCurrentBalance);
                    accountRepo.save(toAccount);

                    Float fromHoldBalance = fromAccount.getHoldBalance() - tran.getAmount();
                    log.info("From account hold balance: " + fromHoldBalance);
                    fromAccount.setHoldBalance(fromHoldBalance);
                    accountRepo.save(fromAccount);

                    tran.setBnRStatus(optStatus.get());
                    tranRepo.save(tran);
                    log.info("Transaction approved successfully");
                    return tran;
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while approving transaction", e);
            throw new BadRequestAlertException("Error occurred while approving transaction", "Transaction", "error");
        }
    }

    public BnTTran createTranRecord(TranCreateReqDto tranCreateReqDto) {
        try {

            Optional<BnRTranType> optTranType = tranTypeRepo.findById(tranCreateReqDto.getTranTypeId());
            if (!optTranType.isPresent()) {
                throw new BadRequestAlertException("Transaction type not found", "Transaction", "transaction_type_not_found");
            }

            Optional<BnRStatus> optStatus = statusRepo.findById(tranCreateReqDto.getStatusId());
            if (!optStatus.isPresent()) {
                throw new BadRequestAlertException("Status not found", "Transaction", "status_not_found");
            }

            BnTTran tran = new BnTTran();
            tran.setAmount(tranCreateReqDto.getAmount());
            tran.setTranDate(LocalDate.now());
            tran.setTranTime(LocalTime.now());
            tran.setBnRTranType(optTranType.get());
            tran.setDescription(tranCreateReqDto.getDescription());
            tran.setFromAccountNo(tranCreateReqDto.getFromAccountNo());
            tran.setToAccountNo(tranCreateReqDto.getToAccountNo());
            tran.setBnRStatus(optStatus.get());
            tran = tranRepo.save(tran);
            if (tran != null) {
                log.info("Pending transaction record created successfully");
                return tran;
            } else {
                throw new BadRequestAlertException("Error occurred while creating transaction record", "Transaction", "error");
            }

        } catch (Exception e) {
            log.error("Error occurred while creating transaction record", e);
            throw new BadRequestAlertException("Error occurred while creating transaction record", "Transaction", "error");
        }
    }

    public BnTTran createBankDepositTransaction(BankDepositTranCreateReqDto bankDepositTranCreateReqDto) {
        try {
            Optional<BnMAccount> optAccount = accountRepo.findByAccountNo(bankDepositTranCreateReqDto.getToAccountNo());
            if (!optAccount.isPresent()) {
                throw new BadRequestAlertException("Account not found", "Transaction", "account_not_found");
            } else {
                BnMAccount account = optAccount.get();
                log.info("Account found");
                log.info("Deposit amount: " + bankDepositTranCreateReqDto.getAmount());
                log.info("Available balance: " + account.getAvailableBalance());
                log.info("Current balance: " + account.getCurrentBalance());
                Float availableBalance = account.getAvailableBalance() + bankDepositTranCreateReqDto.getAmount();
                Float currentBalance = account.getCurrentBalance() + bankDepositTranCreateReqDto.getAmount();
                log.info("New available balance: " + availableBalance);
                log.info("New current balance: " + currentBalance);
                account.setAvailableBalance(availableBalance);
                account.setCurrentBalance(currentBalance);
                accountRepo.save(account);
                log.info("Deposit amount added to account successfully");

                BnTTran savedTran = createTranRecord(new TranCreateReqDto(
                        bankDepositTranCreateReqDto.getAmount(),
                        "Bank deposit",
                        "",
                        bankDepositTranCreateReqDto.getToAccountNo(),
                        HardCodeConstant.TRAN_TYPE_CREDIT.longValue(), // bank deposit transaction type hard coded
                        HardCodeConstant.STATUS_APPROVED.longValue() // bank deposit transaction status hard coded
                ));
                log.info("Bank deposit transaction created successfully");
                return savedTran;

            }
        } catch (Exception e) {
            log.error("Error occurred while creating bank deposit transaction", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "error");
        }
    }

}
