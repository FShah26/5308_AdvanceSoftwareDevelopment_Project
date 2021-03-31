package com.group9.server.Quiz.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputAnswerOptionsValidatorTest {
    InputAnswerOptionsValidator validator = new InputAnswerOptionsValidator();
    @Test
    public void validateInput()
    {
        Assertions.assertTrue(validator.validate("A"));
    }

    @Test
    public void validateInvalidInput()
    {
        Assertions.assertFalse(validator.validate("1   2   3"));
    }

    @Test
    public  void validateEmptyInput()
    {
        Assertions.assertFalse(validator.validate(""));
    }
}
