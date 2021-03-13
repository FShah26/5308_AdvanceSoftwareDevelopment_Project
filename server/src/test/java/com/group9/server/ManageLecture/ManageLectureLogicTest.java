package com.group9.server.ManageLecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;

public class ManageLectureLogicTest {
    IManageLecturePersistence mockPersistence = Mockito.mock(IManageLecturePersistence.class);
    IManageLectureLogic underTest;
    private String futureDate;

    @BeforeEach
    public void setup() throws SQLException {

        underTest = new ManageLectureLogic(mockPersistence);
        when(mockPersistence.getFacultyCourses("utkarshp123")).thenReturn(null);
        when(mockPersistence.addLecture("utkarshp123", "CSCI222", "TestTopic",new Date("20/10/2050 12:00:00"))).thenReturn(true);

    }


    @DisplayName("scheduleLectureTest")
    @Test
    public void scheduleLectureTest()
    {
        Assertions.assertFalse(underTest.scheduleLecture("utkarshp123","CSCI222","testCourse","20/02/2050 10:00:00"));
    }


}
