package com.group9.server.cnfg;

import com.group9.server.Modules.Implementation.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HomePageConfiguration {

    @Bean
    public ApplicationHome appHome(){
        return new ApplicationHome();
    }
}
