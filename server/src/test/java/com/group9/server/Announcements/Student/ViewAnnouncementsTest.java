package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewAnnouncementsTest {
    ViewAnnouncements underTest;

    @BeforeEach
    void setUp() {
        underTest = new ViewAnnouncementsImpl();
    }

    @Test
    @DisplayName("fetchAnnouncementsTest")
    void fetchAnnouncementsTest() {
        Assertions.assertNotNull(underTest.fetchAnnouncements());
    }

    @Test
    @DisplayName("displayAllAnnouncementsTest")
    void displayAllAnnouncementsTest() {
        underTest.displayAllAnnouncements();
    }
}