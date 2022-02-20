package com.webpage.predictpoliticalpartyprice.servicedaointegrationtests;


import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;
import com.webpage.predictpoliticalpartyprice.services.LogService;
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

/**
 * Tests for logdayservice integrated with corresponding daos
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LogDayServiceTests {



    @Resource
    @Qualifier("day")
    LogService logDayService;

    static Stream<Arguments> generateDayPolicitalLabelTestData() {
        return Stream.of(
                Arguments.of("conservative",LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-03T00:40:00.00000")),
                        new ContractLog(0.25, LocalDateTime.parse("2021-12-03T00:50:00.000000")),
                        new ContractLog(0.5, LocalDateTime.parse("2021-12-03T01:00:00.00000"))
                        )),
                Arguments.of("liberal", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.3, LocalDateTime.parse("2021-12-04T02:40:00.00000")),
                        new ContractLog(0.2, LocalDateTime.parse("2021-12-04T02:50:00.00000")),
                        new ContractLog(0.7, LocalDateTime.parse("2021-12-04T03:00:00.00000"))
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
            Assertions.assertEquals(contractLog.getLastTradePrice(), exspected.get(i).getLastTradePrice(), 0.0001);
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
            Assertions.assertEquals(contractLog.getLastTradePrice(), exspected.get(i).getLastTradePrice(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }

    static Stream<Arguments> generateDayTwitterhashtagcountTestData() {
        return Stream.of(
                Arguments.of("petro", LocalDate.parse("2021-12-01"), Arrays.asList(
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T15:00:00"), 890, 35),
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T17:00:00"), 890, 44),
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T18:00:00"), 890, 56)
                )),
                Arguments.of("pecresse", LocalDate.parse("2021-12-01"), Arrays.asList(
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T16:00:00"), 1000, 30),
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T17:00:00"), 1000, 70),
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T19:00:00"), 1000, 50)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDayTwitterhashtagcountTestData")
    void givenLabelDate_whenTwitterHashtagCountLogWeekServiceGetsLogsByName_thenActualShouldBeEqualExspected(String label,LocalDate date,List<TwitterHashtagCountLog> exspected){
        List<TwitterHashtagCountLog> actual = logDayService.getTwitterHashtagCountLog(label,date);
        int i =0;
        assert actual.size() == exspected.size();
        for (TwitterHashtagCountLog twitterHashtagCountLog : actual) {
            Assertions.assertEquals(twitterHashtagCountLog.getTweetCount(), exspected.get(i).getTweetCount());
            Assertions.assertEquals(twitterHashtagCountLog.getTotalTweetCount(), exspected.get(i).getTotalTweetCount());
            assert(twitterHashtagCountLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }


}
