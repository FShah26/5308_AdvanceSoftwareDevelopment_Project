package com.group9.server.Announcements.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FetchAnnouncementsFromPersistenceTest {
    FetchAnnouncementsFromPersistence underTest;

    @BeforeEach
    public void setUp(){
        underTest = new FetchAnnouncementsImpl();
    }

    @Test
    @DisplayName("fetchAnnouncementsFromDatabaseTest")
    public void fetchAnnouncementsFromDatabaseTest(){
        Assertions.assertNotNull(underTest.fetchAnnouncementsFromDatabase());
    }
}
