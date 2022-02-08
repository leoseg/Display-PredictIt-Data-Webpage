package com.webpage.predictpoliticalpartyprice.plotclassestests;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.plotclasses.LogPlot;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.mockito.Mockito.when;

public class LogPlotTests {



    List<ContractLog> contractLogList;
    List<ContractLog> contractLogList2;

    @BeforeEach
    public void setupData(){
        EasyRandom testgenerator = new EasyRandom();
        this.contractLogList = testgenerator.objects(ContractLog.class,20).collect(Collectors.toList());
        this.contractLogList2 = testgenerator.objects(ContractLog.class,20).collect(Collectors.toList());

    }

    @Test
    public void givenContraglogLists_whenContragLogPlotCreateChartAndSaveAsJpg_thenUrlShouldMatchPattern() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        LogPlot logPlot = new LogPlot();
        ContractLogService contractLogService = Mockito.mock(ContractLogService.class);
        when(contractLogService.getContractLogs("label1","PoliticalLabel",LocalDate.parse("2021-12-03"))).thenReturn(contractLogList);
        when(contractLogService.getContractLogs("label2","PoliticalLabel",LocalDate.parse("2021-12-03"))).thenReturn(contractLogList2);
        logPlot.addContractLogs(contractLogService, LocalDate.parse("2021-12-03"),"label1","label2");
        logPlot.createChart(("testchart"));
        String actual = logPlot.saveAsJpgServlet(request);
        assertThat(actual).matches("^/chart.*jpeg$");
    }
}
