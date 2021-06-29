package edu.hawking.contract;

import java.util.List;

public interface ContractFatory {
    List<Contract> contractList(ContractConfig config);

    String chooseVersion(ContractConfig config);


}
