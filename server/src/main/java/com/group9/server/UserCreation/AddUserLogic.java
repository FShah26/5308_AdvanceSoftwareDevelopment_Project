package com.group9.server.UserCreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserLogic implements IAddUserLogic {

    @Autowired
    IAddUserPersistence addUserPersistence;

    @Override
    public void addUser(String id, String userid, String password, String user_type) {
        addUserPersistence.addUser(id,userid,password,user_type);
    }
    @Override
    public void addUserDetails(String userid, String user_type, String name, String email_address, String department){
        addUserPersistence.addUserDetails(userid, user_type, name, email_address, department);
    }

}

