package com.group9.server.Course_Creation;

import com.group9.server.CourseCreation.ValidateCourseCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateCourseCreationTest {

    @Test
    void invalid_input() {
        ValidateCourseCreation validate = new ValidateCourseCreation();
        Assertions.assertEquals("PLEASE ENTER VALID COURSE ID STARTING WITH CSCI",
                validate.validateInput("CSC213", "4", "FID12"));
    }

    @Test
    void valid_input() {
        ValidateCourseCreation validate = new ValidateCourseCreation();
        Assertions.assertEquals("true",
                validate.validateInput("CSCI123", "4", "FID12"));
    }
}