package com.group9.server;

import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.StudentDashboard;
import com.group9.server.HomePage.IHomePage;
import com.group9.server.Login.IUser;
import com.group9.server.Login.IUserAuthLogic;
import com.group9.server.Login.UserAuthenticationLogic;
import com.group9.server.cnfg.HomePageConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    String AppUserRole;
    IUserAuthLogic authLogic;
    IDashboard dashboard;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String[] args) {

        IHomePage homePage;
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HomePageConfiguration.class);
        authLogic = ctx.getBean(UserAuthenticationLogic.class);
        homePage = ctx.getBean("appHome", IHomePage.class);

        homePage.getMenu();
        AppUserRole = homePage.selectMenu();

        boolean isValid = authLogic.initiateLogin(AppUserRole);
        if (isValid) {
            System.out.println("Login Successful !");
            if (AppUserRole.equals("admin")) {
                dashboard = ctx.getBean(AdminDashboard.class);
            } else if(AppUserRole.equals("student")){
                dashboard = ctx.getBean(StudentDashboard.class);
            }
            dashboard.setUsername(authLogic.getUsername());
            dashboard.dashboard();

        } else {
            System.out.println("Invalid username or password!");
            System.out.println("Please enter correct credentials.");
        }
        ctx.close();
    }
}
