package com.webpage.predictpoliticalpartyprice;
import com.webpage.predictpoliticalpartyprice.dao.ContractDataRepository;
import com.webpage.predictpoliticalpartyprice.dao.LogDayDao;
import com.webpage.predictpoliticalpartyprice.dao.LogWeekDao;
import com.webpage.predictpoliticalpartyprice.dao.TwitterContractMapRepository;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
import com.webpage.predictpoliticalpartyprice.mapper.TwitterHashtagCountLogRowMapper;
import com.webpage.predictpoliticalpartyprice.plotclasses.PlotCreator;
import com.webpage.predictpoliticalpartyprice.plotclasses.TwitterContractLogsRenderer;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.checkerframework.checker.units.qual.A;
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
    LogDayDao contractLogDaoWeek;

    @Resource
    LogWeekDao contractLogDaoDay;

    @Resource
    ContractDataRepository contractDataRepository;

    @Resource
    PlotCreator plotCreator;

    @Resource
    TwitterHashtagCountLogRowMapper twitterHashtagCountLogRowMapper;

    @Resource
    TwitterContractLogsRenderer twitterContractLogsRenderer;

    @Resource
    TwitterContractMapRepository twitterContractMapRepository;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(contractLogDaoDay);
        Assertions.assertNotNull(contractLogDaoWeek);
        Assertions.assertNotNull(contractLogRowMapper);
        Assertions.assertNotNull(contractLogDayService);
        Assertions.assertNotNull(contractLogWeekService);
        Assertions.assertNotNull(contractDataRepository);
        Assertions.assertNotNull(plotCreator);
        Assertions.assertNotNull(twitterContractLogsRenderer);
        Assertions.assertNotNull(twitterContractMapRepository);
        Assertions.assertNotNull(twitterHashtagCountLogRowMapper);
    }

}
