package com.group9.server.UserCreation;

import org.springframework.stereotype.Component;

@Component
public class AddUserLogic implements IAddUserLogic {

    IAddUserPersistence persistence;

    public AddUserLogic(IAddUserPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public String addUser(String id, String userId, String password, String userType) {
        String message = "Added user";
        try {
            persistence.addUser(id, userId, password, userType);
        } catch (Exception e) {
            System.out.println("Adding feedback failed");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String addUserDetails(String userId, String userType, String name, String emailAddress, String department) {
        String message = "Added user details";
        try {
            persistence.addUserDetails(userId, userType, name, emailAddress, department);
        } catch (Exception e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
        return message;
    }
}

