package com.group9.server.Course_Creation;

import com.group9.server.UserInputValidations.Validators.ValidateCourseCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateCourseCreationTest {

    @Test
    void invalid_input() {
        ValidateCourseCreation vcc = new ValidateCourseCreation();
        Assertions.assertEquals("PLEASE ENTER VALID COURSE ID STARTING WITH CSCI",
                vcc.validate_input("CSC213","4","FID12"));
    }

    @Test
    void valid_input() {
        ValidateCourseCreation vcc = new ValidateCourseCreation();
        Assertions.assertEquals("true",
                vcc.validate_input("CSCI123","4","FID12"));
    }
}