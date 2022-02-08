package com.webpage.predictpoliticalpartyprice.services;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogDayDao;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * Gets list of contractlogs for the given date
 */
@Service(value="day")
public class ContractLogDayService implements ContractLogService{


    @Resource
    ContractLogDayDao contractLogDayDao;


    /**
     * Gets list of contractlogs for given label grouped by 10 minute intervals
     * @param label label of contractlogs
     * @param attribute to choose contractlogs by
     * @param date date for getting the data
     * @return list of contractlog objects
     */
    @Override
    public List<ContractLog> getContractLogs(String label,String attribute, LocalDate date) {
        return  contractLogDayDao.getListOfContractLogs(date,label,attribute);
    }
}
