package com.group9.server.StudentCourseEnrollment;

import org.springframework.stereotype.Component;

@Component
public class ValidateEnrollStudent implements IValidateEnrollStudent {

    @Override
    public String validateInput(String userId, String courseId, String Term) {

        String output = "";
        if (!(courseId).startsWith("CSCI"))
            output = "PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else
            output = "true";

        return output;
    }
}
