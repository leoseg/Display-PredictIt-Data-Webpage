package com.webpage.predictpoliticalpartyprice.controllertests;

import com.webpage.predictpoliticalpartyprice.controller.PlotController;
import com.webpage.predictpoliticalpartyprice.dao.ContractDataRepository;
import com.webpage.predictpoliticalpartyprice.dao.UserRepository;
import com.webpage.predictpoliticalpartyprice.entities.ContractData;
import com.webpage.predictpoliticalpartyprice.entities.PlotInfo;
import com.webpage.predictpoliticalpartyprice.plotclasses.LogPlot;
import com.webpage.predictpoliticalpartyprice.plotclasses.PlotCreator;
import com.webpage.predictpoliticalpartyprice.services.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = PlotController.class)
class PlotControllerTests {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    PlotCreator plotCreator;

    @MockBean
    ContractDataRepository contractDataRepository;


    @Test
    @WithMockUser
    void givenDate_whenPostPlotWeek_thenWeekPlotViewWithChartReturned() throws Exception {
        when(plotCreator.createPlot(eq(LocalDate.parse("2022-12-02")),any(HttpServletRequest.class),eq("Data of the last 7 days since 2022-12-02"),eq("liberal"),eq("conservative"))).thenReturn("ploturl");
        this.mockMvc
                .perform(post("/plot/week").with(csrf())
                        .param("date", "2022-12-02"))
                .andExpect(status().isOk())
                .andExpect(view().name("weekplot"))
                .andExpect(model().attribute("plotpath", "ploturl"));

        verify(plotCreator, times(1)).setPlotProperties(eq("week"),eq("PoliticalLabel"),eq("contract"));
        verify(plotCreator, times(1)).createPlot(eq(LocalDate.parse("2022-12-02")),any(HttpServletRequest.class),eq("Data of the last 7 days since 2022-12-02"),eq("liberal"),eq("conservative"));
    }

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DataSource dataSource;

    @Test
    @WithMockUser
    void givenDate_whenPostPlotDay_thenDayPlotViewWithChartReturned() throws Exception {
        when(plotCreator.createPlot(eq(LocalDate.parse("2022-12-02")),any(HttpServletRequest.class),eq("Data for the date 2022-12-02"),eq("liberal"),eq("conservative"))).thenReturn("ploturl");
        this.mockMvc
                .perform(post("/plot/day").with(csrf())
                        .param("date", "2022-12-02"))
                .andExpect(status().isOk())
                .andExpect(view().name("dayplot"))
                .andExpect(model().attribute("plotpath", "ploturl"));

        verify(plotCreator, times(1)).setPlotProperties("day","PoliticalLabel","contract");
        verify(plotCreator, times(1)).createPlot(eq(LocalDate.parse("2022-12-02")),any(HttpServletRequest.class),eq("Data for the date 2022-12-02"),eq("liberal"),eq("conservative"));
    }

    @Test
    @WithMockUser
    void givenAuthenticatedUser_whenGetPlot_thenPlothomeView() throws Exception {

        when(contractDataRepository.findAll()).thenReturn(Arrays.asList(new ContractData(),new ContractData()));
        this.mockMvc
                .perform(get("/plot").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("plothome"))
                .andExpect(model().attributeExists("plotInfo"))
                .andExpect(model().attribute("localDate",LocalDate.now().toString()))
                .andExpect(model().attribute("candidateNames",Arrays.asList(new ContractData(),new ContractData())));
    }

    @Test
    public void givenUnauthenticatedAcess_whenGetPlot_thenRedirectLoginView() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/plot"))
                .andExpect(status().is3xxRedirection());
        assertThat(result.andReturn().getResponse().getHeader("Location")).isEqualTo("http://localhost/login");
    }

    @Test
    @WithMockUser
    void givenDate_whenPostPlotCandidates_thenCandidatesPlotViewReturned() throws Exception {
        when(plotCreator.createPlot(eq(LocalDate.parse("2022-12-02")),any(HttpServletRequest.class),eq("Data for the date 2022-12-02"),eq("Max"),eq("Maxina"))).thenReturn("ploturlday");
        when(plotCreator.createPlot(eq(LocalDate.parse("2022-12-02")),any(HttpServletRequest.class),eq("Data for the last 7 days since 2022-12-02"),eq("Max"),eq("Maxina"))).thenReturn("ploturlweek");
        this.mockMvc
                .perform(post("/plot/candidates").with(csrf())
                        .param("date", "2022-12-02"))
                .andExpect(status().isOk())
                .andExpect(view().name("candidatesplot"))
                .andExpect(model().attribute("plotpathday", "ploturlday"))
                .andExpect(model().attribute("plotpathweek","ploturlweek"));

        verify(plotCreator, times(1)).setPlotProperties("day","Name","contract");
        verify(plotCreator, times(1)).setPlotProperties("week","Name","contract");
        verify(plotCreator, times(2)).createPlot(any(),any(),any(),any());
    }
}