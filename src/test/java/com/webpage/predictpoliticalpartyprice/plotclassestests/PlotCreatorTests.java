package com.webpage.predictpoliticalpartyprice.plotclassestests;

import com.webpage.predictpoliticalpartyprice.dao.TwitterContractMapRepository;
import com.webpage.predictpoliticalpartyprice.plotclasses.LogPlot;
import com.webpage.predictpoliticalpartyprice.plotclasses.PlotCreator;
import com.webpage.predictpoliticalpartyprice.plotclasses.TwitterContractLogsRenderer;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

public class PlotCreatorTests {


    @Mock
    LogService logServiceday;

    @Mock
    LogService logServiceweek;

    @Mock
    TwitterContractLogsRenderer twitterContractLogsRenderer;

    @Mock
    LogPlot logPlot;

    @BeforeEach
    public void injectMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenTestParamtersDay_whenCreatePlotCalled_thenMockedMembersShouldBeCalledWithTestParametersDay() throws IOException {
        PlotCreator plotCreator = new PlotCreator(logServiceday,logServiceweek,logPlot,twitterContractLogsRenderer);
        plotCreator.setPlotProperties("day","testattribute","contract","twitter");
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        plotCreator.createPlot(LocalDate.parse("2021-12-04"), mockHttpServletRequest,"testtitle","label1","label2");
        verify(logPlot,times(1)).initializeTimeSeriesCollection();
        verify(twitterContractLogsRenderer,times(1)).setNumberSeries(2);
        verify(logPlot,times(1)).addContractLogsLists(logServiceday,LocalDate.parse("2021-12-04"),"testattribute","label1","label2");
        verify(logPlot,times(1)).addTwitterHashtagCountLogsLists(logServiceday,LocalDate.parse("2021-12-04"),true,"label1","label2");
        verify(logPlot,times(1)).createChart("testtitle","Value of logs scaled to 0-1");
        verify(logPlot,times(1)).setRenderer(twitterContractLogsRenderer);
        verify(logPlot,times(1)).saveAsJpgServlet(mockHttpServletRequest);
    }

    @Test
    public void givenTestParamtersWeek_whenCreatePlotCalled_thenMockedMembersShouldBeCalledWithTestParametersWeek() throws IOException {
        PlotCreator plotCreator = new PlotCreator(logServiceday,logServiceweek,logPlot,twitterContractLogsRenderer);
        plotCreator.setPlotProperties("week","testattribute","contract","twitter");
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        plotCreator.createPlot(LocalDate.parse("2021-12-04"), mockHttpServletRequest,"testtitle","label1","label2");
        verify(logPlot,times(1)).initializeTimeSeriesCollection();
        verify(twitterContractLogsRenderer,times(1)).setNumberSeries(2);
        verify(logPlot,times(1)).addContractLogsLists(logServiceweek,LocalDate.parse("2021-12-04"),"testattribute","label1","label2");
        verify(logPlot,times(1)).addTwitterHashtagCountLogsLists(logServiceweek,LocalDate.parse("2021-12-04"),true,"label1","label2");
        verify(logPlot,times(1)).createChart("testtitle","Value of logs scaled to 0-1");
        verify(logPlot,times(1)).setRenderer(twitterContractLogsRenderer);
        verify(logPlot,times(1)).saveAsJpgServlet(mockHttpServletRequest);
    }

}
