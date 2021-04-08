package com.group9.server.Dashboard;

import org.springframework.stereotype.Component;

@Component
public class FacultyValidator implements IInputValidator {

    private static final String VALIDATION = "^\\s*([1-7])\\s*$";
    String regex;

    public FacultyValidator() {
        this.regex = VALIDATION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
