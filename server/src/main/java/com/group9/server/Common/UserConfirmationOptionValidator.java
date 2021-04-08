package com.group9.server.Common;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class UserConfirmationOptionValidator implements IUserInputValidator {

    private static final String VALIDATION = "^\\s*([1-2])\\s*$";
    String regex;

    public UserConfirmationOptionValidator() {
        this.regex = VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
