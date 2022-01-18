package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ContractLogDao {

    List<ContractLog> getListOfContractLogs(LocalDate date, String Label);

}
