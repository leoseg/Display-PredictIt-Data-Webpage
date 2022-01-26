package com.webpage.predictpoliticalpartyprice;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogDayDao;
import com.webpage.predictpoliticalpartyprice.dao.ContractLogWeekDao;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PredictPoliticalPartyPriceApplicationTests {
    @Resource
    @Qualifier("week")
    ContractLogService contractLogWeekService;

    @Resource
    @Qualifier("day")
    ContractLogService contractLogDayService;

    @Resource
    ContractLogRowMapper contractLogRowMapper;

    @Resource
    ContractLogDayDao contractLogDaoWeek;

    @Resource
    ContractLogWeekDao contractLogDaoDay;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(contractLogDaoDay);
        Assertions.assertNotNull(contractLogDaoWeek);
        Assertions.assertNotNull(contractLogRowMapper);
        Assertions.assertNotNull(contractLogDayService);
        Assertions.assertNotNull(contractLogWeekService);
    }

}
