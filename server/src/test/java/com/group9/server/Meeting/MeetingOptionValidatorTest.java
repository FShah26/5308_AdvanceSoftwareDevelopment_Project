package com.group9.server.Meeting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MeetingOptionValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "2,true",
            "3,false",
    })
    @DisplayName("validateTest")
    void validateTest(String value, boolean expectedOutput) {
        MeetingOptionValidator validator = new MeetingOptionValidator();
        boolean testOutput = validator.validate(value);
        Assertions.assertEquals(testOutput, expectedOutput);
    }
}