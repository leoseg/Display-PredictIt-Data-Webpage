package com.webpage.predictpoliticalpartyprice.controller;

import com.webpage.predictpoliticalpartyprice.entities.PlotInfo;
import com.webpage.predictpoliticalpartyprice.plotclasses.ContractLogPlot;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.jfree.chart.servlet.DisplayChart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;


@Controller
@RequestMapping("/plot")
public class PlotController {

    @Resource
    @Qualifier("day")
    ContractLogService contractLogDayService;

    @Resource
    @Qualifier("week")
    ContractLogService contractLogWeekService;

    @Resource
    ContractLogPlot contractLogPlot;
    /**
     * Registrates the Servlet for displaying the chart at the website
     * @return new registration bean
     */
    @Bean
    public ServletRegistrationBean MyServlet() {
        return new ServletRegistrationBean<>(new DisplayChart(),"/chart");
    }

    @PostMapping(value="/week")
    public String showWeekPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        LocalDate date = LocalDate.parse(plotInfo.getDate());
        contractLogPlot.addContractLogLists(contractLogWeekService,date,"liberal","conservative");
        contractLogPlot.createChart("Data of the last 7 days since "+date);
        model.addAttribute("plotpath", contractLogPlot.saveAsJpgServlet(request));
        return "weekplot";
    }

    @PostMapping(value="/day")
    public String showDayPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        LocalDate date = LocalDate.parse(plotInfo.getDate());
        contractLogPlot.addContractLogLists(contractLogDayService,date,"liberal","conservative");
        contractLogPlot.createChart("Data for the date "+date);
        model.addAttribute("plotpath", contractLogPlot.saveAsJpgServlet(request));
        return "dayplot";
    }

    @GetMapping()
    public String showPlotHome(Model model){
        model.addAttribute("localDate", LocalDate.now());
        return "plothome";
    }


}