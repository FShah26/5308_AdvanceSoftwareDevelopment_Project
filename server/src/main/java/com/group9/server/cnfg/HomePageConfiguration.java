package com.group9.server.cnfg;

import com.group9.server.AddUser.AdminDashboard;
import com.group9.server.AddUser.AddUser;
import com.group9.server.AddUser.DaoLayer.AddUserDao;
import com.group9.server.AddUser.DaoLayer.IAddUserDao;
import com.group9.server.AddUser.ServiceLayer.AddUserService;
import com.group9.server.AddUser.ServiceLayer.IAddUserService;
import com.group9.server.UserInputValidations.Interface.IValidate;
import com.group9.server.AddUser.ServiceLayer.AddUserService;
import com.group9.server.UserInputValidations.Validators.ValidateAddUser;
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
    public AddUser create(){
        return new AddUser();
    }

    @Bean
    public AdminDashboard dashboard(){
        return new AdminDashboard();
    }

    @Bean
    public IValidate validate(){
        return new ValidateAddUser();
    }

    @Bean
    public IAddUserService Create_Course(){
        return new AddUserService();
    }

    @Bean
    public IAddUserDao CreateCourses(){
        return new AddUserDao();
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
