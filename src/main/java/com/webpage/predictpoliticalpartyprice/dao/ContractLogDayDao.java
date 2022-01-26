package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.time.LocalDate;
import java.util.List;

public interface ContractLogDayDao {
    public List<ContractLog> getListOfContractLogs(LocalDate date, String Label);
}
