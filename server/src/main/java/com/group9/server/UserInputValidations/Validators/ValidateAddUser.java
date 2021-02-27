package com.group9.server.UserInputValidations.Validators;

import com.group9.server.UserInputValidations.Interface.IValidate;


public class ValidateAddUser implements IValidate {

    @Override
    public String validate_input(String id,String userid,String password, String user_type) {

        String output="";

            output ="true";

        return output;
    }
}
