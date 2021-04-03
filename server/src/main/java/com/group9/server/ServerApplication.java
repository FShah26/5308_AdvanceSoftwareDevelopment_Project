package com.group9.server;

import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.FacultyDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.StudentDashboard;
import com.group9.server.Database.ISingletonDatabase;
import com.group9.server.Database.SingletonDatabase;
import com.group9.server.HomePage.IHomePage;
import com.group9.server.Login.IUserAuthLogic;
import com.group9.server.Login.UserAuthenticationLogic;
import com.group9.server.cnfg.DBConfig;
import com.group9.server.cnfg.HomePageConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
    String userType;
    IUserAuthLogic authLogic;
    IDashboard dashboard;
    ISingletonDatabase database;
    Connection connection;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String[] args) throws SQLException {

        IHomePage homePage;
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HomePageConfiguration.class);
        authLogic = ctx.getBean(UserAuthenticationLogic.class);
        homePage = ctx.getBean("appHome", IHomePage.class);
        database = ctx.getBean(SingletonDatabase.class);
        database = database.getInstance();
        connection = database.getConnection(ctx.getBean(DBConfig.class));

        homePage.getMenu();
        userType = homePage.UserTypeSelectMenu();

        boolean loginSuccess = authLogic.initiateLogin(userType);
        if (loginSuccess) {
            System.out.println("Login Successful !");
            if (userType.equals("admin")) {
                dashboard = ctx.getBean(AdminDashboard.class);
            }
            else if(userType.equals("student")){
                dashboard = ctx.getBean(StudentDashboard.class);
            }
            else if(userType.equals("faculty")){
                dashboard = ctx.getBean(FacultyDashboard.class);
            }
            dashboard.setUsername(authLogic.getUsername());
            dashboard.dashboard();

        } else {
            System.out.println("Invalid username or password!");
            System.out.println("Please enter correct credentials.");
        }
        ctx.close();
        connection.close();
    }
}
