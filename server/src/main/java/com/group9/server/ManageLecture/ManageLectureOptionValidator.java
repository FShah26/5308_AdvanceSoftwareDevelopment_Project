package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;

public class ManageLectureOptionValidator implements IUserInputValidator {
    private static final String REGEX = "^\\s*([1-3]|\\*)\\s*$";
    String regex;

    public ManageLectureOptionValidator() {
        this.regex = REGEX;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
