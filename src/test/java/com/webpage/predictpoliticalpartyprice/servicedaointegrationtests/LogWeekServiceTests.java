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
 * Tests for logweekservice integrated with corresponding daos
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LogWeekServiceTests {


    @Resource
    @Qualifier("week")
    LogService logWeekService;

    static Stream<Arguments> generateWeekPoliticalLabelTestData() {
        return Stream.of(
                Arguments.of("conservative", LocalDate.parse("2021-12-03"), Arrays.asList(
                        new ContractLog(0.2, LocalDateTime.parse("2021-11-30T00:00:00")),
                        new ContractLog(0.75, LocalDateTime.parse("2021-12-01T00:00:00")),
                        new ContractLog(0.38333, LocalDateTime.parse("2021-12-03T00:00:00"))
                )),
                Arguments.of("liberal", LocalDate.parse("2021-12-04"), Arrays.asList(
                        new ContractLog(0.15, LocalDateTime.parse("2021-11-30T00:00:00")),
                        new ContractLog(0.3, LocalDateTime.parse("2021-12-02T00:00:00")),
                        new ContractLog(0.4, LocalDateTime.parse("2021-12-04T00:00:00"))
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
            Assertions.assertEquals(contractLog.getLastTradePrice(), exspected.get(i).getLastTradePrice(), 0.0001);
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
    void givenLabelDate_whenContractLogWeekServiceGetsContractLogsByName_thenActualShouldBeEqualExspected(String label, LocalDate date, List<ContractLog> exspected){
        List<ContractLog>actual = logWeekService.getContractLogs(label,"Name",date);
        int i =0;
        assert actual.size() == exspected.size();
        for (ContractLog contractLog : actual) {
            Assertions.assertEquals(contractLog.getLastTradePrice(), exspected.get(i).getLastTradePrice(), 0.0001);
            assert(contractLog.getTimestamp().equals(exspected.get(i).getTimestamp()));
            i++;
        }
    }

    static Stream<Arguments> generateWeekTwitterhashtagcountTestData() {
        return Stream.of(
                Arguments.of("pecresse", LocalDate.parse("2021-12-01"), Arrays.asList(
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-11-30T00:00:00"), 860, 120),
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T00:00:00"), 1000, 150)
                )),
                Arguments.of("petro", LocalDate.parse("2021-12-01"), Arrays.asList(
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-11-30T00:00:00"), 780, 135),
                        new TwitterHashtagCountLog(LocalDateTime.parse("2021-12-01T00:00:00"), 890, 135)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateWeekTwitterhashtagcountTestData")
    void givenLabelDate_whenTwitterHashtagCountLogWeekServiceGetsLogsByName_thenActualShouldBeEqualExspected(String label,LocalDate date,List<TwitterHashtagCountLog> exspected){
        List<TwitterHashtagCountLog> actual = logWeekService.getTwitterHashtagCountLog(label,date);
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
