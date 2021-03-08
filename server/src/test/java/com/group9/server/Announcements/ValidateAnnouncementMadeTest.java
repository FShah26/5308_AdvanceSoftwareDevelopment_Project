package com.group9.server.Announcements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ValidateAnnouncementMadeTest {
    @ParameterizedTest
    @CsvSource({
            "Hi,true",
            "2,true",
    })
   public void validate_announcementTest(String message,Boolean expected ) {
        ValidateAnnouncementMade announcement = new ValidateAnnouncementMade();
        Assertions.assertEquals(expected,announcement.validate_announcement(message));
    }
}