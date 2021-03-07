package com.group9.server.CourseCreation;

import com.group9.server.Dashboard.IAdminInputValidator;

public class AdminCreateCourseConfirm implements IAdminInputValidator {

    String regex;

    public AdminCreateCourseConfirm(){
        this.regex="^\\s*([1-2])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
