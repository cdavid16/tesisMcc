package org.project.mcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"org.project.mcc.beans", "org.project.mcc.controllers"})
@EnableWebMvc
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
