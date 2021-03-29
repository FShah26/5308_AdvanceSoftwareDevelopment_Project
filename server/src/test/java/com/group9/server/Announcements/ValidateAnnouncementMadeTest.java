package com.group9.server.Announcements;

import com.group9.server.Announcements.Admin.ValidateAnnouncementMade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateAnnouncementMadeTest {

    @ParameterizedTest
    @CsvSource({
            "Hi, true",
            "Hi this is my first test announcement and it has limit upto 2000 characters,true",
    })
    @DisplayName("validate_announcementTest")
    void validate_announcementTest(String value, boolean expectedOutput) {
        ValidateAnnouncementMade validator = new ValidateAnnouncementMade();
        boolean testOutput = validator.validate_announcement(value);
        Assertions.assertEquals(testOutput, expectedOutput);
    }
}