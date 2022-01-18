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

@Service(value="week")
public class ContractLogWeekService implements ContractLogService{

    @Resource
    @Qualifier("weekdao")
    ContractLogDao contractLogWeekDao;


    @Override
    public List<ContractLog> getContractLogList(String label, LocalDate date) {
        return contractLogWeekDao.getListOfContractLogs(date,label);
    }
}
