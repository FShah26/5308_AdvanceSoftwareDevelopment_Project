package com.group9.server.Dashboard;

import org.springframework.stereotype.Component;

@Component
public class StudentValidator implements InputValidator {

    static final String VALIDATION = "^\\s*([1-8])\\s*$";

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(VALIDATION);
    }
}
