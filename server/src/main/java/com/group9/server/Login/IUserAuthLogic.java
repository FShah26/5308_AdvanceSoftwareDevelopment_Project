package com.group9.server.Login;

import java.util.ArrayList;

public interface IUserAuthLogic {
    ArrayList<String> getUserCredentials();

    boolean validateUserCredentials(String uname, String pass, String role);
}
