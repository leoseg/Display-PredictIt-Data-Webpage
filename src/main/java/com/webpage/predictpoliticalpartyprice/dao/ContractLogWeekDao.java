package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;


import java.time.LocalDate;
import java.util.List;

public interface ContractLogWeekDao {
    /**
     * Gets list of contractlogs with same label grouped by the time with their average latestTradePrice
     * @param date date to use
     * @param Label only contractlogs with this label are requested
     * @return list of contractlog objects
     */
    List<ContractLog> getListOfContractLogs(LocalDate date, String Label);

}
