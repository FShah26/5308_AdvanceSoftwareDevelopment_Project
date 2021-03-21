package com.group9.server.Meeting;

import com.group9.server.Announcements.ValidateAnnouncementMade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CourseSelectionValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "3,4,true",
            "Hi,4,false",
    })
    @DisplayName("validateTest")
    void validateTest(String value,int length, boolean expectedOutput) {
        CourseSelectionValidator validator = new CourseSelectionValidator();
        boolean testOutput = validator.validate(value,length);
        Assertions.assertEquals(testOutput, expectedOutput);
    }
}