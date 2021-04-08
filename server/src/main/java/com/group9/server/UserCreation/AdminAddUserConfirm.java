package com.group9.server.UserCreation;

import com.group9.server.Dashboard.IInputValidator;

public class AdminAddUserConfirm implements IInputValidator {

    String regex;

    public AdminAddUserConfirm() {
        this.regex = "^\\s*([1-3])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
