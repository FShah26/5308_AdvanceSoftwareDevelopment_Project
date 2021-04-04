package com.group9.server.UserCreation;

import org.springframework.stereotype.Component;

@Component
public class AddUserLogic implements IAddUserLogic {

    IAddUserPersistence addUserPersistence;

    public void AddUserLogic(IAddUserPersistence addUserPersistence) {
        this.addUserPersistence = addUserPersistence;
    }

    @Override
    public void addUser(String id, String userId, String password, String userType) {
        addUserPersistence.addUser(id, userId, password, userType);
    }

    @Override
    public void addUserDetails(String userId, String userType, String name, String emailAddress, String department) {
        addUserPersistence.addUserDetails(userId, userType, name, emailAddress, department);
    }

}

