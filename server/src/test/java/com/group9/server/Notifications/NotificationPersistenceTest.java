package com.group9.server.Notifications;

import com.group9.server.cnfg.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfig.class)
class NotificationPersistenceTest {
    NotificationPersistence underTest;

    @Autowired
    DBConfig config;
    @BeforeEach
    void setUp() throws SQLException {
        underTest = new NotificationPersistenceImpl(config);
    }

    @Test
    void fetchNotificationsFromDatabase() throws SQLException {
        Assertions.assertNotNull(underTest.fetchNotificationsFromDatabase());
    }
}