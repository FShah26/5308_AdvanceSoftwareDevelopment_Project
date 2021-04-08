package com.group9.server.Quiz.Student;

import com.group9.server.Login.IUserInputValidator;

public class QuizAssessmentOptionValidator implements IUserInputValidator {

    private static final String QUIZ_OPTION_VALIDATE_PATTERN =  "^\\s*([1-2]|\\*)\\s*$";
    String regex;

    public QuizAssessmentOptionValidator() {
        this.regex = QUIZ_OPTION_VALIDATE_PATTERN;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
