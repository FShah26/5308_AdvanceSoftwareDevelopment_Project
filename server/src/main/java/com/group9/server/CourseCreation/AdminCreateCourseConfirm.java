package com.group9.server.CourseCreation;

import com.group9.server.Dashboard.IInputValidator;
import org.springframework.stereotype.Component;

@Component
public class AdminCreateCourseConfirm implements IInputValidator {

    private static final String CONDITION = "^\\s*([1-2])\\s*$";
    String regex;

    public AdminCreateCourseConfirm() {
        this.regex = CONDITION;
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
