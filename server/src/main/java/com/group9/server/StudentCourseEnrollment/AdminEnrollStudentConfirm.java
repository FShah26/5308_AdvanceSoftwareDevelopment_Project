package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Dashboard.IAdminInputValidator;

public class AdminEnrollStudentConfirm implements IAdminInputValidator {

    String regex;

    public AdminEnrollStudentConfirm(){
        this.regex="^\\s*([1-4])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
