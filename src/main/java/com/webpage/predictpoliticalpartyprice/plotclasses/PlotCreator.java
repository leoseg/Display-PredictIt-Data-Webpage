package com.webpage.predictpoliticalpartyprice.plotclasses;

import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class PlotCreator {

    @Resource
    @Qualifier("day")
    LogService contractLogDayService;

    @Resource
    @Qualifier("week")
    LogService contractLogWeekService;

    @Resource
    LogPlot logPlot;

    private LogService logService;

    private String attribute;



    private String[] dataSources;

    public void setPlotProperties(String timeSpan,String attribute, String... dataSources){
        switch (timeSpan){
            case "week":
                this.logService = contractLogWeekService;
                break;
            case "day":
                this.logService = contractLogDayService;
                break;

        }
        this.attribute =attribute;
        this.dataSources = dataSources;

    }

    public String createPlot(LocalDate localDate, HttpServletRequest httpServletRequest, String title, String... labels) throws IOException {
        logPlot.initializeTimeSeriesCollection();

        for(String datasource : dataSources) {
            switch (datasource) {
                case "contract":
                    logPlot.addContractLogs(logService,localDate,attribute,labels);
            }
        }
        logPlot.createChart(title);
        return logPlot.saveAsJpgServlet(httpServletRequest);

    }
}
