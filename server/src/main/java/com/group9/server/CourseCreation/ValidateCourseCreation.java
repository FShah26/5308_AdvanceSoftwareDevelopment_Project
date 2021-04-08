package com.group9.server.CourseCreation;

import org.springframework.stereotype.Component;

@Component
public class ValidateCourseCreation implements IValidate {

    @Override
    public Boolean validateInput(String courseId, String credit, String facultyId) {

        if (courseId.length() < 4)
            return false;
        else if (facultyId.length() < 1)
            return false;
        else if (Integer.parseInt(credit) >= 5 || Integer.parseInt(credit) == 0)
            return false;
        else if (courseId.startsWith("CSCI"))
            return true;
        else
            return false;
    }
}
