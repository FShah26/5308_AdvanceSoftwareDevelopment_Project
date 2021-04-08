package com.group9.server.Dashboard;

import org.springframework.stereotype.Component;

@Component
public class AdminInputValidator implements IInputValidator {

    private static final String ADMIN_INPUT_VALIDATION = "^\\s*([1-5])\\s*$";
    String regex;

    public AdminInputValidator() {
        this.regex = ADMIN_INPUT_VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
