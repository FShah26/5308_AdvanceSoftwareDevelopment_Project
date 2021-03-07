package com.group9.server.Dashboard;

import org.springframework.stereotype.Component;

@Component
public class StudentValidator implements InputValidator{

    static final String regexForValidation = "^\\s*([1-2])\\s*$";

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regexForValidation);
    }
}
