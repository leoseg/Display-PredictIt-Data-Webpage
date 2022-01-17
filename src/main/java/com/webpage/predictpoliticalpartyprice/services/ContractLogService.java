package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface ContractLogService {

    HashMap<String, List<ContractLog>> getLabelContractLogMap(String label, Date date);
}
