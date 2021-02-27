package com.group9.server;

import com.group9.server.Modules.Interface.IHomePage;
import com.group9.server.cnfg.HomePageConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    String AppUserRole;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }

    @Override
    public void run(String[] args){

        IHomePage homePage;
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext(HomePageConfiguration.class);

        homePage = ctx.getBean("appHome",IHomePage.class);
        homePage.GetMenu();
        homePage.SelectMenu();
        ctx.close();

    }
}
