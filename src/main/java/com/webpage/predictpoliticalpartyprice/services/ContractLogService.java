package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.sql.Date;
import java.util.List;

public interface ContractLogService {

    List<ContractLog> getContractLogList(String label, Date date);
}
