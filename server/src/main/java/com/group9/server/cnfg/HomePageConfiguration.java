package com.group9.server.cnfg;

import com.group9.server.Modules.Implementation.*;
import com.group9.server.UserInputValidations.Interface.IValidateAddUser;
import com.group9.server.UserInputValidations.Validators.ValidateCourseCreation;
import com.group9.server.dao.Impl.AddUserDao;
import com.group9.server.dao.Impl.CourseDao;
import com.group9.server.dao.Interface.IAddUserDao;
import com.group9.server.Services.Implementation.AddUserService;
import com.group9.server.Services.Interface.IAddUserService;
import com.group9.server.UserInputValidations.Interface.IValidate;
import com.group9.server.UserInputValidations.Validators.ValidateAddUser;
import com.group9.server.dao.Interface.ICourseDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.group9.server.services.Interface.ICourseService;
import com.group9.server.services.Implementation.CourseService;
@ComponentScan(basePackages = "com.group9.server.*")
@Configuration
@PropertySource("classpath:JDBC.properties")
public class HomePageConfiguration {

    @Bean
    public ApplicationHome appHome(){
        return new ApplicationHome();
    }
    @Bean

    public CreateCourse create(){
        return new CreateCourse();
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
    public AdminDashboard dashboard(){
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

    @Bean
    public ICourseService Create_Course(){
        return new CourseService();
    }

    @Bean
    public ICourseDao CreateCourses(){
        return new CourseDao();
    }

    @Bean
    public IAddUserService Add_User(){
        return new AddUserService();
    }

    @Bean
    public IAddUserDao AddUser(){
        return new AddUserDao();
    }




}
