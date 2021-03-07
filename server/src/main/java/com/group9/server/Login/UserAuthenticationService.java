package com.group9.server.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class UserAuthenticationService implements IUserAuthService {

    @Autowired
    IUserAuthDao userAuthenticationDAO;
    private String username;
    private String password;

    @Override
    public ArrayList<String> getUserCredentials() {
        ArrayList<String> credentials = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome User !");
        System.out.println("Please enter your username :");
        username = sc.nextLine();
        credentials.add(username);
        System.out.println("Please enter your password :");
        password = sc.nextLine();
        credentials.add(password);

        return credentials;
    }

    @Override
    public boolean ValidateUserCredentials(String uname, String pass, String role) {
        System.out.println("Validating Credentials...");
        return userAuthenticationDAO.AuthorizeUser(uname, pass, role);
    }
}
