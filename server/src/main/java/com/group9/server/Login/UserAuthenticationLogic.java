package com.group9.server.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class UserAuthenticationLogic implements IUserAuthLogic {

    @Autowired
    IUserAuthPersistence userAuthPersistence;
    private String username;
    private String password;

    @Override
    public boolean initiateLogin(String userRole) {
        List<String> credentials = getUserCredentials();
        return validateUserCredentials(credentials.get(0), credentials.get(1), userRole);
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
    public boolean validateUserCredentials(String uname, String pass, String role) {
        System.out.println("Validating Credentials...");
        return userAuthPersistence.authorizeUser(uname, pass, role);
    }

    @Override
    public String getUsername() {
        return username;
    }
}
