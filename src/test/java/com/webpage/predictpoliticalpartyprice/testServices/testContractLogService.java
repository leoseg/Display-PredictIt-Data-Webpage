package com.webpage.predictpoliticalpartyprice.testServices;


import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class testContractLogService {

    @Resource
    @Qualifier("week")
    ContractLogService contractLogWeekService;

    @Resource
    @Qualifier("day")
    ContractLogService contractLogDayService;

    static Stream<Arguments> generateDayTestData() {
        return Stream.of(
                Arguments.of("conservative",Date.valueOf("2021-12-02"), Arrays.asList(
                        new ContractLog(0.2, Timestamp.valueOf("2021-12-03 00:50:00.000000")),
                        new ContractLog(0.4, Timestamp.valueOf("2021-12-03 00:40:00.000000")),
                        new ContractLog(0.8, Timestamp.valueOf("2021-12-03 01:00:00.000000"))
                        )),
                Arguments.of("liberal", Date.valueOf("2021-12-03"), Arrays.asList(
                        new ContractLog(0.4, Timestamp.valueOf("2021-12-04 02:50:00.000000")),
                        new ContractLog(0.4, Timestamp.valueOf("2021-12-04 02:40:00.000000")),
                        new ContractLog(0.6, Timestamp.valueOf("2021-12-04 03:00:00.000000"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDayTestData")
    void testIfCorrectEntriesForDayAreReturned(String label, Date date,List<ContractLog> exspected) {
        HashMap<String,List<ContractLog>> result = contractLogDayService.getLabelContractLogMap(label,date);
        List<ContractLog> contractLogList = result.get(label);
        int i =0;
        for (ContractLog contractLog : contractLogList) {
            Assert.assertEquals(contractLog.getTradePrice(),exspected.get(i).getTradePrice(),0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


    static Stream<Arguments> generateWeekTestData() {
        return Stream.of(
                Arguments.of("conservative",Date.valueOf("2021-12-02"), Arrays.asList(
                        new ContractLog(0.2, Timestamp.valueOf("2021-11-30 00:00:00")),
                        new ContractLog(0.75, Timestamp.valueOf("2021-12-01 00:00:00")),
                        new ContractLog(0.38333333333333336, Timestamp.valueOf("2021-12-02 00:00:00"))
                )),
                Arguments.of("liberal", Date.valueOf("2021-12-03"), Arrays.asList(
                        new ContractLog(0.15, Timestamp.valueOf("2021-11-29 00:00:00")),
                        new ContractLog(0.3, Timestamp.valueOf("2021-12-02 00:00:00")),
                        new ContractLog(0.4, Timestamp.valueOf("2021-12-03 00:00:00"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateWeekTestData")
    void testIfCorrectEntriesForWeekAreReturned(String label, Date date,List<ContractLog> exspected){
        HashMap<String,List<ContractLog>> result = contractLogWeekService.getLabelContractLogMap(label,date);
        List<ContractLog> contractLogList = result.get(label);
        int i =0;
        for (ContractLog contractLog : contractLogList) {
            Assert.assertEquals(contractLog.getTradePrice(),exspected.get(i).getTradePrice(),0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


}
