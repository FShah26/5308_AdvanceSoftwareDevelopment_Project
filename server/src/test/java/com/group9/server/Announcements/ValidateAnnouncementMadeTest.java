package com.group9.server.Announcements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateAnnouncementMadeTest {

    @Test
    void validate_annoucement() {
        ValidateAnnouncementMade va =new ValidateAnnouncementMade();
        Assertions.assertEquals(true ,va.validate_announcement("Hi"));
    }
}