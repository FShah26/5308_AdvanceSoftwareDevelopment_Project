package com.group9.server.Login;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class AdminImpl implements IUser {

    @Autowired
    IUserAuthService userAuthService;
    private String Username;
    private String Password;
    private final String Role;

    @Autowired
    public AdminImpl() {
        this.Role = "admin";
    }


    @Override
    public boolean AuthorizeUser() {
        ArrayList<String> cred = new ArrayList<String>();
        cred = userAuthService.getUserCredentials();
        boolean isValid = userAuthService.ValidateUserCredentials(cred.get(0), cred.get(1), this.Role);
        return isValid;

    }
}
