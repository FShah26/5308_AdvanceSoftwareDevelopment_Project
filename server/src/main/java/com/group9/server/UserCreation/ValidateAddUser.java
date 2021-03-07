package com.group9.server.UserCreation;

import com.group9.server.UserCreation.IValidateAddUser;
import org.springframework.stereotype.Component;


@Component
public class ValidateAddUser implements IValidateAddUser {

    @Override
    public String validate_input(String id,String userid,String password, String user_type) {

        String output="";
        int x = 0;
        if( x == Integer.parseInt(id))
        output = "PLEASE ENTER VALID ID";
        else if(userid.length() < 3)
        output = "PLEASE ENTER VALID USER ID";
        else if(password.length() < 4)
        output = "PLEASE ENTER VALID PASSWORD";
        else if( x == Integer.parseInt(user_type))
        output = "PLEASE ENTER VALID USER_TYPE";
        else
            output ="true";

        return output;

    }
}
