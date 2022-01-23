package com.webpage.predictpoliticalpartyprice.plotclassestests;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.plotclasses.ContractLogPlot;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContractLogPlotTests {

    @Resource
    ContractLogPlot contractLogPlot;

    @MockBean
    @Qualifier("week")
    ContractLogService contractLogService;

    List<ContractLog> contractLogList;
    List<ContractLog> contractLogList2;

    @BeforeEach
    public void setupData(){
        EasyRandom testgenerator = new EasyRandom();
        this.contractLogList = testgenerator.objects(ContractLog.class,20).collect(Collectors.toList());
        this.contractLogList2 = testgenerator.objects(ContractLog.class,20).collect(Collectors.toList());

    }

    @Test
    public void testChartCreation() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        when(contractLogService.getContractLogList("label1",LocalDate.parse("2021-12-03"))).thenReturn(contractLogList);
        when(contractLogService.getContractLogList("label2",LocalDate.parse("2021-12-03"))).thenReturn(contractLogList2);
        contractLogPlot.addContractLogLists(contractLogService, LocalDate.parse("2021-12-03"),"label1","label2");
        contractLogPlot.createChart(("testchart"));
        String testurl = contractLogPlot.saveAsJpgServlet(request);
        assertThat(testurl).matches("^/chart.*jpeg$");
    }
}
