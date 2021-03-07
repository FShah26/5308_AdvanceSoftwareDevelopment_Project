package com.group9.server.UserInputValidations.Validators;

import com.group9.server.Dashboard.AdminInputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdminInputValidatorTest {


    @Test
    void valid_input() {
        AdminInputValidator vcc = new AdminInputValidator();
        Assertions.assertEquals(true,
                vcc.validate("3"));
    }

    @Test
    void invalid_input() {
        AdminInputValidator vcc = new AdminInputValidator();
        Assertions.assertEquals(false,
                vcc.validate("5"));
    }

}