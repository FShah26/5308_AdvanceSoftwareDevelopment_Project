package com.group9.server.UserInputValidations.Validators;

import com.group9.server.UserInputValidations.Interface.IAdminInputValidator;

public class AdminInputValidator implements IAdminInputValidator {

    String regex;

    public AdminInputValidator(){
        this.regex="^\\s*([1-3])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
