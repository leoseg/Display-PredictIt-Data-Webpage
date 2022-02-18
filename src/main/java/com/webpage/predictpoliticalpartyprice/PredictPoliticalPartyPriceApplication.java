package com.webpage.predictpoliticalpartyprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class PredictPoliticalPartyPriceApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
    }

    public static void main(String[] args) {
        SpringApplication.run(PredictPoliticalPartyPriceApplication.class, args);
    }

}
