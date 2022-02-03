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

/**
 * Controller for showing the plots
 */
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
    /*
    Creates and shows plot of the last 7 days, takes date as input parameter from the plot info object
     */
    @PostMapping(value="/week")
    public String showWeekPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        LocalDate date = LocalDate.parse(plotInfo.getDate());
        contractLogPlot.addContractLogsByLabel(contractLogWeekService,date,"liberal","conservative");
        contractLogPlot.createChart("Data of the last 7 days since "+date);
        model.addAttribute("plotpath", contractLogPlot.saveAsJpgServlet(request));
        model.addAttribute("localDate", LocalDate.now());
        return "weekplot";
    }

    /*
    Creates and shows plot of the data of the day, takes date as input parameter from the plotinfo object
     */
    @PostMapping(value="/day")
    public String showDayPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        LocalDate date = LocalDate.parse(plotInfo.getDate());
        contractLogPlot.addContractLogsByLabel(contractLogDayService,date,"liberal","conservative");
        contractLogPlot.createChart("Data for the date "+date);
        model.addAttribute("plotpath", contractLogPlot.saveAsJpgServlet(request));
        model.addAttribute("localDate", LocalDate.now());
        return "dayplot";
    }
    /*
    Shows the homepage of plots, with one button leading to the dayplot and one to the weekplot
     */
    @GetMapping()
    public String showPlotHome(Model model){
        model.addAttribute("localDate", LocalDate.now().toString());
        return "plothome";
    }


}
