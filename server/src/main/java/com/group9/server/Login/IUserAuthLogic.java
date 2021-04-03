package com.group9.server.Login;

import com.group9.server.Dashboard.IDashboard;

import java.util.ArrayList;

public interface IUserAuthLogic {
    IDashboard initiateLogin(String userRole);

    ArrayList<String> getUserCredentials();

    IDashboard validateUserCredentials(String uname, String pass, String role);

    String getUsername();

    boolean isAuthSuccessful();
}
