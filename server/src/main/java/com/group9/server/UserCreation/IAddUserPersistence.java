package com.group9.server.UserCreation;

public interface IAddUserPersistence {
    void addUser(String id, String userId, String password, String userType);

    void addUserDetails(String userId, String userType, String name, String emailAddress, String department);

}
