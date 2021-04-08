package com.group9.server.UserCreation;

import com.group9.server.Dashboard.IInputValidator;

public class AdminAddUserConfirm implements IInputValidator {

    static final String ADD_USER_CONFIRMATION_VALIDATION = "^\\s*([1-8])\\s*$";
    String regex;

    public AdminAddUserConfirm() {
        this.regex = ADD_USER_CONFIRMATION_VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
