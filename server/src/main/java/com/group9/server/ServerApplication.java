package com.group9.server;

import com.group9.server.Dashboard.IDashboard;
import com.group9.server.HomePage.HomePageConfiguration;
import com.group9.server.HomePage.IHomePage;
import com.group9.server.Login.IUserAuthLogic;
import com.group9.server.Login.UserAuthenticationLogic;
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
    IDashboard userDashboard;

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

        userDashboard = authLogic.initiateLogin(userType);
        if (authLogic.isAuthSuccessful()) {
            userDashboard.showDashboard();
        } else {
            System.out.println("Invalid username or password!");
            System.out.println("Please enter correct credentials.");
        }
        ctx.close();
        connection.close();
    }
}
