package com.group9.server.Announcements.Student;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfig.class)
public class FetchAnnouncementsFromPersistenceTest {
    FetchAnnouncementsFromPersistence underTest;
    @Autowired
    DBConfig config;

    @BeforeEach
    public void setUp() throws SQLException {
        underTest = new FetchAnnouncementsImpl(config);
    }

    @Test
    @DisplayName("fetchAnnouncementsFromDatabaseTest")
    public void fetchAnnouncementsFromDatabaseTest() throws SQLException {
        Assertions.assertNotNull(underTest.fetchAnnouncementsFromDatabase());
    }
}
