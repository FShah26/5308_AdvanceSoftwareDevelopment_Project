package com.group9.server.UserCreation;


public interface IAddUserLogic {
    void addUser(String id, String userid, String password, String user_type);
    void addUserDetails(String userid, String user_type, String name, String email_address, String department);
}
