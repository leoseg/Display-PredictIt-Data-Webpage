package com.webpage.predictpoliticalpartyprice.controllertests;

import com.webpage.predictpoliticalpartyprice.controller.PlotController;
import com.webpage.predictpoliticalpartyprice.dao.UserRepository;
import com.webpage.predictpoliticalpartyprice.plotclasses.ContractLogPlot;
import com.webpage.predictpoliticalpartyprice.services.ContractLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = PlotController.class)
class PlotControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("week")
    ContractLogService contractLogWeekService;

    @MockBean
    @Qualifier("day")
    ContractLogService contractLogDayService;

    @MockBean
    ContractLogPlot contractLogPlot;


    @Test
    @WithMockUser
    void showWeekPlot() throws Exception {
        when(contractLogPlot.saveAsJpgServlet(any())).thenReturn("ploturl");
        this.mockMvc
                .perform(post("/plot/week").with(csrf())
                        .param("date","2022-12-02"))
                .andExpect(status().isOk())
                .andExpect(view().name("weekplot"))
                .andExpect(model().attribute("plotpath", "ploturl"));

        verify(contractLogPlot,times(1)).createChart("Data of the last 7 days since 2022-12-02");
        verify(contractLogPlot,times(1)).saveAsJpgServlet(any());
        verify(contractLogPlot,times(1)).addContractLogLists(contractLogWeekService,LocalDate.parse("2022-12-02"),"liberal","conservative");
    }

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DataSource dataSource;

    @Test
    @WithMockUser
    void showDayPlot() throws Exception {
        when(contractLogPlot.saveAsJpgServlet(any())).thenReturn("ploturl");
        this.mockMvc
                .perform(post("/plot/day").with(csrf())
                        .param("date","2022-12-02"))
                .andExpect(status().isOk())
                .andExpect(view().name("dayplot"))
                .andExpect(model().attribute("plotpath", "ploturl"));

        verify(contractLogPlot,times(1)).createChart("Data for the date 2022-12-02");
        verify(contractLogPlot,times(1)).saveAsJpgServlet(any());
        verify(contractLogPlot,times(1)).addContractLogLists(contractLogDayService,LocalDate.parse("2022-12-02"),"liberal","conservative");
    }

    @Test
    @WithMockUser
    void showPlotHome() throws Exception {
        this.mockMvc
                .perform(get("/plot").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("plothome"));
    }

    @Test
    public void getPlotPageWithoutLogin() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/plot"))
                .andExpect(status().is3xxRedirection());
        assertThat(result.andReturn().getResponse().getHeader("Location")).isEqualTo("http://localhost/login");
    }
}