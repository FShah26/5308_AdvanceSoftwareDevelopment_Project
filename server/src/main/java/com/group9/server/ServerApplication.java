package com.group9.server;

import com.group9.server.Course_Creation.AdminDashboard;
import com.group9.server.Course_Creation.CreateCourse;
import com.group9.server.Course_Creation.ServiceLayer.ICourseService;
import com.group9.server.cnfg.HomePageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jd;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String[] args){

        AdminDashboard cc;
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext(HomePageConfiguration.class);
        cc = ctx.getBean("dashboard", AdminDashboard.class);
        cc.dashboard();
    }
}
