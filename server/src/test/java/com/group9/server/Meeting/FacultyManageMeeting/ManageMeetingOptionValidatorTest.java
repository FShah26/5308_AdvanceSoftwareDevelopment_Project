package com.group9.server.Meeting.FacultyManageMeeting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ManageMeetingOptionValidatorTest {
    ManageMeetingOptionValidator valid = new ManageMeetingOptionValidator();

    @DisplayName("validateTest")
    @ParameterizedTest
    @CsvSource({
            "1,true",
            "7,false",
    })
    void validateTest(String selection, boolean expectedOutput) {
        Assertions.assertEquals(expectedOutput, valid.validate(selection));
    }
}