package com.group9.server.Meeting;

import com.group9.server.Notes.NotesLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RequestMeetingLogicTest {

    IRequestMeetingPersistence mockPersistence = Mockito.mock(IRequestMeetingPersistence.class);
    IRequestMeetingLogic test;

    @BeforeEach
    public void setUp() throws SQLException {
        test = new RequestMeetingLogic(mockPersistence);
        when(mockPersistence.fetchRegisteredCourses("hashik")).thenReturn(null);
        when(mockPersistence.setMeeting("CSCI200", "Hashik", "testing")).thenReturn("set successfully");
        when(mockPersistence.setMeeting("CSCI200", "Hashik", "testing")).thenReturn("failed");

    }

    @Test
    void viewCoursesTest() throws SQLException {
        Assertions.assertNotNull(test.viewCourses("hashik"));
    }

    @Test
    void raiseMeetingRequestTest() {
        Assertions.assertDoesNotThrow(() -> test.raiseMeetingRequest("CSCI200", "Hashik", "testing"));
    }
}