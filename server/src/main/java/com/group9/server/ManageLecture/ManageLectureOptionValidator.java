package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;

public class ManageLectureOptionValidator implements IUserInputValidator {

    String regex;

    public ManageLectureOptionValidator() {
        this.regex = "^\\s*([1-3])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
