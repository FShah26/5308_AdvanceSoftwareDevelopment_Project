package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingleAnnouncementTest {
    SingleAnnouncement underTest;

    @BeforeEach
    public void setUp(){
        underTest = new SingleAnnouncementImpl();
    }

    @Test
    void printAnnouncementTest() {
        underTest.printAnnouncement();
    }

    @Test
    void setUserIDTest() {
        underTest.setUserID("TEST1");
    }

    @Test
    void setAnnouncementTest() {
        underTest.setAnnouncement("TEST TEST");
    }
}
