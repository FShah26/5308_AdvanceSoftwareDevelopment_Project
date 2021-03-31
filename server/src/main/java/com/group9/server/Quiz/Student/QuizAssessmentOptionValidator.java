package com.group9.server.Quiz.Student;

import com.group9.server.Login.IUserInputValidator;

public class QuizAssessmentOptionValidator implements IUserInputValidator {
    String regex;

    public QuizAssessmentOptionValidator() {
        this.regex = "^\\s*([1-2]|\\*)\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
