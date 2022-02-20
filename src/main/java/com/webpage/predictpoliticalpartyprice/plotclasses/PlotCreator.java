package com.webpage.predictpoliticalpartyprice.plotclasses;

import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Facade calss for creating different timeseries-plots as jpeg
 */
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

    @Resource
    TwitterContractLogsRenderer twitterContractLogsRenderer;

    private LogService logService;

    private String attribute;

    public PlotCreator(@Qualifier("day") LogService contractLogDayService,@Qualifier("week") LogService contractLogWeekService,LogPlot logPlot,TwitterContractLogsRenderer twitterContractLogsRenderer){
        this.contractLogDayService = contractLogDayService;
        this.contractLogWeekService = contractLogWeekService;
        this.logPlot = logPlot;
        this.twitterContractLogsRenderer = twitterContractLogsRenderer;
    }

    private String[] dataSources;

    /**
     * Sets the properties for the plot
     * @param timeSpan timespan of the plot can be week or day
     * @param attribute the attribute to choose the plot data by
     * @param dataSources   the datasources for the plot can be twitter or contract
     */
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

    /**
     * Creates a plot as jpeg with the before setted properties
     * @param localDate date for which to create the plot
     * @param httpServletRequest request with data about the request
     * @param title title of the plot
     * @param labels labels of data of the plot
     * @return  url to the jpeg
     * @throws IOException thrown by the saveasJpgServlet method
     */
    public String createPlot(LocalDate localDate, HttpServletRequest httpServletRequest, String title, String... labels) throws IOException {
        logPlot.initializeTimeSeriesCollection();
        twitterContractLogsRenderer.setNumberSeries(labels.length);
        for(String datasource : dataSources) {
            switch (datasource) {
                case "contract":
                    logPlot.addContractLogsLists(logService,localDate,attribute,labels);
                    break;
                case "twitter":
                    logPlot.addTwitterHashtagCountLogsLists(logService,localDate,true,labels);
                    break;
            }
        }
        logPlot.createChart(title, "Value of logs scaled to 0-1");
        logPlot.setRenderer(twitterContractLogsRenderer);
        return logPlot.saveAsJpgServlet(httpServletRequest);

    }
}
