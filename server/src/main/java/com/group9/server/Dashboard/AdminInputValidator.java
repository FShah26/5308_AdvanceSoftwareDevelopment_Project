package com.group9.server.Dashboard;

public class AdminInputValidator implements InputValidator {

    String regex;
    final String CONDITION = "^\\s*([1-4])\\s*$";

    public AdminInputValidator() {
        this.regex = "^\\s*([1-4])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
