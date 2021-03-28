package com.group9.server.Notifications;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

class ViewUserNotificationsTest {
    ViewUserNotifications underTest;
    ResultSet mockSet = Mockito.mock(ResultSet.class);
    NotificationPersistence mockPersistence = Mockito.mock(NotificationPersistence.class);

    @BeforeEach
    void setUp() throws SQLException {
        when(mockSet.getString(1)).thenReturn("Hashik");
        when(mockSet.getString(2)).thenReturn("Test Notification");
        when(mockPersistence.fetchNotificationsFromDatabase("Some Guy")).thenReturn(mockSet);

        underTest = new ViewUserNotificationsImpl(mockPersistence);
    }

    @Test
    void displayAllNotifications() {
        underTest.displayAllNotifications("Some Guy");
    }
}