package com.webpage.predictpoliticalpartyprice.plotclasses;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import lombok.Getter;
import lombok.Setter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Class for plotting the contractlogs over time
 */
@Service
public class ContractLogPlot {

    TimeSeriesCollection timeSeriesCollection;
    JFreeChart chart;


    /**
     * Adds a list of contractlogs as timeseries to the timeseriesdataset
     * @param contractLogList list to ad
     * @param label label of the timeseries
     */
    private void addContractLogList(List<ContractLog> contractLogList, String label){
        TimeSeries timeSeries = new TimeSeries(label);

        //Timeseries is adding two hours to each timestamp so it needs to be subtracted
        for(ContractLog contractLog: contractLogList){
            timeSeries.add(new Millisecond(Date.from(contractLog.getTimestamp().minus(2, ChronoUnit.HOURS))),contractLog.getTradePrice());
        }
        timeSeriesCollection.addSeries(timeSeries);
    }

    /**
     * Creates a time to tradeprice timeserieschart
     * @param title title of chart
     */
    public void createChart(String title){
        chart = ChartFactory.createTimeSeriesChart(title,"Time","Tradeprice",timeSeriesCollection);
        chart.getPlot().setBackgroundPaint( Color.WHITE );
    }

    /**
     * Saves chart as jpeg
     * @param request servletrequest for displaying chart
     * @return url to the chat
     * @throws IOException caused by ServletUtilities.saveChartAsJPEG
     */
    public String saveAsJpgServlet(HttpServletRequest request) throws IOException {
        String fileName = ServletUtilities.saveChartAsJPEG(chart,700,400, null, request.getSession());
        return request.getContextPath()+"/chart?filename=" +fileName;
    }

    /**
     * Adds multiple contractloglists with given labels at the given date to the timeseriesdataset
     * @param contractLogService service for getting the contractlog data (can be week or day)
     * @param date date for getting the data from
     * @param labels for each label a list of contractlogs is added
     */
    public void addContractLogLists(ContractLogService contractLogService, LocalDate date, String... labels){
        this.timeSeriesCollection = new TimeSeriesCollection();
        for(String label: labels){
            addContractLogList(contractLogService.getContractLogList(label,date),label);
        }
    }


}
