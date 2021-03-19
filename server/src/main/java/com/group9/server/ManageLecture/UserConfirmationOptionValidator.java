package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;

public class UserConfirmationOptionValidator implements IUserInputValidator {

    String regex;

    public UserConfirmationOptionValidator() {
        this.regex = "^\\s*([1-2])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
