package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for accessing the contractlog data
 */
public interface LogService {

    /**
     * Gets a list of contractlogs with the same label
     * @param label label of contratlogs
     * @param date date for getting the data
     * @param attribute to choose column by
     * @return list of contractlogs object
     */
    List<ContractLog> getContractLogs(String label, String attribute,LocalDate date);

    /**
     * Gets a list of twitterhashtagcountlogs with the same label
     * @param label label of contratlogs
     * @param date date for getting the data
     * @return list of contractlogs object
     */
    List<TwitterHashtagCountLog> getTwitterHashtagCountLog(String label,LocalDate date);


 }
