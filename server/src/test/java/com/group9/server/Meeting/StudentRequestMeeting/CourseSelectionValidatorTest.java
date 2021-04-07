package com.group9.server.Meeting.StudentRequestMeeting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CourseSelectionValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "3,4,true",
            "Hi,4,false",
    })
    @DisplayName("validateTest")
    void validateTest(String value, int length, boolean expectedOutput) {
        ICourseSelectionValidator validator = new CourseSelectionValidator();
        boolean testOutput = validator.validate(value, length);
        Assertions.assertEquals(testOutput, expectedOutput);
    }
}