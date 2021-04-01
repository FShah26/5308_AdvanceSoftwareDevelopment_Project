package com.group9.server.Quiz.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class QuizAssessmentOptionValidatorTest {
    QuizAssessmentOptionValidator validator = new QuizAssessmentOptionValidator();

    @Test
    public void validateInput() {
        Assertions.assertTrue(validator.validate("*"));
    }

    @Test
    public void validateInvalidInput() {
        Assertions.assertFalse(validator.validate("1   2   3"));
    }

    @Test
    public void validateEmptyInput() {
        Assertions.assertFalse(validator.validate(""));
    }
}
