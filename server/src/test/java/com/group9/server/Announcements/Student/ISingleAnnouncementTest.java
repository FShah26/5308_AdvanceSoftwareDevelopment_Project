package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISingleAnnouncementTest {
    ISingleAnnouncement underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SingleAnnouncement();
    }

    @Test
    void printAnnouncementTest() {
        underTest.printAnnouncement();
    }

    @Test
    void setUserIDTest() {
        underTest.setUserId("TEST1");
    }

    @Test
    void setAnnouncementTest() {
        underTest.setAnnouncement("TEST TEST");
    }
}
