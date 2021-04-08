package com.group9.server.Dashboard;

public class AdminInputValidator implements InputValidator {

    static final String VALIDATION = "^\\s*([1-4])\\s*$";
    String regex;

    public AdminInputValidator() {
        this.regex = VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
