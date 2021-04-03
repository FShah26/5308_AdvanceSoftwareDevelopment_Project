package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfig.class)
class ManageMeetingPersistenceTest {
    IManageMeetingPersistence underTest;
    @Autowired
    DBConfig config;
    ISingletonDatabase mockDatabase = mock(ISingletonDatabase.class);

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockDatabase.getInstance()).thenReturn(mockDatabase);
        when(mockDatabase.getConnection(config)).thenReturn(DriverManager.getConnection(config.url, config.user, config.password));
        underTest = new ManageMeetingPersistence(config, mockDatabase);

    }

    @Test
    void meetingLogTest() throws SQLException {
        Assertions.assertNotNull(underTest.meetingLog("faculty1", "1"));
    }

    @Test
    void responseMeetingTest() throws SQLException {
        Assertions.assertNotNull(underTest.responseMeeting(12, "Approve", "Hi there"));
    }
}


