package com.group9.server.CourseCreation;

import org.springframework.stereotype.Component;

@Component
public class ValidateCourseCreation implements IValidate {

    @Override
    public String validateInput(String courseId, String credit, String facultyId) {

        String output = "";
        if (courseId.length() < 4)
            output = "PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else if (!(courseId).startsWith("CSCI"))
            output = "PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else if (facultyId.length() < 3)
            output = "PLEASE ENTER VALID FACULTY ID";
        else if (Integer.parseInt(credit) >= 5 || Integer.parseInt(credit) == 0)
            output = "PLEASE ENTER VALID CREDIT BETWEEN 1 TO 5";
        else
            output = "true";

        return output;
    }
}
