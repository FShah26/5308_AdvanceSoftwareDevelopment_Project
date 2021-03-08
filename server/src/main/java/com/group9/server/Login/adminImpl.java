package com.group9.server.Login;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class adminImpl implements IUser {

    private final String Role;
    @Autowired
    IUserAuthLogic userAuthService;
    private String Username;
    private String Password;

    @Autowired
    public adminImpl() {
        this.Role = "admin";
    }


    @Override
    public boolean authorizeUser() {
        ArrayList<String> cred = new ArrayList<String>();
        cred = userAuthService.getUserCredentials();
        boolean isValid = userAuthService.validateUserCredentials(cred.get(0), cred.get(1), this.Role);
        return isValid;

    }
}
