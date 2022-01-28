package com.webpage.predictpoliticalpartyprice.security;

import com.webpage.predictpoliticalpartyprice.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;



@Configuration
@EnableWebSecurity
@ConditionalOnWebApplication
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * Creates an authentication provider using bcrypt as password encoder and the custom user details service
     * @return new instance of CustomUserDetailsImpl
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Configures security options and enables security filters
     * @param http Object to be configured
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers("/plot/**").authenticated()
                .antMatchers("/").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .defaultSuccessUrl("/plot")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }
}
