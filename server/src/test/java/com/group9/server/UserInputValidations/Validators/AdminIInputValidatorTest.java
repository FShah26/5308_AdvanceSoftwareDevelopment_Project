package com.group9.server.UserInputValidations.Validators;

import com.group9.server.Dashboard.AdminIInputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdminIInputValidatorTest {


    @Test
    void valid_input() {
        AdminIInputValidator vcc = new AdminIInputValidator();
        Assertions.assertEquals(true,
                vcc.validate("3"));
    }

    @Test
    void invalid_input() {
        AdminIInputValidator vcc = new AdminIInputValidator();
        Assertions.assertEquals(false,
                vcc.validate("6"));
    }

}