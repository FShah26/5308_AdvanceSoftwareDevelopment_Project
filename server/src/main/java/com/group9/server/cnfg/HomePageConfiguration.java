package com.group9.server.cnfg;

import com.group9.server.Modules.Implementation.AdminImpl;
import com.group9.server.Modules.Implementation.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.group9.server.*")
@Configuration
public class HomePageConfiguration {

    @Bean
    public ApplicationHome appHome() {
        return new ApplicationHome();
    }

    @Bean
    public AdminImpl admin() {
        return new AdminImpl();
    }
}
