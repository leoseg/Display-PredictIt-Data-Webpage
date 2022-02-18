package com.webpage.predictpoliticalpartyprice.controller;

import com.webpage.predictpoliticalpartyprice.dao.ContractDataRepository;
import com.webpage.predictpoliticalpartyprice.entities.PlotInfo;
import com.webpage.predictpoliticalpartyprice.plotclasses.PlotCreator;
import org.jfree.chart.servlet.DisplayChart;
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
    ContractDataRepository contractDataRepository;

    @Resource
    PlotCreator plotCreator;


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
        plotCreator.setPlotProperties("week","PoliticalLabel","contract");
        String title = "Data of the last 7 days since "+date;
        model.addAttribute("plotpath",plotCreator.createPlot(date,request,title,"liberal","conservative"));
        model.addAttribute("localDate", LocalDate.now().toString());
        return "weekplot";
    }

    /*
    Creates and shows plot of the data of the day, takes date as input parameter from the plotinfo object
     */
    @PostMapping(value="/day")
    public String showDayPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        LocalDate date = LocalDate.parse(plotInfo.getDate());
        plotCreator.setPlotProperties("day","PoliticalLabel","contract");
        model.addAttribute("plotpath", plotCreator.createPlot(date,request,"Data for the date "+date,"liberal","conservative"));
        model.addAttribute("localDate", LocalDate.now().toString());
        return "dayplot";
    }

    /*
    Creates and shows plot of different candidates
    */
    @PostMapping(value="/candidates")
    public String showCandidatesPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        LocalDate date = LocalDate.parse(plotInfo.getDate());
        String[] candidateNames = plotInfo.getPresidentNames().toArray(new String[0]);
        plotCreator.setPlotProperties("day","Name","contract");
        model.addAttribute("plotpathday", plotCreator.createPlot(date,request,"Data for the date "+date,candidateNames));
        plotCreator.setPlotProperties("week","Name","contract");
        model.addAttribute("plotpathweek", plotCreator.createPlot(date,request,"Data of the last 7 days since "+date,candidateNames));
        return "candidatesplot";
    }
    /*
    Shows the homepage of plots, with one button leading to the dayplot and one to the weekplot
     */
    @GetMapping()
    public String showPlotHome(Model model){
        model.addAttribute("candidateNames",contractDataRepository.findAll());
        model.addAttribute("localDate", LocalDate.now().toString());
        model.addAttribute("plotInfo",new PlotInfo());
        return "plothome";
    }


}
