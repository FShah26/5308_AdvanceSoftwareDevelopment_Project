package com.group9.server.Meeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class MeetingOptionValidator implements IUserInputValidator {

    String regex;

    public MeetingOptionValidator() {
        this.regex = "^\\s*([1-2])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
