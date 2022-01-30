package com.webpage.predictpoliticalpartyprice.controllertests;


import com.webpage.predictpoliticalpartyprice.controller.UserController;
import com.webpage.predictpoliticalpartyprice.dao.UserRepository;
import com.webpage.predictpoliticalpartyprice.entities.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureDataJdbc
public class UserControllerTests {

    @Resource
    private MockMvc mockMvc;


    @MockBean
    UserRepository userRepository;



    @Test
    public void whenGetRegister_thenRegistrationFormReturned() throws Exception{
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_form"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", Matchers.isA(User.class)));
    }


    @Test
    public void givenUserData_whenPostProcessRegister_thenRegisterSucessReturnedAndUserCreated() throws Exception{
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);


        this.mockMvc.perform(post("/process_register")
                        .param("username","testuser")
                        .param("email","testemail@googlemail.com")
                        .param("password","testpassword")
                        .param("id","1234")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("register_success"));
        verify(userRepository,times(1)).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getUsername()).isEqualTo("testuser");
        assertThat(userArgumentCaptor.getValue().getEmail()).isEqualTo("testemail@googlemail.com");
        assertThat(new BCryptPasswordEncoder().matches("testpassword",userArgumentCaptor.getValue().getPassword())).isTrue();


    }
    @Test
    public void whenGetLogin_thenLoginViewReturned() throws Exception{
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }



}
