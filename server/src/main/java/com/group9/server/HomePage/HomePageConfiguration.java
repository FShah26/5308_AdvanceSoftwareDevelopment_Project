package com.group9.server.HomePage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.group9.server.*")
@Configuration
@PropertySource("classpath:JDBC-${spring.profiles.active}.properties")
public class HomePageConfiguration {

    @Bean
    public ApplicationHome appHome() {
        return new ApplicationHome();
    }
}
