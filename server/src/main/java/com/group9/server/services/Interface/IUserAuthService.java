package com.group9.server.services.Interface;

import java.util.ArrayList;

public interface IUserAuthService {
    ArrayList<String> getUserCredentials();

    boolean ValidateUserCredentials(String uname, String pass, String role);
}
