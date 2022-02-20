package com.webpage.predictpoliticalpartyprice.plotclasses;

import com.webpage.predictpoliticalpartyprice.dao.TwitterContractMapRepository;
import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class for creatig time-series plots from given data
 */
@Service
public class LogPlot {

    TimeSeriesCollection timeSeriesCollection;
    JFreeChart chart;


    TwitterContractMapRepository twitterContractMapRepository;


    public LogPlot(TwitterContractMapRepository twitterContractMapRepository){
    this.twitterContractMapRepository = twitterContractMapRepository;
    }

    /**
     * Adds a list of contractlogs as timeseries to the timeseriesdataset
     * @param logList list to ad
     * @param label label of the timeseries
     */
    private void addContractLogs(List<ContractLog> logList, String label){
        TimeSeries timeSeries = new TimeSeries(label);
        for(ContractLog Log: logList){
            LocalDateTime localDateTime = Log.getTimestamp();
            Millisecond millisecond = new Millisecond(
                    0,
                    localDateTime.getSecond(),
                    localDateTime.getMinute(),
                    localDateTime.getHour(),
                    localDateTime.getDayOfMonth(),
                    localDateTime.getMonthValue(),
                    localDateTime.getYear());
            timeSeries.add(millisecond,Log.getLastTradePrice());
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
    public void createChart(String title,String xAxisLabel){
        chart = ChartFactory.createTimeSeriesChart(title,"Time",xAxisLabel,timeSeriesCollection);
        chart.getPlot().setBackgroundPaint( Color.WHITE );
        chart.getLegend().setBackgroundPaint(Color.BLACK);


    }

    /**
     * Set renderer to customize plot
     * @param renderer XYLineAndShape renderer
     */
    public void setRenderer(XYLineAndShapeRenderer renderer){
        chart.getXYPlot().setRenderer(renderer);
    }


    /**
     * Saves chart as jpeg
     * @param request servletrequest with request parameters
     * @return url to the chat
     * @throws IOException caused by ServletUtilities.saveChartAsJPEG
     */
    public String saveAsJpgServlet(HttpServletRequest request) throws IOException {
        String fileName = ServletUtilities.saveChartAsJPEG(chart,800,800, null, request.getSession());
        return request.getContextPath()+"/chart?filename=" +fileName;
    }

    /**
     * Adds multiple contractloglists with given labels at the given date to the timeseriesdataset
     * @param logService service for getting the contractlog data (can be week or day)
     * @param date date for getting the data from
     * @param attribute attribute to add contracts by
     * @param labels for each label a list of contractlogs is added
     */
    public void addContractLogsLists(LogService logService, LocalDate date, String attribute, String... labels){
        for(String label: labels){
            addContractLogs(logService.getContractLogs(label,attribute,date),label);
        }
    }

    /**
     * Adds multiple twitterhashtagcount lists with given labels at the given date to the timeseriesdataset
     * @param logService service for getting the twitterhashtagcount logs (can be week or day)
     * @param date date for getting the data from
     * @param labels for each label a list of twitterhashtagcount logs is added
     */
    public void addTwitterHashtagCountLogsLists(LogService logService, LocalDate date, Boolean doScaling, String... labels) {

        for(String label: labels){
            String hashtag = twitterContractMapRepository.findTwitterContractMapByName(label).getHashtag();
            addTwitterHashtagCountLogs(logService.getTwitterHashtagCountLog(label,date),hashtag,doScaling);
        }
    }

    /**
     * Adds a list of twitterhashtagcount logs as timeseries to the timeseriesdataset
     * @param logList list to ad
     * @param label label of the timeseries
     */
    public void addTwitterHashtagCountLogs(List<TwitterHashtagCountLog> logList,String label,boolean doScaling){
        int maxTweetcount= 1;
        if(doScaling){
            maxTweetcount = Collections.max(logList, Comparator.comparing(TwitterHashtagCountLog::getTweetCount)).getTweetCount();
            label += " scaled to 0-1 with max tweetcount "+maxTweetcount;
        }
        TimeSeries timeSeries = new TimeSeries(label);
        for(TwitterHashtagCountLog Log: logList){
            LocalDateTime localDateTime = Log.getTimestamp();
            Millisecond millisecond = new Millisecond(
                    0,
                    localDateTime.getSecond(),
                    localDateTime.getMinute(),
                    localDateTime.getHour(),
                    localDateTime.getDayOfMonth(),
                    localDateTime.getMonthValue(),
                    localDateTime.getYear());
            timeSeries.add(millisecond, (double) Log.getTweetCount() /maxTweetcount);
        }
        timeSeriesCollection.addSeries(timeSeries);
    }
}
