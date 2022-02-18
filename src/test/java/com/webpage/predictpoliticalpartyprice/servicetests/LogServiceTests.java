package com.webpage.predictpoliticalpartyprice.servicetests;


import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.jfree.data.time.Hour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LogServiceTests {

    @Resource
    @Qualifier("week")
    LogService logWeekService;

    @Resource
    @Qualifier("day")
    LogService logDayService;

    static Stream<Arguments> generateDayPolicitalLabelTestData() {
        return Stream.of(
                Arguments.of("conservative",LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.8, LocalDateTime.parse("2021-12-03T00:40:00.00000")),
                        new ContractLog(0.5, LocalDateTime.parse("2021-12-03T00:50:00.000000")),
                        new ContractLog(1.0, LocalDateTime.parse("2021-12-03T01:00:00.00000"))
                        )),
                Arguments.of("liberal", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.6, LocalDateTime.parse("2021-12-04T02:40:00.00000")),
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-04T02:50:00.00000")),
                        new ContractLog(1.4, LocalDateTime.parse("2021-12-04T03:00:00.00000"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDayPolicitalLabelTestData")
    void givenLabelDate_whenContractLogDayServiceGetsContractLogsByPoliticalLabel_thenActualShouldBeEqualExspected(String label, LocalDate date, List<ContractLog> exspected) {
        List<ContractLog> actual = logDayService.getContractLogs(label,"PoliticalLabel",date);
        int i =0;
        assert actual.size() == exspected.size();
        for (ContractLog contractLog : actual) {
            Assertions.assertEquals(contractLog.getLogvalue(), exspected.get(i).getLogvalue(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


    static Stream<Arguments> generateWeekPoliticalLabelTestData() {
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
    @MethodSource("generateWeekPoliticalLabelTestData")
    void givenLabelDate_whenContractLogWeekServiceGetsContractLogsByPoliticalLabel_thenActualShouldBeEqualExspected(String label, LocalDate date,List<ContractLog> exspected){
        List<ContractLog>actual = logWeekService.getContractLogs(label,"PoliticalLabel",date);
        int i =0;
        assert actual.size() == exspected.size();
        for (ContractLog contractLog : actual) {
            Assertions.assertEquals(contractLog.getLogvalue(), exspected.get(i).getLogvalue(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


    static Stream<Arguments> generateDayNameTestData() {
        return Stream.of(
                Arguments.of("Macron",LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.2, LocalDateTime.parse("2021-12-04T02:40:00.000000"))
                )),
                Arguments.of("Le Pen", LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-03T00:50:00.00000"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDayNameTestData")
    void givenLabelDate_whenContractLogDayServiceGetsContractLogsByName_thenActualShouldBeEqualExspected(String label, LocalDate date, List<ContractLog> exspected) {
        List<ContractLog> actual = logDayService.getContractLogs(label,"Name",date);
        int i =0;
        assert actual.size() == exspected.size();
        for (ContractLog contractLog : actual) {
            Assertions.assertEquals(contractLog.getLogvalue(), exspected.get(i).getLogvalue(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


    static Stream<Arguments> generateWeekNameTestData() {
        return Stream.of(
                Arguments.of("Macron", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.2, LocalDateTime.parse("2021-11-30T00:00:00")),
                        new ContractLog(0.6, LocalDateTime.parse("2021-12-01T00:00:00")),
                        new ContractLog(0.2, LocalDateTime.parse("2021-12-04T00:00:00"))
                )),
                Arguments.of("Márki-Zay", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.2, LocalDateTime.parse("2021-11-30T00:00:00")),
                        new ContractLog(0.3, LocalDateTime.parse("2021-12-02T00:00:00")),
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-04T00:00:00"))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateWeekNameTestData")
    void givenLabelDate_whenContractLogWeekServiceGetsContractLogsByName_thenActualShouldBeEqualExspected(String label, LocalDate date,List<ContractLog> exspected){
        List<ContractLog>actual = logWeekService.getContractLogs(label,"Name",date);
        int i =0;
        assert actual.size() == exspected.size();
        for (ContractLog contractLog : actual) {
            Assertions.assertEquals(contractLog.getLogvalue(), exspected.get(i).getLogvalue(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }

}
