package com.group9.server.Quiz.Student;

import com.group9.server.Login.IUserInputValidator;

public class InputAnswerOptionsValidator implements IUserInputValidator {

    private static final String ANSWER_OPTIONS_VALIDATOR_PATTERN = "^\\s*([A-Da-d])\\s*$";
    String regex;

    public InputAnswerOptionsValidator() {
        this.regex = ANSWER_OPTIONS_VALIDATOR_PATTERN;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
