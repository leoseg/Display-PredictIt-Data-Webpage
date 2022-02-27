package com.webpage.predictpoliticalpartyprice.services;
import com.webpage.predictpoliticalpartyprice.dao.LogDayDao;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * Gets list of contractlogs for the given date
 */
@Service(value="day")
public class LogDayService implements LogService {


    @Resource
    LogDayDao logDayDao;


    /**
     * Gets list of contractlogs for given label grouped by 10 minute intervals
     * @param label label of contractlogs
     * @param attribute to choose contractlogs by
     * @param date date for getting the data
     * @return list of contractlog objects
     */
    @Override
    public List<ContractLog> getContractLogs(String label,String attribute, LocalDate date) {
        return  logDayDao.getListOfContractLogs(date,label,attribute);
    }

    /**
     * Gets twitterhashtagcount list with same label define grouped by 10 minute intervals
     * @param label label of contratlogs
     * @param date last date of the 7 days
     * @return  list of contractlog object
     */
    @Override
    public List<TwitterHashtagCountLog> getTwitterHashtagCountLog(String label, LocalDate date) {
        return logDayDao.getListOfTwitterHashtagCountLogs(date, label);

    }


}
