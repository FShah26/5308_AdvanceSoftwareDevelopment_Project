package com.group9.server.UserCreation;

import com.group9.server.IExecuteAction;

public interface IAddUser extends IExecuteAction {
    void addUser(String id, String userId, String password, String userType);
    void addUserDetails(String userId, String userType, String name, String emailAddress, String department);
    String getId();
    String getUserId();
    String getPassword();
    String getUserType();
    String getName();
    String getEmail();
    String getDepartment();
}
