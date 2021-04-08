package com.group9.server.UserCreation;


public interface IAddUserLogic {
    String addUser(String id, String userId, String password, String userType);

    String addUserDetails(String userId, String userType, String name, String emailAddress, String department);
}
