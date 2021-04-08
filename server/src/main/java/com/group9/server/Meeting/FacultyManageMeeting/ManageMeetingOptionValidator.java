package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class ManageMeetingOptionValidator implements IUserInputValidator {

    static final String OPTION_VALIDATE = "^\\s*([1-3]|\\*)\\s*$";
    String regex;

    public ManageMeetingOptionValidator() {
        this.regex = OPTION_VALIDATE;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
