package com.group9.server;

import com.group9.server.Modules.Implementation.AdminDashboard;
import com.group9.server.Modules.Interface.IDashboard;
import com.group9.server.Modules.Interface.IHomePage;
import com.group9.server.Modules.Interface.IUser;
import com.group9.server.cnfg.HomePageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    String AppUserRole;

    IDashboard cc;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String[] args){

        IHomePage homePage;
        IUser appUser;
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext(HomePageConfiguration.class);

        homePage = ctx.getBean("appHome", IHomePage.class);
        homePage.GetMenu();
        AppUserRole = homePage.SelectMenu();

        appUser = ctx.getBean(AppUserRole, IUser.class);
        boolean isValid = appUser.AuthorizeUser();
        if (isValid) {
            System.out.println("Login Successful !");
            if(AppUserRole.equals("admin")){
                cc = ctx.getBean("dashboard", AdminDashboard.class);
                cc.dashboard();
            }
        } else {
            System.out.println("Invalid username or password!");
            System.out.println("Please enter correct credentials.");
        }
        ctx.close();
    }
}
