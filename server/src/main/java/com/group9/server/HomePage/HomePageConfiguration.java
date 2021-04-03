package com.group9.server.HomePage;

import com.group9.server.UserCreation.AddUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.group9.server.*")
@Configuration
@PropertySource("classpath:JDBC.properties")
public class HomePageConfiguration {

    @Bean
    public ApplicationHome appHome() {
        return new ApplicationHome();
    }

    @Bean
    public AddUser addUser() {
        return new AddUser();
    }
}
