package com.webpage.predictpoliticalpartyprice.servicetests;


import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContractLogServiceTests {

    @Resource
    @Qualifier("week")
    ContractLogService contractLogWeekService;

    @Resource
    @Qualifier("day")
    ContractLogService contractLogDayService;

    static Stream<Arguments> generateDayTestData() {
        return Stream.of(
                Arguments.of("conservative",LocalDate.parse("2021-12-02"), Arrays.asList(
                        new ContractLog(0.2, Instant.parse("2021-12-03T00:50:00.000000Z")),
                        new ContractLog(0.4, Instant.parse("2021-12-03T00:40:00.00000Z")),
                        new ContractLog(0.8, Instant.parse("2021-12-03T01:00:00.00000Z"))
                        )),
                Arguments.of("liberal", LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.4, Instant.parse("2021-12-04T02:50:00.00000Z")),
                        new ContractLog(0.4, Instant.parse("2021-12-04T02:40:00.00000Z")),
                        new ContractLog(0.6, Instant.parse("2021-12-04T03:00:00.00000Z"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDayTestData")
    void givenLabelDate_whenContractLogDayServiceGetsContractLogsByLabel_thenActualShouldBeEqualExspected(String label, LocalDate date, List<ContractLog> exspected) {
        List<ContractLog> actual = contractLogDayService.getContractLogsByLabel(label,date);
        int i =0;
        for (ContractLog contractLog : actual) {
            Assertions.assertEquals(contractLog.getTradePrice(), exspected.get(i).getTradePrice(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


    static Stream<Arguments> generateWeekTestData() {
        return Stream.of(
                Arguments.of("conservative", LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.2, Instant.parse("2021-11-30T00:00:00Z")),
                        new ContractLog(0.75, Instant.parse("2021-12-01T00:00:00Z")),
                        new ContractLog(0.38333333333333336, Instant.parse("2021-12-03T00:00:00Z"))
                )),
                Arguments.of("liberal", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.15, Instant.parse("2021-11-30T00:00:00Z")),
                        new ContractLog(0.3, Instant.parse("2021-12-02T00:00:00Z")),
                        new ContractLog(0.4, Instant.parse("2021-12-04T00:00:00Z"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateWeekTestData")
    void givenLabelDate_whenContractLogWeekServiceGetsContractLogsByLabel_thenActualShouldBeEqualExspected(String label, LocalDate date,List<ContractLog> exspected){
        List<ContractLog>result = contractLogWeekService.getContractLogsByLabel(label,date);
        int i =0;
        for (ContractLog contractLog : result) {
            Assertions.assertEquals(contractLog.getTradePrice(), exspected.get(i).getTradePrice(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


}
