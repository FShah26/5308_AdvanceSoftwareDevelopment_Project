package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

class AnnouncementListTest {

    AnnouncementList underTest;
    SingleAnnouncement singleAnnouncementMock;

    @BeforeEach
    void setUp() {
        underTest = new AnnouncementListImpl();
    }

    @Test
    @DisplayName("printAllAnnouncementsTest")
    void printAllAnnouncementsTest() {
        underTest.printAllAnnouncements();
    }

    @Test
    @DisplayName("addAnnouncementTest")
    void addAnnouncementTest() {
        singleAnnouncementMock = Mockito.mock(SingleAnnouncement.class);
        underTest.addAnnouncement(singleAnnouncementMock);
    }
}