package com.group9.server.Dashboard;

import org.springframework.stereotype.Component;

@Component
public class AdminIInputValidator implements IInputValidator {

    private static final String VALIDATION = "^\\s*([1-5])\\s*$";
    String regex;

    public AdminIInputValidator() {
        this.regex = VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
