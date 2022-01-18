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


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ContractLogPlot {

    TimeSeriesCollection timeSeriesCollection;

    @Getter@Setter
    String title;
    JFreeChart chart;

    public ContractLogPlot(String title){
        this.timeSeriesCollection = new TimeSeriesCollection();
        this.title = title;
    }

    public void addContractLogList(List<ContractLog> contractLogList, String label){
        TimeSeries timeSeries = new TimeSeries(label);

        for(ContractLog contractLog: contractLogList){
            timeSeries.add(new Millisecond(Date.from(contractLog.getTimestamp())),contractLog.getTradePrice());
        }
        timeSeriesCollection.addSeries(timeSeries);
    }

    public void createChart(){
        chart = ChartFactory.createTimeSeriesChart(title,"Time","Tradeprice",timeSeriesCollection);
    }


    public String saveAsJpgServlet(HttpServletRequest request) throws IOException {
        String fileName = ServletUtilities.saveChartAsJPEG(chart,700,400, null, request.getSession());
        return request.getContextPath()+"/chart?filename=" +fileName;
    }

    public void addContractLogLists(ContractLogService contractLogService, LocalDate date, String... labels){
        for(String label: labels){
            addContractLogList(contractLogService.getContractLogList(label,date),label);
        }
    }


}
