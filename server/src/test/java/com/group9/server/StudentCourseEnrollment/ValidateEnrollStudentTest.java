package com.group9.server.StudentCourseEnrollment;

import com.group9.server.StudentCourseEnrollment.ValidateEnrollStudent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateEnrollStudentTest {

    @Test
    void invalid_input() {
        ValidateEnrollStudent ves = new ValidateEnrollStudent();
        Assertions.assertEquals("PLEASE ENTER VALID COURSE ID STARTING WITH CSCI",
                ves.validateInput("5","CSC213","summer"));
    }

    @Test
    void valid_input() {
        ValidateEnrollStudent ves = new ValidateEnrollStudent();
        Assertions.assertEquals("true",
                ves.validateInput("4","CSCI200","winter"));
    }

}
