package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for accessing the contractlog data
 */
public interface ContractLogService {

    /**
     * Gets a list of contractlogs with the same label
     * @param label label of contratlogs
     * @param date date for getting the data
     * @return list of contractlogs object
     */
    List<ContractLog> getContractLogsByLabel(String label, LocalDate date);
}
