package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Login.IUserInputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        when(mockPersistence.meetingLog("1", "utkarshp123")).thenReturn(mockSet);
        underTest = new ManageMeetingLogic(mockPersistence);
    }

    @DisplayName("meetingLogicTest")
    @ParameterizedTest
    @CsvSource({
            "2,true",
            "6,false",
    })
    void meetingLogicTest(String value, boolean expectedOutput) {
        Assertions.assertEquals(expectedOutput, underTest.meetingValidation(value));
    }

    @DisplayName("validateinput")
    @ParameterizedTest
    @CsvSource({
            "no,Approve,false",
            "1,Approve,true",
    })
    void validateinputTest(String selection, String decision, boolean expectedOutput) {
        Assertions.assertEquals(expectedOutput, underTest.validateInput(selection, decision));
    }

    @DisplayName("respondMeetingRequest")
    @ParameterizedTest
    @CsvSource({
            "12,Approve,Hi this is testcase",
    })
    void respondMeetingRequestTest(int meetingid, String decision, String response) {
        Assertions.assertDoesNotThrow(() -> underTest.respondMeetingRequest(meetingid, decision, response));
    }
}