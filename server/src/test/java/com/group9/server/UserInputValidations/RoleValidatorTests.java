package com.group9.server.UserInputValidations;

import com.group9.server.Login.RoleValidator;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class RoleValidatorTests  {
    RoleValidator rv = new RoleValidator();
    @Test
    public void validateInput()
    {
        Assertions.assertTrue(rv.validate("*"));
    }

    @Test
    public void validateInvalidInput()
    {
        Assertions.assertFalse(rv.validate("1   2   3"));
    }

    @Test
    public  void validateEmptyInput()
    {
        Assertions.assertFalse(rv.validate(""));
    }

}
