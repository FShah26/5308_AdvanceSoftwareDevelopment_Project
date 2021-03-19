package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ViewAnnouncementsTest {
    ViewAnnouncements underTest;
    FetchAnnouncementsFromPersistence mockPersistence = Mockito.mock(FetchAnnouncementsFromPersistence.class);
    ResultSet set = Mockito.mock(ResultSet.class);

    @BeforeEach
    void setUp() throws SQLException {
        when(set.getString(1)).thenReturn("Hashik");
        when(set.getString(2)).thenReturn("Test Notification or Announcement");
        when(mockPersistence.fetchAnnouncementsFromDatabase()).thenReturn(set);
        underTest = new ViewAnnouncementsImpl(mockPersistence);
    }

    @Test
    void fetchAnnouncements() {
        Assertions.assertNotNull(underTest.fetchAnnouncements());
    }

    @Test
    void displayAllAnnouncements() {
        underTest.displayAllAnnouncements();
    }
}