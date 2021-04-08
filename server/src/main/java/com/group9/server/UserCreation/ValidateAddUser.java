package com.group9.server.UserCreation;

import org.springframework.stereotype.Component;


@Component
public class ValidateAddUser implements IValidateAddUser {

    static final int USER_ID_LENGTH = 3;
    static final int USER_PASSWORD_LENGTH = 4;
    @Override
    public boolean validateInput(String id, String userId, String password, String userType) {

        boolean result = false;
        int x = 0;
        if (x == Integer.parseInt(id)) {
            System.out.println("PLEASE ENTER VALID ID");
        } else if (userId.length() < USER_ID_LENGTH) {
            System.out.println("PLEASE ENTER VALID USER ID");
        } else if (password.length() < USER_PASSWORD_LENGTH) {
            System.out.println("PLEASE ENTER VALID PASSWORD");
        } else if (x == Integer.parseInt(userType)) {
            System.out.println("PLEASE ENTER VALID USER_TYPE");
        } else
        {
            result = true;
        }
        return result;

    }
}
