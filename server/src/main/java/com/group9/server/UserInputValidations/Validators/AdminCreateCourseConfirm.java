package com.group9.server.UserInputValidations.Validators;

import com.group9.server.UserInputValidations.Interface.IAdminInputValidator;

public class AdminCreateCourseConfirm implements IAdminInputValidator {

    String regex;

    public AdminCreateCourseConfirm(){
        this.regex="^\\s*([1-2])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
