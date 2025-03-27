package io.jmix.uisamples.view.flowui.cookbook.customservice1;

import io.jmix.core.DataManager;
import io.jmix.uisamples.entity.Contract;
import io.jmix.uisamples.entity.ContractStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ContractService {

    private final DataManager dataManager;

    public ContractService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Contract changeStatus(Contract contract, ContractStatus status) {
        contract.setStatus(status);
        contract.setComments(StringUtils.defaultString(contract.getComments()) +
                "\nChanged to " + status + " at " + LocalDateTime.now());
        Contract savedContract = dataManager.save(contract);
        // Return the saved instance
        return savedContract;
    }

    public Contract saveContract(Contract contract) {
        Contract savedContract = dataManager.save(contract);
        // Return the saved instance
        return savedContract;
    }
}
