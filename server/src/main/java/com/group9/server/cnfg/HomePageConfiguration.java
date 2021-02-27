package com.group9.server.cnfg;

import com.group9.server.Course_Creation.AdminDashboard;
import com.group9.server.Course_Creation.CreateCourse;
import com.group9.server.Course_Creation.DaoLayer.CourseDao;
import com.group9.server.Course_Creation.DaoLayer.ICourseDao;
import com.group9.server.UserInputValidations.Interface.IValidate;
import com.group9.server.Course_Creation.ServiceLayer.CourseService;
import com.group9.server.Course_Creation.ServiceLayer.ICourseService;
import com.group9.server.UserInputValidations.Validators.ValidateCourseCreation;
import com.group9.server.Modules.Implementation.ApplicationHome;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

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
    public AdminDashboard dashboard(){
        return new AdminDashboard();
    }

    @Bean
    public IValidate validate(){
        return new ValidateCourseCreation();
    }

    @Bean
    public ICourseService Create_Courses(){
        return new CourseService();
    }

    @Bean
    public ICourseDao CreateCourses(){
        return new CourseDao();
    }

    @Value("${JDBC.driver}")
    private String driverClassName;
    @Value("${JDBC.url}")
    private String url;
    @Value("${JDBC.user}")
    private String user;
    @Value("${JDBC.password}")
    private String password;

    @Bean
    public DataSource dataSources(){
        DriverManagerDataSource bs =new DriverManagerDataSource();
        bs.setDriverClassName(driverClassName);
        bs.setUrl(url);
        bs.setUsername(user);
        bs.setPassword(password);
        return bs;
    }

    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
