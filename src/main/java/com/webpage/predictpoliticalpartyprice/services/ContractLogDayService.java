package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.dao.ContractLogDao;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service(value="day")
public class ContractLogDayService implements ContractLogService{


    @Resource
    @Qualifier("daydao")
    ContractLogDao contractLogDayDao;



    @Override
    public HashMap<String, List<ContractLog>> getLabelContractLogMap(String label, Date date) {
        List<ContractLog> contractLogList = contractLogDayDao.getListOfContractLogs(date,label);
        HashMap<String, List<ContractLog>> labelContractLogMap = new HashMap<>();
        labelContractLogMap.put(label,contractLogList);
        return labelContractLogMap;
    }
}
