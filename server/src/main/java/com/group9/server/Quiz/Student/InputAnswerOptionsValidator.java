package com.group9.server.Quiz.Student;

import com.group9.server.Login.IUserInputValidator;

public class InputAnswerOptionsValidator implements IUserInputValidator {
    String regex;

    public InputAnswerOptionsValidator() {
        this.regex = "^\\s*([A-Da-d])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
