package com.group9.server.UserCreation;

import com.group9.server.UserCreation.IValidateAddUser;
import org.springframework.stereotype.Component;


@Component
public class ValidateAddUser implements IValidateAddUser {

    @Override
    public String validateInput(String id, String userId, String password, String userType) {

        String output = "";
        int x = 0;
        if (x == Integer.parseInt(id)) {
            output = "False";
            System.out.println("PLEASE ENTER VALID ID");
        } else if (userId.length() < 3) {
            output = "False";
            System.out.println("PLEASE ENTER VALID USER ID");
        } else if (password.length() < 4) {
            output = "False";
            System.out.println("PLEASE ENTER VALID PASSWORD");
        } else if (x == Integer.parseInt(userType)) {
            output = "False";
            System.out.println("PLEASE ENTER VALID USER_TYPE");
        } else
            output = "true";


        return output;

    }
}
