package com.group9.server.cnfg;

import com.group9.server.Announcements.IAnnouncementPersistence;
import com.group9.server.CourseCreation.IValidate;
import com.group9.server.CourseCreation.ValidateCourseCreation;
import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.HomePage.ApplicationHome;
import com.group9.server.Login.adminImpl;
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
    public AddUser add_user() {
        return new AddUser();
    }

    @Bean
    public adminImpl admin() {
        return new adminImpl();
    }

    @Bean
    public IValidate validate() {
        return new ValidateCourseCreation();
    }

}
