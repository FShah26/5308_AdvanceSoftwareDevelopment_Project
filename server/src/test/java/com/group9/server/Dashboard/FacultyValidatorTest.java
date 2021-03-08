package com.group9.server.Dashboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FacultyValidatorTest {
    @ParameterizedTest
    @CsvSource({
            "5, true",
            "9, false",
            "2, true",
    })
    @DisplayName("validateTest")
    public void validateTest(String value, boolean expectedOutput) {
        FacultyValidator validator = new FacultyValidator();
        boolean testOutput = validator.validate(value);
        Assertions.assertEquals(testOutput, expectedOutput);
    }
}