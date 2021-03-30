package com.group9.server.Meeting.Faculty;

import com.group9.server.Announcements.Student.FetchAnnouncementsFromPersistence;
import com.group9.server.Announcements.Student.FetchAnnouncementsImpl;
import com.group9.server.cnfg.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfig.class)
class ManageMeetingPersistenceTest {
    IManageMeetingPersistence underTest;
    @Autowired
    DBConfig config;

    @BeforeEach
    public void setUp() throws SQLException {
        underTest = new ManageMeetingPersistence(config);
    }

    @Test
    void meetingLogTest() throws SQLException {
        Assertions.assertNotNull(underTest.meetingLog("faculty1","1"));
    }

    @Test
    void responseMeetingTest() throws SQLException {
        Assertions.assertNotNull(underTest.responseMeeting(12,"Approve","Hi there"));
    }
}


