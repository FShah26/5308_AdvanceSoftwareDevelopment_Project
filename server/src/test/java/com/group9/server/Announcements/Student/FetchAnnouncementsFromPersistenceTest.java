package com.group9.server.Announcements.Student;

import com.group9.server.Database.ISingletonDatabase;
import com.group9.server.cnfg.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
public class FetchAnnouncementsFromPersistenceTest {
    FetchAnnouncementsFromPersistence underTest;
    @Autowired
    DBConfig config;
    ISingletonDatabase mockDatabase = mock(ISingletonDatabase.class);

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockDatabase.getInstance()).thenReturn(mockDatabase);
        when(mockDatabase.getConnection(config)).thenReturn(DriverManager.getConnection(config.url, config.user, config.password));
        underTest = new FetchAnnouncementsImpl(config, mockDatabase);
    }

    @Test
    @DisplayName("fetchAnnouncementsFromDatabaseTest")
    public void fetchAnnouncementsFromDatabaseTest() throws SQLException {
        Assertions.assertNotNull(underTest.fetchAnnouncementsFromDatabase());
    }
}
