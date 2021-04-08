package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class IAnnouncementListTest {

    IAnnouncementList underTest;
    ISingleAnnouncement singleAnnouncementMock;

    @BeforeEach
    void setUp() {
        underTest = new AnnouncementList();
    }

    @Test
    @DisplayName("printAllAnnouncementsTest")
    void printAllAnnouncementsTest() {
        underTest.printAllAnnouncements();
    }

    @Test
    @DisplayName("addAnnouncementTest")
    void addAnnouncementTest() {
        singleAnnouncementMock = Mockito.mock(ISingleAnnouncement.class);
        underTest.addAnnouncement(singleAnnouncementMock);
    }
}