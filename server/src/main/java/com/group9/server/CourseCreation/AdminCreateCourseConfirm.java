package com.group9.server.CourseCreation;

import com.group9.server.Dashboard.InputValidator;

public class AdminCreateCourseConfirm implements InputValidator {

    String regex;

    public AdminCreateCourseConfirm(){
        this.regex="^\\s*([1-2])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
