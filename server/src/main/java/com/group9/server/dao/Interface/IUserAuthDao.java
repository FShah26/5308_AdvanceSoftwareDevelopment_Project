package com.group9.server.dao.Interface;

public interface IUserAuthDao {
    boolean AuthorizeUser(String uname, String pass, String role);
}
