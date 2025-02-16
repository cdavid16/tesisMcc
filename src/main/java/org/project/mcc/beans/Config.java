package org.project.mcc.beans;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//
//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            ProcessPullRequest processor = new ProcessPullRequest(restTemplate);
//            processor.processRepositories();
//        };
//    }

}
