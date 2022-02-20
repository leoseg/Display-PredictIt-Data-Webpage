package com.webpage.predictpoliticalpartyprice.plotclassestests;

import com.webpage.predictpoliticalpartyprice.dao.TwitterContractMapRepository;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterContractMap;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;
import com.webpage.predictpoliticalpartyprice.plotclasses.LogPlot;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LogPlotTests {



    List<ContractLog> contractLogList;
    List<ContractLog> contractLogList2;
    List<TwitterHashtagCountLog> twitterhashtagcountlist;
    List<TwitterHashtagCountLog> twitterhashtagcountlist2;
    TwitterContractMap twitterContractMap1;
    TwitterContractMap twitterContractMap2;

    @Mock
    TwitterContractMapRepository twitterContractMapRepository;

    @BeforeEach
    public void setupData(){
        MockitoAnnotations.openMocks(this);
        EasyRandom testgenerator = new EasyRandom();
        this.contractLogList = testgenerator.objects(ContractLog.class,20).collect(Collectors.toList());
        this.contractLogList2 = testgenerator.objects(ContractLog.class,20).collect(Collectors.toList());
        this.twitterhashtagcountlist = testgenerator.objects(TwitterHashtagCountLog.class,20).collect(Collectors.toList());
        this.twitterhashtagcountlist2 = testgenerator.objects(TwitterHashtagCountLog.class,20).collect(Collectors.toList());
        this.twitterContractMap1 = new TwitterContractMap();
        twitterContractMap1.setHashtag("#test1");
        twitterContractMap1.setName("label1");
        this.twitterContractMap2 = new TwitterContractMap();
        twitterContractMap2.setHashtag("#test2");
        twitterContractMap2.setName("label2");


    }

    @Test
    public void givenContraglogLists_whenContragLogPlotCreateChartAndSaveAsJpg_thenUrlShouldMatchPattern() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        LogPlot logPlot = new LogPlot(twitterContractMapRepository);
        LogService logService = Mockito.mock(LogService.class);
        logPlot.initializeTimeSeriesCollection();
        when(logService.getContractLogs("label1","PoliticalLabel",LocalDate.parse("2021-12-03"))).thenReturn(contractLogList);
        when(logService.getContractLogs("label2","PoliticalLabel",LocalDate.parse("2021-12-03"))).thenReturn(contractLogList2);
        logPlot.addContractLogsLists(logService, LocalDate.parse("2021-12-03"),"PoliticalLabel","label1","label2");
        logPlot.createChart("testchart","testlabel");
        String actual = logPlot.saveAsJpgServlet(request);
        assertThat(actual).matches("^/chart.*jpeg$");
        verify(logService,times(2)).getContractLogs(any(),any(),any());
        verify(logService,atLeast(1)).getContractLogs("label1","PoliticalLabel",LocalDate.parse("2021-12-03"));
        verify(logService,atLeast(1)).getContractLogs("label2","PoliticalLabel",LocalDate.parse("2021-12-03"));
    }

    @Test
    public void givenTwitterhashtagcountlogLists_whenLogPlotCreateChartAndSaveAsJpg_thenUrlShouldMatchPattern() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        LogPlot logPlot = new LogPlot(twitterContractMapRepository);
        LogService logService = Mockito.mock(LogService.class);
        logPlot.initializeTimeSeriesCollection();
        when(logService.getTwitterHashtagCountLog("label1",LocalDate.parse("2021-12-03"))).thenReturn(twitterhashtagcountlist);
        when(logService.getTwitterHashtagCountLog("label2",LocalDate.parse("2021-12-03"))).thenReturn(twitterhashtagcountlist2);
        when(twitterContractMapRepository.findTwitterContractMapByName("label1")).thenReturn(twitterContractMap1);
        when(twitterContractMapRepository.findTwitterContractMapByName("label2")).thenReturn(twitterContractMap1);
        logPlot.addTwitterHashtagCountLogsLists(logService, LocalDate.parse("2021-12-03"),true,"label1","label2");
        logPlot.createChart("testchart","testlabel");
        String actual = logPlot.saveAsJpgServlet(request);
        assertThat(actual).matches("^/chart.*jpeg$");
        verify(twitterContractMapRepository,times(2)).findTwitterContractMapByName(any());
        verify(twitterContractMapRepository,atLeast(1)).findTwitterContractMapByName("label1");
        verify(twitterContractMapRepository,atLeast(1)).findTwitterContractMapByName("label2");
        verify(logService,times(2)).getTwitterHashtagCountLog(any(),any());
        verify(logService,atLeast(1)).getTwitterHashtagCountLog("label1",LocalDate.parse("2021-12-03"));
        verify(logService,atLeast(1)).getTwitterHashtagCountLog("label2",LocalDate.parse("2021-12-03"));

    }
}
