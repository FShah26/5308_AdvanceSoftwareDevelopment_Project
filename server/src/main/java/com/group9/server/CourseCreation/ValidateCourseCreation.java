package com.group9.server.CourseCreation;

import org.springframework.stereotype.Component;

@Component
public class ValidateCourseCreation implements IValidate {
    private static final int COURSE_LENGTH_CHECKER = 4;
    private static final int FACULTY_LENGTH_CHECKER = 1;
    private static final int MAX_CREDITS = 5;
    private static final int MIN_CREDITS = 0;
    private static final String COURSE_ID_STARTING = "CSCI";

    @Override
    public Boolean validateInput(String courseId, String credit, String facultyId) {

        if (courseId.length() < COURSE_LENGTH_CHECKER) {
            return false;
        }
        else if (facultyId.length() < FACULTY_LENGTH_CHECKER) {
            return false;
        }
        else if (Integer.parseInt(credit) >= MAX_CREDITS || Integer.parseInt(credit) == MIN_CREDITS){
            return false;
        }
        else if (courseId.startsWith(COURSE_ID_STARTING)){
            return true;
        }
        else {
            return false;
        }
    }
}
