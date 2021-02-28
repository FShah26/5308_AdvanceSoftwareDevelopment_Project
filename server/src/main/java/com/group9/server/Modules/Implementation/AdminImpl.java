package com.group9.server.Modules.Implementation;

import com.group9.server.Modules.Interface.IUser;
import com.group9.server.services.Interface.IUserAuthService;
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
