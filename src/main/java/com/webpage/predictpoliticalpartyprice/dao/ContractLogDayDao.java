package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.time.LocalDate;
import java.util.List;

public interface ContractLogDayDao {
    /**
     * Gets list of contractlogs with same label grouped by the time with their average latestTradePrice
     * @param date date to use
     * @param label only contractlogs with has the samel label for the attribute are choosen
     * @param attribute choose contracty by this attribute
     * @return list of contractlog objects
     */
    List<ContractLog> getListOfContractLogs(LocalDate date, String label,String attribute);
}
