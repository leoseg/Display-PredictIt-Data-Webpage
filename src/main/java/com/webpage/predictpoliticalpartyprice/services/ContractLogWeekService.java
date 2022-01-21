package com.webpage.predictpoliticalpartyprice.services;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogDao;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Gets list of contract logs of the last 7 days
 */
@Service(value="week")
public class ContractLogWeekService implements ContractLogService{

    @Resource
    @Qualifier("weekdao")
    ContractLogDao contractLogWeekDao;

    /**
     * Gets contract log list with same label grouped by the day
     * @param label label of contratlogs
     * @param date date for getting the data
     * @return  list of contractlog object
     */
    @Override
    public List<ContractLog> getContractLogList(String label, LocalDate date) {
        return contractLogWeekDao.getListOfContractLogs(date,label);
    }
}
