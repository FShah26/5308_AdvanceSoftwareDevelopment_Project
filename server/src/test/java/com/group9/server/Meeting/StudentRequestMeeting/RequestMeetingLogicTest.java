package com.group9.server.Meeting.StudentRequestMeeting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

class RequestMeetingLogicTest {

    IRequestMeetingPersistence mockPersistence = Mockito.mock(IRequestMeetingPersistence.class);
    IRequestMeetingLogic test;

    @BeforeEach
    public void setUp() throws SQLException {
        test = new RequestMeetingLogic(mockPersistence);
        when(mockPersistence.fetchRegisteredCourses("hashik")).thenReturn(null);
        when(mockPersistence.setMeeting("CSCI200", "Student1", "testing")).thenReturn("set successfully");
        when(mockPersistence.setMeeting("CSCI123", "Student2", "testing")).thenReturn("failed");

    }

    @Test
    void viewCoursesTest() throws SQLException {
        Assertions.assertNotNull(test.viewCourse("hashik"));
    }

    @ParameterizedTest
    @CsvSource({
            "set successfully,CSCI200,Student1,testing",
            "set successfully,CSCI200,Student1,testing",
    })
    @DisplayName("raiseMeetingRequestTest")
    void raiseMeetingRequestTest(String output, String courseId, String studentId, String reason) {
        Assertions.assertEquals(output, test.raiseMeetingRequest(courseId, studentId, reason));
    }
}