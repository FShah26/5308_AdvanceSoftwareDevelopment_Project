package com.group9.server.Course_Creation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCourseCreationTest {

    @Test
    void invalid_input() {
        ValidateCourseCreation vcc = new ValidateCourseCreation();
        Assertions.assertEquals("PLEASE ENTER VALID COURSE ID STARTING WITH CSCI",
                vcc.validate_input("CSC213","4","qwerty"));
    }

    @Test
    void valid_input() {
        ValidateCourseCreation vcc = new ValidateCourseCreation();
        Assertions.assertEquals("true",
                vcc.validate_input("CSCI123","4","qwerty"));
    }
}