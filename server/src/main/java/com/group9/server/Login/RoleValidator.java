package com.group9.server.Login;

import org.springframework.stereotype.Component;

@Component
public class RoleValidator implements IUserInputValidator {
    private static final String VALIDATION = "^\\s*([1-3]|[*])\\s*$";
    String regex;

    public RoleValidator() {
        this.regex = VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
