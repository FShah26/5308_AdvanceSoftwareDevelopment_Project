package com.group9.server.Dashboard;

public class AdminIInputValidator implements IInputValidator {

    private static final String VALIDATION = "^\\s*([1-4])\\s*$";
    String regex;

    public AdminIInputValidator() {
        this.regex = VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
