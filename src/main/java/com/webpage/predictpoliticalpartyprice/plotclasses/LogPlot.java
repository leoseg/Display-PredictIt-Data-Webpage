package com.webpage.predictpoliticalpartyprice.plotclasses;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for plotting the contractlogs over time
 */
@Service
public class LogPlot {

    TimeSeriesCollection timeSeriesCollection;
    JFreeChart chart;


    /**
     * Adds a list of contractlogs as timeseries to the timeseriesdataset
     * @param contractLogList list to ad
     * @param label label of the timeseries
     */
    private void addContractLogs(List<ContractLog> contractLogList, String label){
        TimeSeries timeSeries = new TimeSeries(label);

        //Timeseries is adding one hours to each timestamp so it needs to be subtracted (coming from the calendartime)
        for(ContractLog contractLog: contractLogList){
            LocalDateTime localDateTime = contractLog.getTimestamp();
            Millisecond millisecond = new Millisecond(
                    0,
                    localDateTime.getSecond(),
                    localDateTime.getMinute(),
                    localDateTime.getHour(),
                    localDateTime.getDayOfMonth(),
                    localDateTime.getMonthValue(),
                    localDateTime.getYear());
            timeSeries.add(millisecond,contractLog.getLogvalue());
        }
        timeSeriesCollection.addSeries(timeSeries);
    }

    /**
     * Initialize a new timeseriescollection for one plot
     */
    public void initializeTimeSeriesCollection(){
        this.timeSeriesCollection = new TimeSeriesCollection();
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
     * @param request servletrequest with request parameters
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
     * @param attribute attribute to add contracts by
     * @param labels for each label a list of contractlogs is added
     */
    public void addContractLogs(ContractLogService contractLogService, LocalDate date, String attribute, String... labels){
        for(String label: labels){
            addContractLogs(contractLogService.getContractLogs(label,attribute,date),label);
        }
    }


}
