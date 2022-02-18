package com.webpage.predictpoliticalpartyprice;
import com.webpage.predictpoliticalpartyprice.controller.PlotController;
import com.webpage.predictpoliticalpartyprice.dao.ContractDataRepository;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogDayDao;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogWeekDao;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
import com.webpage.predictpoliticalpartyprice.plotclasses.PlotCreator;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PredictPoliticalPartyPriceApplicationTests {
    @Resource
    @Qualifier("week")
    LogService contractLogWeekService;

    @Resource
    @Qualifier("day")
    LogService contractLogDayService;

    @Resource
    ContractLogRowMapper contractLogRowMapper;

    @Resource
    ContractLogDayDao contractLogDaoWeek;

    @Resource
    ContractLogWeekDao contractLogDaoDay;

    @Resource
    ContractDataRepository contractDataRepository;

    @Resource
    PlotCreator plotCreator;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(contractLogDaoDay);
        Assertions.assertNotNull(contractLogDaoWeek);
        Assertions.assertNotNull(contractLogRowMapper);
        Assertions.assertNotNull(contractLogDayService);
        Assertions.assertNotNull(contractLogWeekService);
        Assertions.assertNotNull(contractDataRepository);
        Assertions.assertNotNull(plotCreator);
    }

}
