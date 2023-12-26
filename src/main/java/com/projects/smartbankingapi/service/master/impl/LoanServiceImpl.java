package com.projects.smartbankingapi.service.master.impl;

import com.projects.smartbankingapi.constant.HardCodeConstant;
import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.master.BnMLoanRepository;
import com.projects.smartbankingapi.dao.reference.BnRLoanPayTypeRepository;
import com.projects.smartbankingapi.dao.reference.BnRLoanProductRepository;
import com.projects.smartbankingapi.dao.reference.BnRStatusRepository;
import com.projects.smartbankingapi.dao.transaction.BnTLoanTranRepository;
import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.miscellaneous.PaginationDto;
import com.projects.smartbankingapi.dto.other.*;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.master.BnMLoanMapper;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.master.BnMLoan;
import com.projects.smartbankingapi.model.reference.*;
import com.projects.smartbankingapi.model.transaction.BnTLoanTran;
import com.projects.smartbankingapi.other.CustomMethods;
import com.projects.smartbankingapi.rest.transaction.TransactionController;
import com.projects.smartbankingapi.service.master.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService {
    private final BnMLoanRepository loanRepository;
    private final BnMLoanMapper loanMapper;
    private final BnRLoanProductRepository loanProductRepository;
    private final BnMAccountRepository accountRepository;
    private final BnRStatusRepository statusRepository;
    private final CustomMethods customMethods;
    private final BnRLoanPayTypeRepository loanPayTypeRepository;
    private final BnTLoanTranRepository loanTranRepository;
    private final TransactionController transactionController;

    public LoanServiceImpl(BnMLoanRepository loanRepository, BnMLoanMapper loanMapper, BnRLoanProductRepository loanProductRepository, BnMAccountRepository accountRepository, BnRStatusRepository statusRepository, CustomMethods customMethods, BnRLoanPayTypeRepository loanPayTypeRepository, BnTLoanTranRepository loanTranRepository, TransactionController transactionController) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.loanProductRepository = loanProductRepository;
        this.accountRepository = accountRepository;
        this.statusRepository = statusRepository;
        this.customMethods = customMethods;
        this.loanPayTypeRepository = loanPayTypeRepository;
        this.loanTranRepository = loanTranRepository;
        this.transactionController = transactionController;
    }

    @Override
    public ResponseEntity<BnMLoanDto> createLoan(LoanCreateReqDto loanCreateReqDto) {
        try {
            Optional<BnMAccount> optAccount = accountRepository.findById(loanCreateReqDto.getAccountId());
            if (!optAccount.isPresent()) {
                throw new BadRequestAlertException("Account not found", "Loan", "createLoan");
            } else {

                Optional<BnRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_PENDING_ID.longValue());
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found", "Loan", "createLoan");
                }
                BnMAccount account = optAccount.get();
                Optional<BnRLoanProduct> optLoanProduct = loanProductRepository.findById(loanCreateReqDto.getLoanProductId());
                if (!optLoanProduct.isPresent()) {
                    throw new BadRequestAlertException("Loan Product not found", "Loan", "createLoan");
                } else {
                    BnRLoanProduct loanProduct = optLoanProduct.get();
                    BnRIntRate intRate = loanProduct.getBnRIntRate();
                    BnRLoanPeriod loanPeriod = loanProduct.getBnRLoanPeriod();
                    BnRLoanType loanType = loanProduct.getBnRLoanType();

                    float interest = customMethods.calculateInterest(loanCreateReqDto.getAmount(), intRate.getRate(), loanPeriod.getMonth(), loanType.getLoanTypeId());

                    BnMLoan loan = new BnMLoan();
                    loan.setAmount(loanCreateReqDto.getAmount());
                    loan.setInterest(interest);
                    loan.setTotInstallments(loanPeriod.getMonth());
                    loan.setNextInstallmentDate(null);
                    loan.setTotArrearsAmt((float) 0);
                    loan.setRemInstallments(loanPeriod.getMonth());
                    loan.setNextInstallmentAmt((float) 0);
                    loan.setDistributedAmt((float) 0);
                    loan.setTotSettledAmt((float) 0);
                    loan.setTotInterestPaid((float) 0);
                    loan.setTotPaid((float) 0);
                    loan.setBnRStatus(optStatus.get());
                    loan.setBnMAccount(account);
                    loan.setBnRLoanProduct(loanProduct);

                    loan = loanRepository.save(loan);
                    if (loan.getLoanId() != null) {
                        return ResponseEntity.ok(loanMapper.toDto(loan));
                    } else {
                        throw new BadRequestAlertException("Error while creating loan", "Loan", "createLoan");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while creating loan", e);
            throw new BadRequestAlertException(e.getMessage(), "Loan", "createLoan");
        }
    }

    @Override
    public ResponseEntity<BnMLoanDto> approveLoan(Long loanId) {
        try {
            if (loanId == null) {
                throw new BadRequestAlertException("Loan Id is required", "Loan", "approveLoan");
            } else {
                Optional<BnMLoan> optLoan = loanRepository.findById(loanId);
                if (!optLoan.isPresent()) {
                    throw new BadRequestAlertException("Loan not found", "Loan", "approveLoan");
                } else {
                    BnMLoan loan = optLoan.get();
                    Optional<BnRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_APPROVED_ID.longValue());
                    if (!optStatus.isPresent()) {
                        throw new BadRequestAlertException("Status not found", "Loan", "approveLoan");
                    } else {
                        loan.setBnRStatus(optStatus.get());
                        loan = loanRepository.save(loan);
                    }
                    log.info("Loan approved successfully");
                    return ResponseEntity.ok(loanMapper.toDto(loan));
                }
            }
        } catch (Exception e) {
            log.error("Error while approving loan", e);
            throw new BadRequestAlertException(e.getMessage(), "Loan", "approveLoan");
        }
    }

    @Override
    public ResponseEntity<BnMLoanDto> getLoan(Long loanId) {
        try {
            if (loanId == null) {
                throw new BadRequestAlertException("Loan Id is required", "Loan", "getLoan");
            } else {
                Optional<BnMLoan> optLoan = loanRepository.findById(loanId);
                if (!optLoan.isPresent()) {
                    throw new BadRequestAlertException("Loan not found", "Loan", "getLoan");
                } else {
                    BnMLoan loan = optLoan.get();
                    return ResponseEntity.ok(loanMapper.toDto(loan));
                }
            }
        } catch (Exception e) {
            log.error("Error while getting loan", e);
            throw new BadRequestAlertException(e.getMessage(), "Loan", "getLoan");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<BnMLoanDto>>> getLoanForTable(Integer page, Integer perPage, String sort, String direction, String search) {
        try {
            Page<BnMLoan> dbData;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = loanRepository.getLoanForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = loanRepository.getLoanForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }
            ApiResponseDto<List<BnMLoanDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setTotal(dbData.getTotalElements());
            pagination.setPerPage(perPage);
            pagination.setCurrentPage(page);
            pagination.setFrom(page * perPage + 1);
            pagination.setTo(page * perPage + dbData.getNumberOfElements());
            response.setPagination(pagination);
            response.setResult(loanMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error while getting loan for table", e);
            throw new BadRequestAlertException(e.getMessage(), "Loan", "getLoanForTable");
        }
    }

    @Override
    public ResponseEntity<BnMLoanDto> disburseLoan(Long loanId, LoanDisburseReqDto loanDisburseReqDto) {
        try {
            if (loanId == null) {
                throw new BadRequestAlertException("Loan Id is required", "Loan", "disburseLoan");
            } else if (!loanId.equals(loanDisburseReqDto.getLoanId())) {
                throw new BadRequestAlertException("Loan Id is not match", "Loan", "disburseLoan");
            } else {

                Optional<BnRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_DISBURSED_ID.longValue());
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found", "Loan", "disburseLoan");
                }

                Optional<BnMLoan> optLoan = loanRepository.findById(loanId);
                if (!optLoan.isPresent()) {
                    throw new BadRequestAlertException("Loan not found", "Loan", "disburseLoan");
                } else {
                    BnMLoan loan = optLoan.get();

                    float nextInstallmentAmt = customMethods.calculateNextInstallmentAmt(loanDisburseReqDto.getDisburseAmt(), loan.getInterest(), loan.getTotInstallments(), loan.getRemInstallments(), loan.getBnRLoanProduct().getBnRLoanType().getLoanTypeId());

                    loan.setNextInstallmentDate(LocalDate.now().plusMonths(1));
                    loan.setNextInstallmentAmt(nextInstallmentAmt);
                    loan.setDistributedAmt(loanDisburseReqDto.getDisburseAmt());
                    loan.setBnRStatus(optStatus.get());

                    loan = loanRepository.save(loan);

                    log.info("Loan disbursed successfully");
                    BnTLoanTran loanTran = new BnTLoanTran();

                    Optional<BnRLoanPayType> optLoanPayType = loanPayTypeRepository.findById(HardCodeConstant.LOAN_PAY_TYPE_DISBURSE_ID.longValue());
                    if (!optLoanPayType.isPresent()) {
                        throw new BadRequestAlertException("Loan Pay Type not found", "Loan", "disburseLoan");
                    } else {
                        loanTran.setBnRLoanPayType(optLoanPayType.get());
                    }

                    loanTran.setAmount(loanDisburseReqDto.getDisburseAmt());
                    loanTran.setDescription("Disbursement of loan");
                    loanTran.setTranReference(UUID.randomUUID().toString());
                    loanTran.setTranDate(LocalDate.now());
                    loanTran.setTranTime(LocalTime.now());
                    loanTran.setBnMLoan(loan);
                    loanTran.setBnRLoanPayType(optLoanPayType.get());
                    loanTran = loanTranRepository.save(loanTran);
                    if (loanTran.getLoanTranId() == null) {
                        throw new BadRequestAlertException("Error while saving loan transaction", "Loan", "disburseLoan");
                    }
                    log.info("Loan transaction saved successfully");

                    return ResponseEntity.ok(loanMapper.toDto(loan));
                }
            }
        } catch (Exception e) {
            log.error("Error while disbursing loan", e);
            throw new BadRequestAlertException(e.getMessage(), "Loan", "disburseLoan");
        }
    }

    @Override
    public ResponseEntity<CalculatorResponseDto> calculator(CalculatorReqDto calculatorReqDto) {
        try {
            float interest = 0;
            float i = 0;
            float p = 0;
            int t = 0;
            float totalPayable = 0;
            float emi = 0;
            CalculatorResponseDto calculatorResponseDto = new CalculatorResponseDto();
            if (calculatorReqDto.getLoanTypeId() == HardCodeConstant.LOAN_TYPE_FLAT_ID) {

                i = calculatorReqDto.getInterestRate();
                p = calculatorReqDto.getLoanAmount();
                t = calculatorReqDto.getMonths() / 12;

                interest = (p * i * t) / 100;
                totalPayable = p + interest;

                calculatorResponseDto.setLoanAmount(calculatorReqDto.getLoanAmount());
                calculatorResponseDto.setIntRate(calculatorReqDto.getInterestRate());
                calculatorResponseDto.setMonths(calculatorReqDto.getMonths());
                calculatorResponseDto.setTotalInterest(interest);
                calculatorResponseDto.setTotalPayable(totalPayable);
                return ResponseEntity.ok(calculatorResponseDto);

            } else if (Objects.equals(calculatorReqDto.getLoanTypeId(), HardCodeConstant.LOAN_TYPE_REDUCING_ID)) {

                i = (calculatorReqDto.getInterestRate() / 100) / 12;
                p = calculatorReqDto.getLoanAmount();
                t = calculatorReqDto.getMonths();

                emi = (p * i * (float) Math.pow(1 + i, t)) / ((float) Math.pow(1 + i, t) - 1);

                interest = emi * t - p;
                totalPayable = emi * t;

                calculatorResponseDto.setLoanAmount(calculatorReqDto.getLoanAmount());
                calculatorResponseDto.setIntRate(calculatorReqDto.getInterestRate());
                calculatorResponseDto.setMonths(calculatorReqDto.getMonths());
                calculatorResponseDto.setTotalInterest(interest);
                calculatorResponseDto.setTotalPayable(totalPayable);
                return ResponseEntity.ok(calculatorResponseDto);

            } else {
                log.error("Invalid loan type");
                throw new BadRequestAlertException("Invalid loan type", "ERROR", "ERROR");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "ERROR", "ERROR");
        }
    }

    @Override
    public ResponseEntity<String> deleteLoan(Long loanId) {
        try {
            if (loanId == null) {
                throw new BadRequestAlertException("Loan Id is required", "Loan", "deleteLoan");
            } else {
                Optional<BnMLoan> optLoan = loanRepository.findById(loanId);
                if (!optLoan.isPresent()) {
                    throw new BadRequestAlertException("Loan not found", "Loan", "deleteLoan");
                } else {
                    if (optLoan.get().getBnRStatus().getStatusId() != HardCodeConstant.STATUS_PENDING_ID.longValue()) {
                        throw new BadRequestAlertException("Loan can not be deleted", "Loan", "deleteLoan");
                    } else {
                        loanRepository.deleteById(loanId);
                        Optional<BnMLoan> checkLoan = loanRepository.findById(loanId);
                        if (checkLoan.isPresent()) {
                            throw new BadRequestAlertException("Error while deleting loan", "Loan", "deleteLoan");
                        }
                        return ResponseEntity.ok("Loan deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting loan", e);
            throw new BadRequestAlertException(e.getMessage(), "Loan", "deleteLoan");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> recoveryRun() {
        try {
            List<BnMLoan> loans = loanRepository.findByBnRStatusStatusId(HardCodeConstant.STATUS_DISBURSED_ID.longValue());
            if (loans.isEmpty()) {
                throw new BadRequestAlertException("No disbursed loans found", "Loan", "recoveryRun");
            } else {
                BnRLoanPayType loanPayType;
                Optional<BnRLoanPayType> optLoanPayType = loanPayTypeRepository.findById(HardCodeConstant.LOAN_PAY_TYPE_REPAYMENT_ID.longValue());
                if (!optLoanPayType.isPresent()) {
                    throw new BadRequestAlertException("Loan Pay Type not found", "Loan", "recoveryRun");
                } else {
                    loanPayType = optLoanPayType.get();
                }

                for (BnMLoan loan : loans) {
                    if (loan.getNextInstallmentDate().isEqual(LocalDate.now())) {

                        Long accountId = loan.getBnMAccount().getAccountId();
                        Optional<BnMAccount> optAccount = accountRepository.findById(accountId);
                        if (!optAccount.isPresent()) {
                            throw new BadRequestAlertException("Account not found", "Loan", "recoveryRun");
                        } else {

                            DebitTranCreateReqDto debitTranCreateReqDto = new DebitTranCreateReqDto();
                            debitTranCreateReqDto.setFromAccountNo(optAccount.get().getAccountNo());
                            debitTranCreateReqDto.setToAccountNo(HardCodeConstant.HEAD_OFFICE_ACCOUNT_NO);

                            BnMAccount account = optAccount.get();
                            if (account.getAvailableBalance() < loan.getNextInstallmentAmt()) {
                                log.warn("Insufficient balance in account. Account ID : {}", account.getAccountId());
                                if (account.getAvailableBalance() > 0) {
                                    float arrearsAmt = loan.getNextInstallmentAmt() - account.getAvailableBalance();
                                    loan.setTotArrearsAmt(loan.getTotArrearsAmt() + arrearsAmt);

                                    //Set debit transaction amount to available balance
                                    debitTranCreateReqDto.setAmount(account.getAvailableBalance());

                                    float amount;
                                    if (account.getAvailableBalance() > loan.getInterest()) {
                                        loan.setTotInterestPaid(loan.getTotInterestPaid() + loan.getInterest());
                                        amount = account.getAvailableBalance() - loan.getInterest();
                                        loan = loanRepository.save(loan);

                                        if (amount > 0) {
                                            loan.setTotPaid(loan.getTotPaid() + amount);
                                            loan = loanRepository.save(loan);
                                        } else {
                                            log.info("Loan recover only interest. Loan ID : {}", loan.getLoanId());
                                        }
                                    } else {
                                        loan.setTotInterestPaid(loan.getTotInterestPaid() + account.getAvailableBalance());
                                        loan = loanRepository.save(loan);
                                    }

                                    loan = loanRepository.save(loan);

                                    BnTLoanTran loanTran = new BnTLoanTran();

                                    loanTran.setAmount(account.getAvailableBalance());
                                    createLoanTran(loanPayType, loan, loanTran);
                                } else {
                                    loan.setTotArrearsAmt(loan.getTotArrearsAmt() + loan.getNextInstallmentAmt());
                                }
                            } else {
                                loan.setRemInstallments(loan.getRemInstallments() - 1);
                                loan.setNextInstallmentDate(LocalDate.now().plusMonths(1));
                                loan.setNextInstallmentAmt(customMethods.calculateNextInstallmentAmt(loan.getAmount(), loan.getInterest(), loan.getTotInstallments(), loan.getRemInstallments(), loan.getBnRLoanProduct().getBnRLoanType().getLoanTypeId()));
                                loan.setTotInterestPaid(loan.getTotInterestPaid() + loan.getInterest());
                                loan.setTotPaid(loan.getTotPaid() + loan.getNextInstallmentAmt());
                                loan = loanRepository.save(loan);
                                log.info("Loan recover successfully. Loan ID : {}", loan.getLoanId());

                                BnTLoanTran loanTran = new BnTLoanTran();

                                loanTran.setAmount(loan.getNextInstallmentAmt());
                                createLoanTran(loanPayType, loan, loanTran);

                                //Set debit transaction amount to next installment amount
                                debitTranCreateReqDto.setAmount(loan.getNextInstallmentAmt());
                            }
                            ResponseEntity<BnTTranDto> debitTranResponse = transactionController.createDebitTransaction(debitTranCreateReqDto);
                            if (debitTranResponse.getStatusCodeValue() != 200) {
                                throw new BadRequestAlertException("Error while creating debit transaction", "Loan", "recoveryRun");
                            } else {
                                log.info("Debit transaction created successfully");
                            }
                        }
                    }
                }
                ResponseDto responseDto = new ResponseDto();
                responseDto.setMessage("Recovery run completed successfully");
                responseDto.setStatus(200);
                responseDto.setData(null);
                return ResponseEntity.ok(responseDto);
            }
        } catch (Exception e) {
            log.error("Error while recovery run", e);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage(e.getMessage());
            responseDto.setStatus(400);
            responseDto.setData(null);
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    private void createLoanTran(BnRLoanPayType loanPayType, BnMLoan loan, BnTLoanTran loanTran) {
        loanTran.setDescription("Recovery of loan");
        loanTran.setTranReference(UUID.randomUUID().toString());
        loanTran.setTranDate(LocalDate.now());
        loanTran.setTranTime(LocalTime.now());
        loanTran.setBnMLoan(loan);
        loanTran.setBnRLoanPayType(loanPayType);
        loanTran = loanTranRepository.save(loanTran);
        if (loanTran.getLoanTranId() == null) {
            throw new BadRequestAlertException("Error while saving loan transaction", "Loan", "recoveryRun");
        }
        log.info("Loan transaction saved successfully");
    }
}