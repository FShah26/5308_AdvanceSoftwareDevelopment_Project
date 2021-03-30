package com.group9.server.Meeting.Faculty;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class ManageMeetingOptionValidator implements IUserInputValidator {

    String regex;

    public ManageMeetingOptionValidator() {
        this.regex = "^\\s*([1-3]|\\*)\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
