package com.webpage.predictpoliticalpartyprice.services;

import com.webpage.predictpoliticalpartyprice.dao.ContractLogDao;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Gets list of contractlogs for the given date
 */
@Service(value="day")
public class ContractLogDayService implements ContractLogService{


    @Resource
    @Qualifier("daydao")
    ContractLogDao contractLogDayDao;


    /**
     * Gets list of contractlogs for given label grouped by 10 minute intervals
     * @param label label of contractlogs
     * @param date date for getting the data
     * @return list of contractlog objects
     */
    @Override
    public List<ContractLog> getContractLogList(String label, LocalDate date) {
        return  contractLogDayDao.getListOfContractLogs(date,label);
    }
}
