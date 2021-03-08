package com.group9.server.Dashboard;

public class FacultyValidator implements InputValidator{

    String regex;

    public FacultyValidator() {
        this.regex = "^\\s*([1-5])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
