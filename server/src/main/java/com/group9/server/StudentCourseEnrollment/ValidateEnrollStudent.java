package com.group9.server.StudentCourseEnrollment;

import org.springframework.stereotype.Component;

@Component
public class ValidateEnrollStudent implements IValidateEnrollStudent {

    private static final String COURSE_ID_PREFIX =  "CSCI";

    @Override
    public String validateInput(String userId, String courseId, String Term) {

        String output = "";
        if (!(courseId).startsWith(COURSE_ID_PREFIX))
            output = "PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else
            output = "true";
        return output;
    }
}
