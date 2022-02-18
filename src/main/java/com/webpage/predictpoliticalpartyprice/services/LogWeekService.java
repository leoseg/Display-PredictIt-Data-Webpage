package com.webpage.predictpoliticalpartyprice.services;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogWeekDao;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

import java.util.List;

/**
 * Gets list of contract logs of the last 7 days
 */
@Service(value="week")
public class LogWeekService implements LogService {

    @Resource
    @Qualifier("weekdao")
    ContractLogWeekDao contractLogWeekDao;

    /**
     * Gets contract log list with same label define grouped by the day
     * @param label label of contratlogs
     * @param attribute to choose contractlogs by
     * @param date last date of the 7 days
     * @return  list of contractlog object
     */
    @Override
    public List<ContractLog> getContractLogs(String label, String attribute, LocalDate date) {
        return contractLogWeekDao.getListOfContractLogs(date,label,attribute);
    }
}
