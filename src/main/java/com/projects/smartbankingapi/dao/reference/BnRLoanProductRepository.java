package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BnRLoanProductRepository extends JpaRepository<BnRLoanProduct, Long> {
    List<BnRLoanProduct> findAllByBnRLoanTypeLoanTypeId(Long loanTypeId);

    List<BnRLoanProduct> findAllByBnRIntRateIntRateId(Long intRateId);

    List<BnRLoanProduct> findAllByBnRLoanPeriodPeriodId(Long periodId);

    List<BnRLoanProduct> findAllByBnRLoanTypeLoanTypeIdAndBnRIntRateIntRateId(Long loanTypeId, Long intRateId);

    List<BnRLoanProduct> findAllByBnRLoanTypeLoanTypeIdAndBnRLoanPeriodPeriodId(Long loanTypeId, Long periodId);

    List<BnRLoanProduct> findAllByBnRIntRateIntRateIdAndBnRLoanPeriodPeriodId(Long intRateId, Long periodId);

    List<BnRLoanProduct> findAllByBnRLoanTypeLoanTypeIdAndBnRIntRateIntRateIdAndBnRLoanPeriodPeriodId(Long loanTypeId, Long intRateId, Long periodId);
}