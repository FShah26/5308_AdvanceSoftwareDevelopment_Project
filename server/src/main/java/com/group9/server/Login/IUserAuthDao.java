package com.group9.server.Login;

public interface IUserAuthDao {
    boolean AuthorizeUser(String uname, String pass, String role);
}
