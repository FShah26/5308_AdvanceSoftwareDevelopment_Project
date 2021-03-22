package com.group9.server.UpcomingLecture;

import com.group9.server.Notes.INotesLogic;
import com.group9.server.Notes.INotesPersistence;
import com.group9.server.Notes.NotesLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UpcomingLectureLogicTest {

    ILecturePersistence mockPersistence = Mockito.mock(ILecturePersistence.class);
    IUpcomingLectureLogic underTest;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockPersistence.viewLecture("hashik")).thenReturn(null);
        underTest = new UpcomingLectureLogic(mockPersistence);
    }

    @Test
    void upcomingTest() {
        Assertions.assertNotNull(underTest.upcoming("hashik"));
    }
}