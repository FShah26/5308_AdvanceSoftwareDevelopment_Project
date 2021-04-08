package com.group9.server.Login;

import com.group9.server.Dashboard.IDashboard;
import com.group9.server.HomePage.IUserDashboardFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class UserAuthenticationLogic implements IUserAuthLogic {
    IUserAuthPersistence userAuthPersistence;
    IUserDashboardFactory dashboardFactory;
    private static final int USER_NAME = 0;
    private static final int USER_PASSWORD = 1;
    private String username;
    private String password;
    private boolean authStatus = false;

    public UserAuthenticationLogic(IUserAuthPersistence userAuthPersistence, IUserDashboardFactory dashboardFactory) {
        this.userAuthPersistence = userAuthPersistence;
        this.dashboardFactory = dashboardFactory;
    }

    @Override
    public IDashboard initiateLogin(String userRole) {
        List<String> credentials = getUserCredentials();
        return validateUserCredentials(credentials.get(USER_NAME), credentials.get(USER_PASSWORD), userRole);
    }

    @Override
    public ArrayList<String> getUserCredentials() {
        ArrayList<String> credentials = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome User !");
        System.out.println("Please enter your username :");
        username = scanner.nextLine();
        credentials.add(username);
        System.out.println("Please enter your password :");
        password = scanner.nextLine();
        credentials.add(password);

        return credentials;
    }

    @Override
    public IDashboard validateUserCredentials(String uname, String pass, String userRole) {
        System.out.println("Validating Credentials...");
        authStatus = userAuthPersistence.authorizeUser(uname, pass, userRole);
        if (authStatus) {
            return dashboardFactory.getDashboard(userRole, uname);
        }
        return null;
    }

    @Override
    public boolean isAuthSuccessful() {
        return authStatus;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
