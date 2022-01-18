package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ContractLogService {

    List<ContractLog> getContractLogList(String label, LocalDate date);
}
