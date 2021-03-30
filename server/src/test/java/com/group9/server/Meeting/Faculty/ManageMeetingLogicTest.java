package com.group9.server.Meeting.Faculty;

import com.group9.server.Login.IUserInputValidator;
import com.group9.server.ManageLecture.IManageLectureLogic;
import com.group9.server.ManageLecture.IManageLecturePersistence;
import com.group9.server.Meeting.MeetingOptionValidator;
import com.group9.server.Notifications.NotificationPersistence;
import com.group9.server.Notifications.ViewUserNotifications;
import com.group9.server.Notifications.ViewUserNotificationsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ManageMeetingLogicTest {

    IManageMeetingPersistence mockPersistence = Mockito.mock(ManageMeetingPersistence.class);
    IUserInputValidator mockValidator = Mockito.mock(ManageMeetingOptionValidator.class);
    IManageMeetingLogic underTest;
    ResultSet mockSet = Mockito.mock(ResultSet.class);
    @BeforeEach
    void setUp() throws SQLException {
        when(mockSet.getString(1)).thenReturn("Hashik");
        when(mockValidator.validate("2")).thenReturn(true);
        when(mockValidator.validate("6")).thenReturn(false);
        when(mockPersistence.meetingLog("1","utkarshp123")).thenReturn(mockSet);
        underTest = new ManageMeetingLogic(mockValidator,mockPersistence);
    }

    @DisplayName("meetingLogicTest")
    @ParameterizedTest
    @CsvSource({
            "2,true",
            "6,false",
    })
    void meetingLogicTest(String value,boolean expectedOutput) {
        Assertions.assertEquals(expectedOutput,underTest.meetingLogic(value));
    }

    @DisplayName("validateinput")
    @ParameterizedTest
    @CsvSource({
            "no,Approve,false",
            "1,Approve,true",
    })
    void validateinputTest(String selection,String decision,boolean expectedOutput) {
        Assertions.assertEquals(expectedOutput,underTest.validateinput(selection,decision));
    }

    @DisplayName("respondMeetingRequest")
    @ParameterizedTest
    @CsvSource({
            "12,Approve,Hi this is testcase",
    })
    void respondMeetingRequestTest(int meetingid,String decision, String response) {
        Assertions.assertDoesNotThrow(() -> underTest.respondMeetingRequest(meetingid, decision, response));
    }
}