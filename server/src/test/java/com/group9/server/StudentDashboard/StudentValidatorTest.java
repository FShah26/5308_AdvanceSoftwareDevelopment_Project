package com.group9.server.StudentDashboard;

import com.group9.server.Dashboard.StudentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StudentValidatorTest {
    @ParameterizedTest
    @CsvSource({
            "9, false",
            "25, false",
            "2, true",
    })
    @DisplayName("validateTest")
    public void validateTest(String value, boolean expectedOutput) {
        StudentValidator validator = new StudentValidator();
        boolean testOutput = validator.validate(value);
        Assertions.assertEquals(testOutput, expectedOutput);
    }
}
