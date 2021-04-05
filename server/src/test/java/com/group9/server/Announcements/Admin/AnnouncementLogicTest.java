package com.group9.server.Announcements.Admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

class AnnouncementLogicTest {
    IValidateAnnouncementMade validate = Mockito.mock(IValidateAnnouncementMade.class);
    IAnnouncementPersistence persistence = Mockito.mock(IAnnouncementPersistence.class);
    IAnnouncementLogic underTest;

    @BeforeEach
    public void setUp() throws SQLException {
        when(validate.validateAnnouncement("Hi this is my first announcement")).thenReturn(true);
        when(persistence.InsertAnnouncement("admin", "CSCI222", "Hi this is my first announcement", "hashik")).thenReturn("Success");
        when(persistence.InsertAnnouncement("admin", "CSCI123", "Hi this is my first announcement", "hashik")).thenReturn("Test");

        underTest = new AnnouncementLogic(validate, persistence);
    }

    @ParameterizedTest
    @CsvSource({
            "Success,admin,CSCI222,Hi this is my first announcement,hashik",
            "Test,admin,CSCI123,Hi this is my first announcement,hashik",
    })
    @DisplayName("validateTest")
    void makeAnnouncementTest(String output, String userRole, String courseId, String message, String userId) {
        Assertions.assertNotNull(underTest.makeAnnouncement(userRole, courseId, message, userId));
    }
}