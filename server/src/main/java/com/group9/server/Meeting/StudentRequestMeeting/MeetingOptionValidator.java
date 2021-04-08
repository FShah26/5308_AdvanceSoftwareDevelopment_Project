package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class MeetingOptionValidator implements IUserInputValidator {

    private static final String OPTION_VALIDATE = "^\\s*([1-2]|\\*)\\s*$";
    String regex;

    public MeetingOptionValidator() {
        this.regex = OPTION_VALIDATE;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
