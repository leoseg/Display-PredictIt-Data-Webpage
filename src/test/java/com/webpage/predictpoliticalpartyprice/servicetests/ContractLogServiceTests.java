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
import java.time.LocalDateTime;
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
                        new ContractLog(0.2, LocalDateTime.parse("2021-12-03T00:50:00.000000")),
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-03T00:40:00.00000")),
                        new ContractLog(0.8, LocalDateTime.parse("2021-12-03T01:00:00.00000"))
                        )),
                Arguments.of("liberal", LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-04T02:50:00.00000")),
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-04T02:40:00.00000")),
                        new ContractLog(0.6, LocalDateTime.parse("2021-12-04T03:00:00.00000"))
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
                        new ContractLog(0.2, LocalDateTime.parse("2021-11-30T00:00:00")),
                        new ContractLog(1.5, LocalDateTime.parse("2021-12-01T00:00:00")),
                        new ContractLog(2.3, LocalDateTime.parse("2021-12-03T00:00:00"))
                )),
                Arguments.of("liberal", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.3, LocalDateTime.parse("2021-11-30T00:00:00")),
                        new ContractLog(0.3, LocalDateTime.parse("2021-12-02T00:00:00")),
                        new ContractLog(2.4, LocalDateTime.parse("2021-12-04T00:00:00"))
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
