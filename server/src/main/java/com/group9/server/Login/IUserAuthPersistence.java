package com.group9.server.Login;

public interface IUserAuthPersistence {
    boolean authorizeUser(String uname, String pass, String role);
}
