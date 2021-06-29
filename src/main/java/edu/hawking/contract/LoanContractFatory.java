package edu.hawking.contract;

public interface LoanContractFatory {

    LoanContract createLoanContract(ContractConfig config);
}
