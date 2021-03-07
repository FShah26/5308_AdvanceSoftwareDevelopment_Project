package com.group9.server.cnfg;

import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.HomePage.ApplicationHome;
import com.group9.server.UserCreation.AddUser;
import com.group9.server.Login.AdminImpl;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Login.IValidateAddUser;
import com.group9.server.CourseCreation.ValidateCourseCreation;
import com.group9.server.UserCreation.AddUserDao;
import com.group9.server.UserCreation.IAddUserDao;
import com.group9.server.UserCreation.AddUserService;
import com.group9.server.UserCreation.IAddUserService;
import com.group9.server.CourseCreation.IValidate;
import com.group9.server.Login.ValidateAddUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.group9.server.*")
@Configuration
@PropertySource("classpath:JDBC.properties")
public class HomePageConfiguration {

    @Bean
    public ApplicationHome appHome(){
        return new ApplicationHome();
    }

    @Bean
    public AddUser add_user(){
        return new AddUser();
    }
    @Bean
    public AdminImpl admin() {
        return new AdminImpl();
    }

    @Bean
    public IDashboard dashboard(){
        return new AdminDashboard();
    }
    @Bean
    public IValidateAddUser validateAddUser(){
        return new ValidateAddUser();
    }
    @Bean
    public IValidate validate(){
        return new ValidateCourseCreation();
    }

   // @Bean
    //public ICourseService Create_Course(){
    //    return new CourseService();
    //}

   // @Bean
   // public ICourseDao CreateCourses(){
     //   return new CourseDao();
    //}

    @Bean
    public IAddUserService Add_User(){
        return new AddUserService();
    }

    @Bean
    public IAddUserDao AddUser(){
        return new AddUserDao();
    }




}
